package pl.netigen.amazon.payments

import android.app.Activity
import pl.netigen.coreapi.payments.Payments
import timber.log.Timber.Forest.d

class AmazonPayments(
    activity: Activity,
    override val inAppSkuList: List<String> = listOf("${activity.packageName}.noads"),
    override val noAdsInAppSkuList: List<String> = listOf("${activity.packageName}.noads"),
    override val subscriptionsSkuList: List<String> = emptyList(),
) : Payments(activity) {

    override val paymentsRepo = AmazonPaymentsRepo(activity, inAppSkuList, noAdsInAppSkuList)

    override fun makePurchase(activity: Activity, sku: String) {
        d("activity = [$activity], skuString = [$sku]")
        paymentsRepo.makePurchase(sku)
    }

    override val paymentsStateFlow: Any = Unit
    override fun purchaseSubscriptionOffer(activity: Activity, productDetails: Any, subscriptionOfferToken: String) = Unit
    override fun makeNoAdsPayment(activity: Activity, noAdsSku: String) {
        d("activity = [$activity], noAdsString = [$noAdsSku]")
        if (noAdsSku in noAdsInAppSkuList) makePurchase(activity, noAdsSku) else makePurchase(activity, noAdsInAppSkuList[0])
    }
}
