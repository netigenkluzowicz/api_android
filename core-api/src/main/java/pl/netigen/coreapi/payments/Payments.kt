package pl.netigen.coreapi.payments

import android.app.Application
import android.content.Intent
import androidx.lifecycle.LiveData

abstract class Payments : IPayments {
    abstract val application: Application

    final override val packageName: String
        get() = application.packageName

    override val noAdsInAppSkuList: List<String> = listOf("${packageName}.noads")

    override val inAppSkuList: List<String> = listOf("${packageName}.noads")

    abstract val paymentsRepo: IPaymentsRepo

    override val ownedPurchasesSkuLD: LiveData<List<String>> by lazy { paymentsRepo.ownedPurchasesSkuLD }
    override val noAdsActive by lazy { paymentsRepo.noAdsActive }
    override val inAppSkuDetailsLD by lazy { paymentsRepo.inAppSkuDetailsLD }
    override val subsSkuDetailsLD by lazy { paymentsRepo.subsSkuDetailsLD }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) = Unit
}
