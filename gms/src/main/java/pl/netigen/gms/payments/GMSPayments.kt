package pl.netigen.gms.payments

import android.app.Activity
import android.app.Application
import pl.netigen.coreapi.payments.Payments
import pl.netigen.coreapi.payments.model.NetigenSkuDetails
import timber.log.Timber.d

class GMSPayments(application: Application, inAppSkuList: List<String> = listOf("${application.packageName}.noads")) : Payments() {
    override val paymentsRepo = GMSPaymentsRepo(application, inAppSkuList)

    override fun makePurchase(activity: Activity, netigenSkuDetails: NetigenSkuDetails) {
        paymentsRepo.launchBillingFlow(activity, netigenSkuDetails)
    }

    override fun makeNoAdsPayment(activity: Activity, noAdsString: String) {
        d(noAdsString)
        paymentsRepo.makeNoAdsPurchase(activity)
    }

    override fun consumeItem() {
    }

}