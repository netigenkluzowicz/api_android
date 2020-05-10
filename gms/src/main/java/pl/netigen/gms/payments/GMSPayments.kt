package pl.netigen.gms.payments

import android.app.Activity
import android.app.Application
import pl.netigen.coreapi.payments.Payments
import timber.log.Timber.d

class GMSPayments(override val application: Application) : Payments() {

    override val paymentsRepo = GMSPaymentsRepo(application, inAppSkuList, noAdsInAppSkuList)

    override fun makePurchase(activity: Activity, sku: String) {
        d("activity = [$activity], skuString = [$sku]")
        paymentsRepo.makePurchase(activity, sku)

    }

    override fun makeNoAdsPayment(activity: Activity, noAdsSku: String) {
        d("activity = [$activity], noAdsString = [$noAdsSku]")
        if (noAdsSku in noAdsInAppSkuList) makePurchase(activity, noAdsSku) else makePurchase(activity, noAdsInAppSkuList[0])
    }

}