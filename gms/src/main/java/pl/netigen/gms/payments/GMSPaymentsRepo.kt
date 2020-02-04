package pl.netigen.gms.payments

import android.app.Activity
import android.app.Application
import com.android.billingclient.api.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import pl.netigen.coreapi.payments.IPaymentsRepo
import pl.netigen.coreapi.payments.Security
import pl.netigen.coreapi.payments.model.NetigenSkuDetails
import timber.log.Timber
import java.util.*

class GMSPaymentsRepo(
    private val application: Application,
    private val inAppSkuList: List<String>,
    private val noAdsInAppSkuList: List<String>,
    private val consumablesInAppSkuList: List<String> = emptyList()
) : IPaymentsRepo, PurchasesUpdatedListener, BillingClientStateListener {
    private val localCacheBillingClient by lazy { LocalBillingDb.getInstance(application) }
    private val gmsBillingClient: BillingClient = BillingClient
        .newBuilder(application.applicationContext)
        .enablePendingPurchases()
        .setListener(this)
        .build()
    override val inAppSkuDetails by lazy { localCacheBillingClient.skuDetailsDao().inAppSkuDetailsLiveData() }
    override val subsSkuDetails by lazy { localCacheBillingClient.skuDetailsDao().subscriptionSkuDetailsLiveData() }

    override val noAdsActive = localCacheBillingClient.purchaseDao().getPurchasesFlow()
        .map { list -> list.any { it.data.sku in noAdsInAppSkuList } }

    init {
        connectToPlayBillingService()
    }

    private fun connectToPlayBillingService(): Boolean {
        Timber.d("()")
        if (!gmsBillingClient.isReady) {
            gmsBillingClient.startConnection(this)
            return true
        }
        return false
    }

    override fun endConnection() {
        Timber.d("()")
        gmsBillingClient.endConnection()
        localCacheBillingClient.close()
    }

    override fun onBillingSetupFinished(billingResult: BillingResult) {
        Timber.d("()")
        when (billingResult.responseCode) {
            BillingClient.BillingResponseCode.OK -> {
                Timber.d(" Response: OK ${inAppSkuList.joinToString("\n")}")
                querySkuDetailsAsync(BillingClient.SkuType.INAPP, inAppSkuList)
                //TODO we should decide whether we want to deal with subs sku separately, but it seems like a right way to me
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
                                try {
                                    Timber.d("inserting $it")
                                    val isNoAd = (it.sku in noAdsInAppSkuList)
                                    localCacheBillingClient.skuDetailsDao().insertOrUpdate(it, isNoAd)
                                } catch (e: Exception) {
                                    Timber.e(e)
                                }
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
        Timber.d("()")
        val purchasesResult = HashSet<Purchase>()
        var result = gmsBillingClient.queryPurchases(BillingClient.SkuType.INAPP)
        Timber.d(" INAPP results: ${result.purchasesList?.size})")
        result.purchasesList?.apply { purchasesResult.addAll(this) }
        if (isSubscriptionSupported()) {
            result = gmsBillingClient.queryPurchases(BillingClient.SkuType.SUBS)
            result.purchasesList?.apply { purchasesResult.addAll(this) }
            Timber.d("SUBS results: ${result.purchasesList?.size}")
        }
        processPurchases(purchasesResult)
    }

    private fun isSubscriptionSupported(): Boolean {
        val billingResult = gmsBillingClient.isFeatureSupported(BillingClient.FeatureType.SUBSCRIPTIONS)
        var succeeded = false
        when (billingResult.responseCode) {
            BillingClient.BillingResponseCode.SERVICE_DISCONNECTED -> connectToPlayBillingService()
            BillingClient.BillingResponseCode.OK -> succeeded = true
            else ->
                Timber.w(" error: ${billingResult.debugMessage}")
        }
        return succeeded
    }

    private fun processPurchases(purchasesResult: Set<Purchase>): Job = CoroutineScope(Job() + Dispatchers.IO).launch {
        try {
            val validPurchases = HashSet<Purchase>(purchasesResult.size)
            purchasesResult.forEach { purchase ->
                if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED) {
                    if (isSignatureValid(purchase)) {
                        Timber.d("purchase = [$purchase]${purchase.purchaseState}")
                        validPurchases.add(purchase)
                    }
                } else if (purchase.purchaseState == Purchase.PurchaseState.PENDING) {
                    Timber.d("Received a pending purchase of SKU: ${purchase.sku}")
                    //TODO handle pending purchases, e.g. confirm with users about the pending purchases, prompt them to complete it, etc.
                }
            }
            val (consumables, nonConsumables)
                    = validPurchases.partition { consumablesInAppSkuList.contains(it.sku) }
            Timber.d("validPurchases content $validPurchases")
            Timber.d("consumables content $consumables")
            Timber.d("non-consumables content $nonConsumables")
            localCacheBillingClient.purchaseDao().insert(*validPurchases.toTypedArray())
            handleConsumablePurchasesAsync(consumables)
            acknowledgeNonConsumablePurchasesAsync(nonConsumables)
        } catch (e: Exception) {
            Timber.e(e)
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
                        //TODO Update the appropriate tables/databases to grant user the items
                        purchaseToken.apply { }
                    }
                    else -> Timber.w(billingResult.debugMessage)
                }
            }
        }
    }

    private fun acknowledgeNonConsumablePurchasesAsync(nonConsumables: List<Purchase>) {
        Timber.d("nonConsumables = [$nonConsumables]")
        nonConsumables.forEach { purchase ->
            val params = AcknowledgePurchaseParams.newBuilder().setPurchaseToken(purchase.purchaseToken).build()
            gmsBillingClient.acknowledgePurchase(params) { billingResult ->
                when (billingResult.responseCode) {
                    BillingClient.BillingResponseCode.OK -> disburseNonConsumableEntitlement(purchase)
                    else -> Timber.d("response is ${billingResult.debugMessage}")
                }
            }
        }
    }

    //TODO here's a method that we might use to give user certain entitlements
    private fun disburseNonConsumableEntitlement(purchase: Purchase) = Unit

    fun makeNoAdsPurchase(activity: Activity, noAdsString: String = "${activity.packageName}.noads") {
        CoroutineScope(Job() + Dispatchers.IO).launch {
            try {
                val netigenNoAdsSkuDetails = localCacheBillingClient.skuDetailsDao().getById(noAdsString)
                Timber.d("netigenSkuDetails for noads: $netigenNoAdsSkuDetails")
                netigenNoAdsSkuDetails?.let {
                    launchBillingFlow(activity, it)
                }
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    fun launchBillingFlow(activity: Activity, netigenSkuDetails: NetigenSkuDetails) {
        Timber.d("launching billing flow")
        netigenSkuDetails.originalJson?.let { launchBillingFlow(activity, SkuDetails(it)) }
            ?: throw IllegalStateException("SkuDetail doesn't contain original json, you should first fetch it from db")
    }

    fun launchBillingFlow(activity: Activity, skuDetails: SkuDetails) {
        Timber.d("activity = [$activity], skuDetails = [$skuDetails]")
        val purchaseParams = BillingFlowParams.newBuilder().setSkuDetails(skuDetails).build()
        gmsBillingClient.launchBillingFlow(activity, purchaseParams)
    }

    override fun onPurchasesUpdated(
        billingResult: BillingResult,
        purchases: MutableList<Purchase>?
    ) {
        Timber.d("billingResult = [$billingResult], purchases = [$purchases]")
        when (billingResult.responseCode) {
            BillingClient.BillingResponseCode.OK -> {
                Timber.d("${purchases?.joinToString("\n")}")
                purchases?.apply { processPurchases(this.toSet()) }
            }
            BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED -> {
                Timber.d(billingResult.debugMessage)
                queryPurchasesAsync()
            }
            BillingClient.BillingResponseCode.SERVICE_DISCONNECTED -> connectToPlayBillingService()
            else -> Timber.i(billingResult.debugMessage)
        }
    }

    override fun onBillingServiceDisconnected() {
        Timber.d("()")
        connectToPlayBillingService()
    }
}