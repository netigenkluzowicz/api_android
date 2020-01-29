package pl.netigen.core.main

import android.app.Application
import kotlinx.coroutines.flow.collect
import pl.netigen.coreapi.ads.IAds
import pl.netigen.coreapi.main.CoreMainVM
import pl.netigen.coreapi.network.INetworkStatus
import pl.netigen.coreapi.payments.IPayments
import pl.netigen.extensions.launchMain
import timber.log.Timber

class CoreMainVmImpl(
    application: Application,
    val ads: IAds,
    val payments: IPayments,
    val networkStatus: INetworkStatus
) : CoreMainVM(application), IPayments by payments, IAds by ads, INetworkStatus by networkStatus {

    override fun onStart() {
        launchMain {
            payments.noAdsActive.collect { onNoAdsChange(it) }
        }
    }

    private fun onNoAdsChange(noAdsActive: Boolean) {
        Timber.d("it = [$noAdsActive]")
        if (noAdsActive) ads.disable() else ads.enable()
    }
}