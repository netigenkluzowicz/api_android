package pl.netigen.gms.payments

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.android.billingclient.api.AcknowledgePurchaseParams
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.ProductDetails
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.PurchasesUpdatedListener
import com.android.billingclient.api.QueryProductDetailsParams
import com.android.billingclient.api.QueryPurchasesParams
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import pl.netigen.coreapi.payments.IPaymentsRepo
import pl.netigen.coreapi.payments.Security
import pl.netigen.coreapi.payments.model.NetigenSkuDetails
import pl.netigen.coreapi.payments.model.PaymentError
import pl.netigen.coreapi.payments.model.PaymentErrorType
import pl.netigen.coreapi.payments.model.PaymentEvent
import pl.netigen.coreapi.payments.model.PaymentRestored
import pl.netigen.coreapi.payments.model.PaymentSuccess
import pl.netigen.extensions.MutableSingleLiveEvent
import pl.netigen.extensions.SingleLiveEvent
import pl.netigen.gms.payments.PurchaseInfoState.NotStarted
import timber.log.Timber

class GMSPaymentsRepo(
    private val context: Context,
    private val inAppSkuList: List<String>,
    private val noAdsInAppSkuList: List<String>,
    private val subscriptionsSkuList: List<String>,
    private val isDebugMode: Boolean = false,
) : IPaymentsRepo, PurchasesUpdatedListener, BillingClientStateListener {
    private val initialState: PaymentsState by lazy { PaymentsState() }

    private val _state = MutableStateFlow(initialState)
    val paymentsStateFlow: StateFlow<PaymentsState> = _state.asStateFlow()
    private val paymentState: PaymentsState get() = paymentsStateFlow.value
    private fun setState(update: (old: PaymentsState) -> PaymentsState): PaymentsState = _state.updateAndGet(update)

    private var makingPurchaseActive: Boolean = false
    private var isConnecting: Boolean = false
    private var lastError: PaymentError? = null
    private val localCacheBillingClient by lazy { LocalBillingDb.getInstance(context) }
    private val gmsBillingClient: BillingClient = BillingClient.newBuilder(context).enablePendingPurchases().setListener(this).build()
    override val skuDetailsLD by lazy { localCacheBillingClient.skuDetailsDao().skuDetailsLiveData() }

    override val noAdsActive = localCacheBillingClient.purchaseDao().getPurchasesFlow().map { list ->
        list.any { it.data.products.any { s -> s in noAdsInAppSkuList } }
    }

    private val _lastPaymentEvent = MutableSingleLiveEvent<PaymentEvent>()

    override fun onActivityStart() = queryPurchasesIfNotRunning()

    override val lastPaymentEvent: SingleLiveEvent<PaymentEvent>
        get() = _lastPaymentEvent

    private val inAppProductsList = inAppSkuList.map {
        QueryProductDetailsParams.Product.newBuilder().setProductId(it).setProductType(BillingClient.ProductType.INAPP).build()
    }
    private val subscriptionsProductsList = subscriptionsSkuList.map {
        QueryProductDetailsParams.Product.newBuilder().setProductId(it).setProductType(BillingClient.ProductType.SUBS).build()
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
        Timber.d("()")
        if (!gmsBillingClient.isReady) {
            connectToPlayBillingService()
            return
        }
        if (paymentState.purchaseInfoState.isNotRunning()) {
            setState { paymentState.copy(purchaseInfoState = PurchaseInfoState.Started) }
            queryPurchasesAsync()
        }
    }

    private fun postError(billingResult: BillingResult) {
        val responseCode = billingResult.responseCode
        val index = if (responseCode < 0) responseCode - 3 else responseCode + 2
        val paymentErrorType = PaymentErrorType.values().getOrElse(index) { PaymentErrorType.DEVELOPER_ERROR }
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
                        onLoaded(productDetailsList)
                    }
                }

                else -> {
                    Timber.e(billingResult.debugMessage)
                    postError(billingResult)
                }
            }
        }
    }

    private fun onLoaded(productDetailsList: List<ProductDetails>) {
        val newList = paymentState.productDetailsInfoState.productDetails + productDetailsList
        when (paymentState.productDetailsInfoState) {
            ProductDetailsInfoState.NotStarted -> setState { it.copy(productDetailsInfoState = ProductDetailsInfoState.OneLoaded(newList)) }
            ProductDetailsInfoState.Started -> setState { it.copy(productDetailsInfoState = ProductDetailsInfoState.OneLoaded(newList)) }
            else -> setState { it.copy(productDetailsInfoState = ProductDetailsInfoState.BothLoaded(newList)) }
        }
    }

    private fun queryPurchasesAsync() {
        Timber.d("()")
        if (subscriptionsSkuList.isNotEmpty()) {
            gmsBillingClient.queryPurchasesAsync(
                QueryPurchasesParams.newBuilder().setProductType(BillingClient.ProductType.SUBS).build(),
            ) { _, purchaseList -> processPartPurchases(purchaseList) }
        } else {
            setState { it.copy(purchaseInfoState = PurchaseInfoState.OneLoaded(emptyList())) }
        }
        gmsBillingClient.queryPurchasesAsync(
            QueryPurchasesParams.newBuilder().setProductType(BillingClient.ProductType.INAPP).build(),
        ) { _, purchaseList -> processPartPurchases(purchaseList) }
    }

    private fun processPartPurchases(purchasesResult: List<Purchase>) {
        Timber.d("purchasesResult = [$purchasesResult]")
        try {
            val newState = when (val purchaseInfoState = paymentState.purchaseInfoState) {
                is PurchaseInfoState.Started -> PurchaseInfoState.OneLoaded(purchasesResult)
                is PurchaseInfoState.OneLoaded -> {
                    val newList = purchaseInfoState.purchases + purchasesResult
                    processAllPurchases(newList)
                    PurchaseInfoState.BothLoaded(newList)
                }

                else -> {
                    postError(PaymentErrorType.DEVELOPER_ERROR, "Wrong State: $purchaseInfoState")
                    NotStarted
                }
            }
            setState { it.copy(purchaseInfoState = newState) }
        } catch (e: Exception) {
            Timber.e(e)
            onDeveloperError(e)
            setState { it.copy(purchaseInfoState = NotStarted) }
        }
    }

    private fun processAllPurchases(purchases: List<Purchase>): Job = CoroutineScope(Job() + Dispatchers.IO).launch {
        Timber.d("purchases = [$purchases]")
        val validPurchases = HashSet<Purchase>(purchases.size)
        purchases.forEach { purchase ->
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
        val valid = validPurchases.toList()
        acknowledgeNonConsumablePurchasesAsync(valid)
        val purchaseDao = localCacheBillingClient.purchaseDao()
        purchaseDao.insert(*validPurchases.toTypedArray())
        val forDelete = purchaseDao.getPurchasesList().filter { cachedPurchase ->
            shouldDelete(cachedPurchase, validPurchases)
        }.toTypedArray()
        purchaseDao.delete(*forDelete)
        setState { it.copy(purchaseInfoState = PurchaseInfoState.Processed(valid)) }
    }

    private fun shouldDelete(
        cachedPurchase: CachedPurchase,
        validPurchases: HashSet<Purchase>,
    ) = cachedPurchase.data.products.first() !in validPurchases.map { it.products.first() }

    private fun isSignatureValid(purchase: Purchase): Boolean =
        Security.verifyPurchase(Security.BASE_64_ENCODED_PUBLIC_KEY, purchase.originalJson, purchase.signature)

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
            MainScope().launch {
                Toast.makeText(context, "GSM_PAYMENTS $message", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun purchaseOffer(activity: Activity, productDetails: ProductDetails, offerToken: String) {
        Timber.d("xxx.+activity = [$activity], productDetails = [$productDetails], offerToken = [$offerToken]")
        val params = listOf(BillingFlowParams.ProductDetailsParams.newBuilder().setProductDetails(productDetails).setOfferToken(offerToken).build())
        val purchaseParams = BillingFlowParams.newBuilder().setProductDetailsParamsList(params).build()
        makingPurchaseActive = true
        gmsBillingClient.launchBillingFlow(activity, purchaseParams)
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
                        gmsBillingClient.isReady -> postError(
                            PaymentErrorType.DEVELOPER_ERROR,
                            "NetigenNoAdsSkuDetails with skuId = $skuId not found",
                        )

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
        val productDetails = paymentState.productDetailsInfoState.productDetails
        if (productDetails.isEmpty()) {
            postError(PaymentErrorType.ERROR, "Product details not loaded")
            return
        }

        val details = productDetails.firstOrNull { it.productId == skuDetails.productId } ?: return
        val offerToken = details.subscriptionOfferDetails?.lastOrNull()?.offerToken
        val params = if (offerToken != null) {
            listOf(BillingFlowParams.ProductDetailsParams.newBuilder().setProductDetails(details).setOfferToken(offerToken).build())
        } else {
            listOf(BillingFlowParams.ProductDetailsParams.newBuilder().setProductDetails(details).build())
        }

        val purchaseParams = BillingFlowParams.newBuilder().setProductDetailsParamsList(params).build()
        makingPurchaseActive = true
        gmsBillingClient.launchBillingFlow(activity, purchaseParams)
    }

    override fun onPurchasesUpdated(billingResult: BillingResult, purchases: MutableList<Purchase>?) {
        Timber.d("billingResult = [$billingResult], purchases = [$purchases]")
        when (billingResult.responseCode) {
            BillingClient.BillingResponseCode.OK -> {
                Timber.d("${purchases?.joinToString("\n")}")
                CoroutineScope(Job() + Dispatchers.IO).launch { purchases?.apply { processAllPurchases(this) } }
            }

            BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED -> {
                Timber.d(billingResult.debugMessage)
                CoroutineScope(Job() + Dispatchers.IO).launch { queryPurchasesAsync() }
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
