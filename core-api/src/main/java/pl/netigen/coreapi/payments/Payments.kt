package pl.netigen.coreapi.payments

import android.content.Intent
import androidx.lifecycle.LiveData

abstract class Payments : IPayments {
    abstract val paymentsRepo: IPaymentsRepo
    override val ownedPurchases: LiveData<List<String>> by lazy { paymentsRepo.ownedPurchases }
    override val noAdsActive by lazy { paymentsRepo.noAdsActive }
    override val inAppSkuDetails by lazy { paymentsRepo.inAppSkuDetails }
    override val subsSkuDetails by lazy { paymentsRepo.subsSkuDetails }
    override fun endConnection() = paymentsRepo.endConnection()
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) = Unit
}
