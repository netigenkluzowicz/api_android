package pl.netigen.hms.payments

import android.app.Activity
import android.content.Intent
import pl.netigen.coreapi.payments.Payments
import timber.log.Timber.d

class HMSPayments(
    activity: Activity,
    inAppSkuList: List<String> = listOf("${activity.packageName}.noads"),
    private val noAdsInAppSkuList: List<String> = listOf("${activity.packageName}.noads"),
    consumablesInAppSkuList: List<String> = emptyList(),
    consumeTestPurchase: Boolean = false
) : Payments() {
    override val paymentsRepo = HMSPaymentsRepo(activity, inAppSkuList, noAdsInAppSkuList, consumablesInAppSkuList, consumeTestPurchase)

    override fun makePurchase(activity: Activity, skuId: String) {
        d("activity = [$activity], skuString = [$skuId]")
        paymentsRepo.makePurchase(activity, skuId)

    }

    override fun makeNoAdsPayment(activity: Activity, noAdsString: String) {
        d("activity = [$activity], noAdsString = [$noAdsString]")
        if (noAdsString in noAdsInAppSkuList) makePurchase(activity, noAdsString) else makePurchase(activity, noAdsInAppSkuList[0])
    }

    //used only in HMS
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) = paymentsRepo.onActivityResult(requestCode, resultCode, data)
}