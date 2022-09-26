package pl.netigen.hms.payments

import android.app.Activity
import android.content.Intent
import pl.netigen.coreapi.payments.Payments
import timber.log.Timber.Forest.d

class HMSPayments(
    activity: Activity,
    override val inAppSkuList: List<String> = listOf("${activity.packageName}.noads"),
    override val noAdsInAppSkuList: List<String> = listOf("${activity.packageName}.noads"),
    override val subscriptionsSkuList: List<String> = emptyList(),
    consumeTestPurchase: Boolean = false,
) : Payments(activity) {

    override val paymentsRepo = HMSPaymentsRepo(activity, inAppSkuList, noAdsInAppSkuList, consumeTestPurchase)

    override fun makePurchase(activity: Activity, sku: String) {
        d("activity = [$activity], skuString = [$sku]")
        paymentsRepo.makePurchase(activity, sku)
    }

    override fun makeNoAdsPayment(activity: Activity, noAdsSku: String) {
        d("activity = [$activity], noAdsString = [$noAdsSku]")
        if (noAdsSku in noAdsInAppSkuList) makePurchase(activity, noAdsSku) else makePurchase(activity, noAdsInAppSkuList[0])
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) = paymentsRepo.onActivityResult(requestCode, resultCode, data)
}
