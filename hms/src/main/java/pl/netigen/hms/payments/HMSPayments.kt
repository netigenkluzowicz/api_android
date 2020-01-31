package pl.netigen.hms.payments

import android.app.Activity
import android.content.Intent
import pl.netigen.coreapi.payments.Payments
import pl.netigen.coreapi.payments.model.NetigenSkuDetails
import timber.log.Timber.d

class HMSPayments(
    activity: Activity,
    inAppSkuList: List<String> = listOf("${activity.packageName}.noads"),
    noAdsInAppSkuList: List<String> = listOf("${activity.packageName}.noads"),
    consumablesInAppSkuList: List<String> = emptyList()
) : Payments() {
    override val paymentsRepo = HMSPaymentsRepo(activity, inAppSkuList, noAdsInAppSkuList, consumablesInAppSkuList)

    override fun makePurchase(activity: Activity, netigenSkuDetails: NetigenSkuDetails) = Unit

    override fun makeNoAdsPayment(activity: Activity, noAdsString: String) {
        d("activity = [$activity], noAdsString = [$noAdsString]")
        paymentsRepo.makeNoAdsPurchase(activity, noAdsString)
    }

    //used only in HMS
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) = paymentsRepo.onActivityResult(requestCode, resultCode, data)
}