package pl.netigen.core.main

import android.app.Application
import android.os.Bundle
import kotlinx.coroutines.flow.collect
import pl.netigen.coreapi.ads.IAds
import pl.netigen.coreapi.main.CoreMainVM
import pl.netigen.coreapi.payments.IPayments
import pl.netigen.extensions.launchMain
import timber.log.Timber.d

class CoreMainVmImpl(
    application: Application,
    val ads: IAds,
    val payments: IPayments
) : CoreMainVM(application), IPayments by payments, IAds by ads {
    override fun onSavedStateRestored(savedInstanceState: Bundle) {
        d("called")
        launchMain { payments.noAdsActive.collect { if (it) ads.disable() else ads.enable() } }
    }
}