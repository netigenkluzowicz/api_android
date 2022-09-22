package pl.netigen.gms.payments

import android.app.Activity
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.android.billingclient.api.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import pl.netigen.coreapi.payments.IPaymentsRepo
import pl.netigen.coreapi.payments.Security
import pl.netigen.coreapi.payments.model.*
import pl.netigen.extensions.MutableSingleLiveEvent
import pl.netigen.extensions.SingleLiveEvent
import timber.log.Timber

class GMSPaymentsRepo(
    private val activity: Activity,
    private val inAppSkuList: List<String>,
    private val noAdsInAppSkuList: List<String>,
    private val subscriptionsSkuList: List<String>,
    private val isDebugMode: Boolean = false,
    private val consumablesInAppSkuList: List<String> = emptyList(),
) : IPaymentsRepo, PurchasesUpdatedListener, BillingClientStateListener {
    private var productDetailsList: MutableList<ProductDetails> = mutableListOf()
    private var makingPurchaseActive: Boolean = false
    private var queryStarted: Boolean = false
    private var isConnecting: Boolean = false
    private var lastError: PaymentError? = null
    private var application = activity.application
    private val localCacheBillingClient by lazy { LocalBillingDb.getInstance(application) }
    private val gmsBillingClient: BillingClient = BillingClient
        .newBuilder(application)
        .enablePendingPurchases()
        .setListener(this)
        .build()
    override val skuDetailsLD by lazy { localCacheBillingClient.skuDetailsDao().skuDetailsLiveData() }

    override val noAdsActive = localCacheBillingClient.purchaseDao().getPurchasesFlow()
        .map { list ->
            list.any { it.data.products.any { s -> s in noAdsInAppSkuList } }
        }

    private val _lastPaymentEvent = MutableSingleLiveEvent<PaymentEvent>()

    override fun onActivityStart() = queryPurchasesIfNotRunning()

    override val lastPaymentEvent: SingleLiveEvent<PaymentEvent>
        get() = _lastPaymentEvent

    private val inAppProductsList =
        inAppSkuList.map {
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId(it)
                .setProductType(BillingClient.ProductType.INAPP)
                .build()
        }
    private val subscriptionsProductsList = subscriptionsSkuList.map {
        QueryProductDetailsParams.Product.newBuilder()
            .setProductId(it)
            .setProductType(BillingClient.ProductType.SUBS)
            .build()
    }

    init {
        connectToPlayBillingService()
    }

    private fun connectToPlayBillingService(): Boolean {
        Timber.d("()")
        if (!gmsBillingClient.isReady && !isConnecting) {
            isConnecting = true
            gmsBillingClient.startConnection(this)
            return true
        }
        return false
    }

    override fun onBillingSetupFinished(billingResult: BillingResult) {
        Timber.d("()")
        isConnecting = false
        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
            billingSetupOk()
        } else {
            Timber.d(billingResult.debugMessage)
            postError(billingResult)
        }
    }

    private fun billingSetupOk() {
        Timber.d(" Response: OK ${inAppSkuList.joinToString("\n")}")
        if (inAppProductsList.isNotEmpty()) querySkuDetailsAsync(inAppProductsList)
        if (subscriptionsProductsList.isNotEmpty()) querySkuDetailsAsync(subscriptionsProductsList)
        queryPurchasesIfNotRunning()
    }

    private fun queryPurchasesIfNotRunning() {
        if (!gmsBillingClient.isReady) {
            connectToPlayBillingService()
            return
        }
        if (!queryStarted) {
            queryStarted = true
            queryPurchasesAsync()
        }
    }

    private fun postError(billingResult: BillingResult) {
        val responseCode = billingResult.responseCode
        val index = if (responseCode < 0) responseCode - 3 else responseCode + 2
        val paymentErrorType = PaymentErrorType.values()
            .getOrElse(index) { PaymentErrorType.DEVELOPER_ERROR }
        postError(paymentErrorType, billingResult.debugMessage)
    }

    private fun postError(paymentErrorType: PaymentErrorType, errorMessage: String = "") {
        val error = PaymentError(errorMessage, paymentErrorType)
        postError(error)
    }

    private fun postError(error: PaymentError) {
        Timber.d("error = [$error]")
        _lastPaymentEvent.postValue(error)
        debugEvent("PAYMENT_ERROR: $error")
        lastError = error
    }

    private fun querySkuDetailsAsync(products: List<QueryProductDetailsParams.Product>) {
        Timber.d("()")
        val params = QueryProductDetailsParams.newBuilder().setProductList(products).build()
        gmsBillingClient.queryProductDetailsAsync(params) { billingResult, productDetailsList ->
            when (billingResult.responseCode) {
                BillingClient.BillingResponseCode.OK -> {
                    Timber.d("productDetailsList ${productDetailsList.joinToString("\n")}")
                    if (productDetailsList.isNotEmpty()) {
                        productDetailsList.forEach {
                            val isNoAd = (it.productId in noAdsInAppSkuList)
                            localCacheBillingClient.skuDetailsDao().insertOrUpdate(it, isNoAd)
                        }
                        this.productDetailsList += productDetailsList
                    }
                }
                else -> {
                    Timber.e(billingResult.debugMessage)
                    postError(billingResult)
                }
            }
        }
    }

    private fun queryPurchasesAsync() {
        Timber.d("()")
        gmsBillingClient.queryPurchasesAsync(
            QueryPurchasesParams.newBuilder()
                .setProductType(BillingClient.ProductType.INAPP)
                .build(),
        ) { _, purchaseList -> processPurchases(purchaseList) }
        if (subscriptionsSkuList.isNotEmpty()) {
            gmsBillingClient.queryPurchasesAsync(
                QueryPurchasesParams.newBuilder()
                    .setProductType(BillingClient.ProductType.SUBS)
                    .build(),
            ) { _, purchaseList -> processPurchases(purchaseList) }
        }
    }

    private fun processPurchases(purchasesResult: List<Purchase>): Job = CoroutineScope(Job() + Dispatchers.IO).launch {
        Timber.d("()")
        try {
            val validPurchases = HashSet<Purchase>(purchasesResult.size)
            purchasesResult.forEach { purchase ->
                Timber.d("purchase = [$purchase]${purchase.purchaseState}")
                if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED) {
                    if (isSignatureValid(purchase)) {
                        validPurchases.add(purchase)
                    }
                } else if (purchase.purchaseState == Purchase.PurchaseState.PENDING) {
                    Timber.d("Received a pending purchase of products: ${purchase.products}")
                    // TODO handle pending purchases, e.g. confirm with users about the pending purchases, prompt them to complete it, etc.
                }
            }
            val (consumables, nonConsumables) =
                validPurchases.partition { consumablesInAppSkuList.contains(it.products.first()) }
            Timber.d("validPurchases content $validPurchases")
            Timber.d("consumables content $consumables")
            Timber.d("non-consumables content $nonConsumables")
            handleConsumablePurchasesAsync(consumables)
            acknowledgeNonConsumablePurchasesAsync(nonConsumables)
            val purchaseDao = localCacheBillingClient.purchaseDao()
            if (!makingPurchaseActive) {
                val forDelete = purchaseDao.getPurchasesList().filter { cachedPurchase ->
                    cachedPurchase.data.products.first() !in validPurchases.map { it.products.first() }
                }.toTypedArray()
                purchaseDao.delete(*forDelete)
            } else {
                makingPurchaseActive = false
            }
            purchaseDao.insert(*validPurchases.toTypedArray())
        } catch (e: Exception) {
            Timber.e(e)
            onDeveloperError(e)
            queryStarted = false
        }
    }

    private fun isSignatureValid(purchase: Purchase): Boolean =
        Security.verifyPurchase(Security.BASE_64_ENCODED_PUBLIC_KEY, purchase.originalJson, purchase.signature)

    private fun handleConsumablePurchasesAsync(consumables: List<Purchase>) {
        Timber.d("consumables = [$consumables]")
        consumables.forEach {
            Timber.d("foreach it is $it")
            val params = ConsumeParams.newBuilder().setPurchaseToken(it.purchaseToken).build()
            gmsBillingClient.consumeAsync(params) { billingResult, purchaseToken ->
                when (billingResult.responseCode) {
                    BillingClient.BillingResponseCode.OK -> {
                        // TODO Update the appropriate tables/databases to grant user the items
                        purchaseToken.apply { }
                    }
                    else -> {
                        Timber.w(billingResult.debugMessage)
                        postError(billingResult)
                    }
                }
            }
        }
    }

    override val ownedPurchasesSkuLD: LiveData<List<String>>
        get() = localCacheBillingClient.purchaseDao().getPurchasesFlow().asLiveData().map { list -> list.map { it.data.products }.flatten() }

    private suspend fun acknowledgeNonConsumablePurchasesAsync(nonConsumables: List<Purchase>) {
        Timber.d("nonConsumables = [$nonConsumables]")
        nonConsumables.forEach { purchase ->
            if (!purchase.isAcknowledged) {
                acknowledgePurchase(purchase)
            } else if (localCacheBillingClient.purchaseDao().get(purchase) == null) {
                val paymentRestored = PaymentRestored(purchase.products.first())
                _lastPaymentEvent.postValue(paymentRestored)
                debugEvent(paymentRestored.toString())
                localCacheBillingClient.purchaseDao().insert(purchase)
                queryStarted = false
            }
        }
    }

    private fun acknowledgePurchase(purchase: Purchase) {
        Timber.d("purchase = [$purchase]")
        val params = AcknowledgePurchaseParams.newBuilder().setPurchaseToken(purchase.purchaseToken).build()
        gmsBillingClient.acknowledgePurchase(params) { billingResult ->
            when (billingResult.responseCode) {
                BillingClient.BillingResponseCode.OK -> onPurchaseAcknowledged(purchase)
                else -> {
                    Timber.d("response is ${billingResult.debugMessage}")
                    postError(billingResult)
                }
            }
            queryStarted = false
        }
    }

    private fun onPurchaseAcknowledged(purchase: Purchase) {
        Timber.d("purchase = [$purchase]")
        val paymentSuccess = PaymentSuccess(purchase.products.first())
        _lastPaymentEvent.postValue(paymentSuccess)
        debugEvent("PAYMENT_SUCCESS: $paymentSuccess")
    }

    private fun debugEvent(message: String) {
        if (isDebugMode) {
            activity.runOnUiThread {
                Toast.makeText(activity, "GSM_PAYMENTS $message", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun makePurchase(activity: Activity, skuId: String) {
        Timber.d("activity = [$activity], skuId = [$skuId]")
        CoroutineScope(Job() + Dispatchers.IO).launch {
            try {
                val netigenNoAdsSkuDetails = localCacheBillingClient.skuDetailsDao().getById(skuId)
                if (netigenNoAdsSkuDetails != null) {
                    launchBillingFlow(activity, netigenNoAdsSkuDetails)
                } else {
                    val lastError1 = lastError
                    when {
                        gmsBillingClient.isReady ->
                            postError(PaymentErrorType.DEVELOPER_ERROR, "NetigenNoAdsSkuDetails with skuId = $skuId not found")
                        lastError1 != null -> postError(lastError1)
                        else -> postError(PaymentErrorType.ERROR, "Unknown error for skuId: $skuId")
                    }
                }
            } catch (e: Exception) {
                Timber.e(e)
                onDeveloperError(e)
            }
        }
    }

    private fun onDeveloperError(e: Exception) = postError(PaymentErrorType.DEVELOPER_ERROR, e.message ?: "")

    private fun launchBillingFlow(activity: Activity, skuDetails: NetigenSkuDetails) {
        Timber.d("activity = [$activity], skuDetails = [$skuDetails]")
        if (productDetailsList.isEmpty()) {
            postError(PaymentErrorType.ERROR, "Product details not loaded")
            return
        }

        val details = productDetailsList.firstOrNull { it.productId == skuDetails.productId } ?: return
        val params =
            listOf(
                BillingFlowParams.ProductDetailsParams.newBuilder()
                    .setProductDetails(details)
                    .build(),
            )

        val purchaseParams = BillingFlowParams.newBuilder().setProductDetailsParamsList(params).build()
        makingPurchaseActive = true
        gmsBillingClient.launchBillingFlow(activity, purchaseParams)
    }

    override fun onPurchasesUpdated(billingResult: BillingResult, purchases: MutableList<Purchase>?) {
        Timber.d("billingResult = [$billingResult], purchases = [$purchases]")
        when (billingResult.responseCode) {
            BillingClient.BillingResponseCode.OK -> {
                Timber.d("${purchases?.joinToString("\n")}")
                CoroutineScope(Job() + Dispatchers.IO).launch {
                    purchases?.apply { processPurchases(this) }
                }
            }
            BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED -> {
                Timber.d(billingResult.debugMessage)
                CoroutineScope(Job() + Dispatchers.IO).launch {
                    queryPurchasesAsync()
                }
                _lastPaymentEvent.postValue(PaymentError(billingResult.debugMessage, PaymentErrorType.ITEM_ALREADY_OWNED))
                debugEvent("ITEM_ALREADY_OWNED " + billingResult.debugMessage)
            }
            BillingClient.BillingResponseCode.SERVICE_DISCONNECTED -> {
                connectToPlayBillingService()
                debugEvent("SERVICE_DISCONNECTED")
            }
            else -> {
                Timber.i(billingResult.debugMessage)
                postError(billingResult)
            }
        }
    }

    override fun onBillingServiceDisconnected() {
        Timber.d("()")
        isConnecting = false
        debugEvent("SERVICE_DISCONNECTED")
    }
}
