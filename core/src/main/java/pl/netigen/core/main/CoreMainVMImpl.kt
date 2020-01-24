package pl.netigen.core.main

import kotlinx.coroutines.flow.collect
import pl.netigen.coreapi.ads.IAds
import pl.netigen.coreapi.main.CoreMainVM
import pl.netigen.coreapi.payments.IPayments
import pl.netigen.extensions.launchMain

open class CoreMainVMImpl(
    private val ads: IAds,
    private val payments: IPayments
) : CoreMainVM(), IAds by ads, IPayments by payments {
    override fun onSavedStateRestored() {
        launchMain { payments.noAdsActive.collect { if (it) ads.enable() else ads.disable() } }
    }
}