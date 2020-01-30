package pl.netigen.hms.payments

import android.app.Activity
import android.app.Application
import pl.netigen.coreapi.payments.Payments
import pl.netigen.coreapi.payments.model.NetigenSkuDetails
import timber.log.Timber.d

class HMSPayments(
    application: Application,
    inAppSkuList: List<String> = listOf("${application.packageName}.noads"),
    noAdsInAppSkuList: List<String> = listOf("${application.packageName}.noads"),
    consumablesInAppSkuList: List<String> = emptyList()
) : Payments() {
    override val paymentsRepo =
        HMSPaymentsRepo(application, inAppSkuList, noAdsInAppSkuList, consumablesInAppSkuList)

    override fun makePurchase(activity: Activity, netigenSkuDetails: NetigenSkuDetails) {
        paymentsRepo.launchBillingFlow(activity, netigenSkuDetails)
    }

    override fun makeNoAdsPayment(activity: Activity, noAdsString: String) {
        d("activity = [$activity], noAdsString = [$noAdsString]")
        paymentsRepo.makeNoAdsPurchase(activity, noAdsString)
    }

    // TODO: 30.01.2020
    override fun consumeItem() = Unit

}