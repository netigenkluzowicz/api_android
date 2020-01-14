package pl.netigen.gms.payments

import android.app.Activity
import android.app.Application
import pl.netigen.coreapi.payments.Payments
import pl.netigen.coreapi.payments.model.NetigenSkuDetails

class GMSPayments(application: Application, inAppSkuList: List<String>) : Payments() {

    override val paymentsRepo = GMSPaymentsRepo(application, inAppSkuList)

    override fun makePurchase(activity: Activity, netigenSkuDetails: NetigenSkuDetails) {
        paymentsRepo.launchBillingFlow(activity, netigenSkuDetails)
    }

    override fun makeNoAdsPayment(activity: Activity, noAdsString: String) {
        paymentsRepo.makeNoAdsPurchase(activity)
    }

    override fun consumeItem() {
    }

}