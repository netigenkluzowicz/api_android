package pl.netigen.coreapi.payments

import android.app.Application

abstract class Payments(private val application: Application) : INoAds, IPayments {

    abstract val paymentsRepo: IPaymentsRepo
//INoAds
    override val noAdsActive by lazy { paymentsRepo.noAdsActive }
//IPayments
    override val inAppSkuDetails by lazy { paymentsRepo.inAppSkuDetails }
    override val subsSkuDetails by lazy { paymentsRepo.subsSkuDetails }
}
