package pl.netigen.core.main

import android.app.Application
import kotlinx.coroutines.flow.collect
import pl.netigen.coreapi.ads.IAds
import pl.netigen.coreapi.gdpr.IGDPRConsent
import pl.netigen.coreapi.main.CoreMainVM
import pl.netigen.coreapi.main.IAppConfig
import pl.netigen.coreapi.main.ICoreMainVM
import pl.netigen.coreapi.network.INetworkStatus
import pl.netigen.coreapi.payments.IPayments
import pl.netigen.extensions.MutableSingleLiveEvent
import pl.netigen.extensions.launchMain

/**
 * Current [ICoreMainVM] implementation, provided implementations should be singletons
 *
 * @property ads [IAds] implementation for activity
 * @property payments [IPayments] implementation for application
 * @property networkStatus [INetworkStatus] implementation for application
 *
 * @param application context for [CoreMainVM]
 * @param gdprConsent [IGDPRConsent] implementation for application
 * @param appConfig [IAppConfig] implementation for application
 */
class CoreMainVmImpl(
    application: Application,
    val ads: IAds,
    val payments: IPayments,
    val networkStatus: INetworkStatus,
    gdprConsent: IGDPRConsent,
    appConfig: IAppConfig
) : CoreMainVM(application), IPayments by payments, IAds by ads, INetworkStatus by networkStatus, IGDPRConsent by gdprConsent,
    IAppConfig by appConfig {

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

    override fun onCleared() = CoreViewModelsFactory.cleanAds()
}