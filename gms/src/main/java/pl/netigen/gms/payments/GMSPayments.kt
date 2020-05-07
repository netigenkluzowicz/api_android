package pl.netigen.gms.payments

import android.app.Activity
import android.app.Application
import pl.netigen.coreapi.payments.Payments
import timber.log.Timber.d

class GMSPayments(
    application: Application,
    inAppSkuList: List<String> = listOf("${application.packageName}.noads"),
    private val noAdsInAppSkuList: List<String> = listOf("${application.packageName}.noads"),
    consumablesInAppSkuList: List<String> = emptyList()
) : Payments() {
    override val paymentsRepo = GMSPaymentsRepo(application, inAppSkuList, noAdsInAppSkuList, consumablesInAppSkuList)

    override fun makePurchase(activity: Activity, skuId: String) {
        d("activity = [$activity], skuString = [$skuId]")
        paymentsRepo.makePurchase(activity, skuId)

    }

    override fun makeNoAdsPayment(activity: Activity, noAdsString: String) {
        d("activity = [$activity], noAdsString = [$noAdsString]")
        if (noAdsString in noAdsInAppSkuList) makePurchase(activity, noAdsString) else makePurchase(activity, noAdsInAppSkuList[0])
    }

}