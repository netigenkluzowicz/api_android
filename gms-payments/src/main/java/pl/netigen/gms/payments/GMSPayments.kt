package pl.netigen.gms.payments

import android.app.Activity
import android.content.Context
import com.android.billingclient.api.ProductDetails
import pl.netigen.coreapi.main.IAppConfig
import pl.netigen.coreapi.payments.Payments
import timber.log.Timber.Forest.d


class GMSPayments(
    private val context: Context,
    override val inAppSkuList: List<String> = listOf("${context.packageName}.noads"),
    override val noAdsInAppSkuList: List<String> = listOf("${context.packageName}.noads"),
    override val subscriptionsSkuList: List<String> = emptyList(),
    inDebugMode: Boolean = false,
    appConfig: IAppConfig,
) : Payments(context) {
    private val donateAddedList =
        inAppSkuList + "${context.packageName}.donate1" + "${context.packageName}.donate2" + "${context.packageName}.donate3"

    private val donateAddedNoAdsList = noAdsInAppSkuList + "${context.packageName}.donate3"

    override val paymentsRepo = GMSPaymentsRepo(
        context = context,
        inAppSkuList = if (appConfig.donateActive) donateAddedList else inAppSkuList,
        noAdsInAppSkuList = if (appConfig.donateActive) donateAddedNoAdsList else noAdsInAppSkuList,
        subscriptionsSkuList = subscriptionsSkuList,
        isDebugMode = inDebugMode,
    )
    override val paymentsStateFlow = paymentsRepo.paymentsStateFlow

    override fun makePurchase(activity: Activity, sku: String) {
        d("activity = [$activity], skuString = [$sku]")
        paymentsRepo.makePurchase(activity, sku)
    }

    override fun purchaseSubscriptionOffer(activity: Activity, productDetails: Any, subscriptionOfferToken: String) {
        d("xxx.+activity = [$activity], productDetails = [$productDetails], offerToken = [$subscriptionOfferToken]")
        paymentsRepo.purchaseOffer(activity, productDetails as ProductDetails, subscriptionOfferToken)
    }

    override fun makeNoAdsPayment(activity: Activity, noAdsSku: String) {
        d("activity = [$activity], noAdsString = [$noAdsSku]")
        if (noAdsSku in donateAddedNoAdsList) makePurchase(activity, noAdsSku) else makePurchase(activity, donateAddedNoAdsList[0])
    }
}
