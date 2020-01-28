package pl.netigen.core.main

import android.os.Bundle
import kotlinx.coroutines.flow.collect
import pl.netigen.coreapi.ads.IAds
import pl.netigen.coreapi.main.CoreMainVM
import pl.netigen.coreapi.payments.IPayments
import pl.netigen.extensions.launchMain
import timber.log.Timber.d

open class CoreMainVMImpl(
    private val ads: IAds,
    private val payments: IPayments
) : CoreMainVM(), IAds by ads, IPayments by payments {
    override fun onSavedStateRestored(savedInstanceState: Bundle) {
        d("called")
        launchMain { payments.noAdsActive.collect { if (it) ads.disable() else ads.enable() } }
    }
}