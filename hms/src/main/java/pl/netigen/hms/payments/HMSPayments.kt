package pl.netigen.hms.payments

import android.app.Activity
import android.content.Intent
import pl.netigen.coreapi.payments.Payments
import pl.netigen.coreapi.payments.model.NetigenSkuDetails

class HMSPayments(
    activity: Activity,
    inAppSkuList: List<String> = listOf("${activity.packageName}.noads"),
    private val noAdsInAppSkuList: List<String> = listOf("${activity.packageName}.noads"),
    consumablesInAppSkuList: List<String> = emptyList(),
    consumeTestPurchase: Boolean = false
) : Payments() {
    override val paymentsRepo = HMSPaymentsRepo(activity, inAppSkuList, noAdsInAppSkuList, consumablesInAppSkuList, consumeTestPurchase)

    override fun makePurchase(activity: Activity, netigenSkuDetails: NetigenSkuDetails) = Unit

    override fun makeNoAdsPayment(activity: Activity, noAdsString: String) {
        if (noAdsString in noAdsInAppSkuList) {
            paymentsRepo.makeNoAdsPurchase(activity, noAdsString)
        } else {
            paymentsRepo.makeNoAdsPurchase(activity, noAdsInAppSkuList[0])
        }
    }

    //used only in HMS
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) = paymentsRepo.onActivityResult(requestCode, resultCode, data)
}