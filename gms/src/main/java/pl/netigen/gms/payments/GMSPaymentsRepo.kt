package pl.netigen.gms.payments

import android.app.Activity
import android.app.Application
import com.android.billingclient.api.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import pl.netigen.coreapi.payments.IPaymentsRepo
import pl.netigen.coreapi.payments.Security
import pl.netigen.coreapi.payments.model.NetigenSkuDetails
import timber.log.Timber
import java.util.*

class GMSPaymentsRepo(

    private val application: Application,
    private val inAppSkuList: List<String>

) : IPaymentsRepo, PurchasesUpdatedListener, BillingClientStateListener {

    private val localCacheBillingClient by lazy { LocalBillingDb.getInstance(application) }

    private val gmsBillingClient: BillingClient = BillingClient
        .newBuilder(application.applicationContext)
        .enablePendingPurchases()
        .setListener(this)
        .build()

    override val inAppSkuDetails by lazy { localCacheBillingClient.skuDetailsDao().inAppSkuDetailsLiveData() }
    override val subsSkuDetails by lazy { localCacheBillingClient.skuDetailsDao().subscriptionSkuDetailsLiveData() }

    override val noAdsActive by lazy { noAdsFlow() }

    private fun noAdsFlow(): Flow<Boolean> {
        return flow {
            localCacheBillingClient.purchaseDao().getPurchasesFlow().collect { list ->
                emit(list.count { it.isNoAds } > 0)
            }
        }
    }

    init {
        connectToPlayBillingService()
    }

    private fun connectToPlayBillingService(): Boolean {
        Timber.d("called")
        if (!gmsBillingClient.isReady) {
            gmsBillingClient.startConnection(this)
            return true
        }
        return false
    }

    //TODO should be called when Payments is being destroyed
    fun endDataSourceConnections() {
        Timber.d("")
        gmsBillingClient.endConnection()
        localCacheBillingClient.close()
    }

    override fun onBillingSetupFinished(billingResult: BillingResult) {
        Timber.d("")
        when (billingResult.responseCode) {
            BillingClient.BillingResponseCode.OK -> {
                Timber.d(" Response: OK ${inAppSkuList.joinToString("\n")}")
                querySkuDetailsAsync(BillingClient.SkuType.INAPP, inAppSkuList)
                //TODO we should decide wether we want to deal with subs sku separately, but it seems like a right way to me
                //querySkuDetailsAsync(BillingClient.SkuType.SUBS, subSkuList)
                queryPurchasesAsync()
            }
            BillingClient.BillingResponseCode.BILLING_UNAVAILABLE -> {
                Timber.d(billingResult.debugMessage)
                //TODO We need to decide whether we want to act upon this callback otherwise it could be deleted
            }
            else -> {
                Timber.d(billingResult.debugMessage)
            }
        }
    }

    private fun querySkuDetailsAsync(@BillingClient.SkuType skuType: String, skuList: List<String>) {
        Timber.d("${skuList.joinToString("\n")} skuType $skuType")
        val params = SkuDetailsParams.newBuilder().setSkusList(skuList).setType(skuType).build()
        gmsBillingClient.querySkuDetailsAsync(params) { billingResult, skuDetailsList ->
            when (billingResult.responseCode) {
                BillingClient.BillingResponseCode.OK -> {
                    Timber.d("skuDetailsList ${skuDetailsList.joinToString("\n")}")
                    if (!skuDetailsList.isNullOrEmpty()) {
                        skuDetailsList.forEach {
                            CoroutineScope(Job() + Dispatchers.IO).launch {
                                Timber.d("inserting $it")
                                localCacheBillingClient.skuDetailsDao().insertOrUpdate(it)
                            }
                        }
                    }
                }
                else -> {
                    Timber.e(billingResult.debugMessage)
                }
            }
        }
    }

    private fun queryPurchasesAsync() {
        val purchasesResult = HashSet<Purchase>()
        var result = gmsBillingClient.queryPurchases(BillingClient.SkuType.INAPP)
        Timber.d(" INAPP results: ${result?.purchasesList?.size})")
        result?.purchasesList?.apply { purchasesResult.addAll(this) }
        if (isSubscriptionSupported()) {
            result = gmsBillingClient.queryPurchases(BillingClient.SkuType.SUBS)
            result?.purchasesList?.apply { purchasesResult.addAll(this) }
            Timber.d("SUBS results: ${result?.purchasesList?.size}")
        }
        processPurchases(purchasesResult)
    }

    private fun isSubscriptionSupported(): Boolean {
        val billingResult =
            gmsBillingClient.isFeatureSupported(BillingClient.FeatureType.SUBSCRIPTIONS)
        var succeeded = false
        when (billingResult.responseCode) {
            BillingClient.BillingResponseCode.SERVICE_DISCONNECTED -> connectToPlayBillingService()
            BillingClient.BillingResponseCode.OK -> succeeded = true
            else ->
                Timber.w(" error: ${billingResult.debugMessage}")
        }
        return succeeded
    }

    private fun processPurchases(purchasesResult: Set<Purchase>) =
        CoroutineScope(Job() + Dispatchers.IO).launch {
            Timber.d("")
            val validPurchases = HashSet<Purchase>(purchasesResult.size)
            Timber.d("newBatch content $purchasesResult")
            purchasesResult.forEach { purchase ->
                if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED) {
                    if (isSignatureValid(purchase)) {
                        validPurchases.add(purchase)
                    }
                } else if (purchase.purchaseState == Purchase.PurchaseState.PENDING) {
                    Timber.d("Received a pending purchase of SKU: ${purchase.sku}")
                    //TODO handle pending purchases, e.g. confirm with users about the pending purchases, prompt them to complete it, etc.
                }
            }
            val (consumables, nonConsumables) = validPurchases.partition {
                inAppSkuList.contains(it.sku)
            }
            Timber.d("consumables content $consumables")
            Timber.d("non-consumables content $nonConsumables")
            val testing = localCacheBillingClient.purchaseDao().getPurchasesFlow()
            testing.collect {
                Timber.d("testing inserted purchases size: ${it.size}")
            }
            localCacheBillingClient.purchaseDao().insert(*validPurchases.toTypedArray())
            handleConsumablePurchasesAsync(consumables)
            acknowledgeNonConsumablePurchasesAsync(nonConsumables)
        }

    private fun isSignatureValid(purchase: Purchase): Boolean {
        return Security.verifyPurchase(
            Security.BASE_64_ENCODED_PUBLIC_KEY, purchase.originalJson, purchase.signature
        )
    }

    private fun handleConsumablePurchasesAsync(consumables: List<Purchase>) {
        Timber.d("called")
        consumables.forEach {
            Timber.d("foreach it is $it")
            val params =
                ConsumeParams.newBuilder().setPurchaseToken(it.purchaseToken).build()
            gmsBillingClient.consumeAsync(params) { billingResult, purchaseToken ->
                when (billingResult.responseCode) {
                    BillingClient.BillingResponseCode.OK -> {
                        //TODO Update the appropriate tables/databases to grant user the items
                        purchaseToken.apply { }
                    }
                    else -> {
                        Timber.w(billingResult.debugMessage)
                    }
                }
            }
        }
    }

    private fun acknowledgeNonConsumablePurchasesAsync(nonConsumables: List<Purchase>) {
        nonConsumables.forEach { purchase ->
            val params = AcknowledgePurchaseParams.newBuilder().setPurchaseToken(
                purchase
                    .purchaseToken
            ).build()
            gmsBillingClient.acknowledgePurchase(params) { billingResult ->
                when (billingResult.responseCode) {
                    BillingClient.BillingResponseCode.OK -> {
                        disburseNonConsumableEntitlement(purchase)
                    }
                    else -> Timber.d("response is ${billingResult.debugMessage}")
                }
            }
        }
    }

    //TODO here's a method that we might use to give user certain entitlements
    private fun disburseNonConsumableEntitlement(purchase: Purchase) =
        CoroutineScope(Job() + Dispatchers.IO).launch {

        }

    fun makeNoAdsPurchase(activity: Activity, noAdsString: String = "${activity.packageName}.noads") {
        CoroutineScope(Job() + Dispatchers.IO).launch {
            val netigenNoAdsSkuDetails = localCacheBillingClient.skuDetailsDao().getById(noAdsString)
            Timber.d("netigenSkuDetails for noads: $netigenNoAdsSkuDetails")
            netigenNoAdsSkuDetails?.let {
                launchBillingFlow(activity, it)
            }
        }
    }

    fun launchBillingFlow(activity: Activity, netigenSkuDetails: NetigenSkuDetails) {
        //an error here would mean that you don't have a proper sku set, or your netigenSkuDetails for your sku is not yet in db
        Timber.d("launching billing flow")
        netigenSkuDetails.originalJson?.let {
            launchBillingFlow(activity, SkuDetails(it))
        } ?: throw IllegalStateException("SkuDetail doesn't contain original json, you should first fetch it from db")
    }

    fun launchBillingFlow(activity: Activity, skuDetails: SkuDetails) {
        val purchaseParams = BillingFlowParams.newBuilder().setSkuDetails(skuDetails)
            .build()
        gmsBillingClient.launchBillingFlow(activity, purchaseParams)
    }

    override fun onPurchasesUpdated(
        billingResult: BillingResult,
        purchases: MutableList<Purchase>?
    ) {
        when (billingResult.responseCode) {
            BillingClient.BillingResponseCode.OK -> {
                Timber.d("${purchases?.joinToString("\n")}")
                purchases?.apply { processPurchases(this.toSet()) }
            }
            BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED -> {
                Timber.d(billingResult.debugMessage)
                queryPurchasesAsync()
            }
            BillingClient.BillingResponseCode.SERVICE_DISCONNECTED -> {
                connectToPlayBillingService()
            }
            else -> {
                Timber.i(billingResult.debugMessage)
            }
        }
    }

    override fun onBillingServiceDisconnected() {
        Timber.d("")
        connectToPlayBillingService()
    }
}