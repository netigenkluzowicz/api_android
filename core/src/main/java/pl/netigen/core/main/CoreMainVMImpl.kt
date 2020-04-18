package pl.netigen.core.main

import android.app.Application
import kotlinx.coroutines.flow.collect
import pl.netigen.coreapi.ads.IAds
import pl.netigen.coreapi.gdpr.IGDPRConsent
import pl.netigen.coreapi.main.CoreMainVM
import pl.netigen.coreapi.network.INetworkStatus
import pl.netigen.coreapi.payments.IPayments
import pl.netigen.extensions.MutableSingleLiveEvent
import pl.netigen.extensions.launchMain

class CoreMainVmImpl(
    application: Application,
    val ads: IAds,
    val payments: IPayments,
    val networkStatus: INetworkStatus,
    gdprConsent: IGDPRConsent
) : CoreMainVM(application), IPayments by payments, IAds by ads, INetworkStatus by networkStatus, IGDPRConsent by gdprConsent {

    override fun start() {
        launchMain { payments.noAdsActive.collect { onNoAdsChange(it) } }
    }

    override fun resetAdsPreferences() = showGdprResetAds.postValue(Unit)

    override val showGdprResetAds: MutableSingleLiveEvent<Unit> = MutableSingleLiveEvent()
    override var currentIsNoAdsActive: Boolean = false
        private set

    private fun onNoAdsChange(noAdsActive: Boolean) {
        currentIsNoAdsActive = noAdsActive
        if (noAdsActive) ads.disable() else ads.enable()
    }
}