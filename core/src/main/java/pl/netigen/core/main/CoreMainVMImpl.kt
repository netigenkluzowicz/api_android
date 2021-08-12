package pl.netigen.core.main

import android.app.Application
import androidx.annotation.CallSuper
import kotlinx.coroutines.flow.collect
import pl.netigen.coreapi.ads.IAds
import pl.netigen.coreapi.gdpr.IGDPRConsent
import pl.netigen.coreapi.main.CoreMainVM
import pl.netigen.coreapi.main.IAppConfig
import pl.netigen.coreapi.main.ICoreMainVM
import pl.netigen.coreapi.main.Store
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
open class CoreMainVmImpl(
    application: Application,
    val ads: IAds,
    val payments: IPayments,
    val networkStatus: INetworkStatus,
    val gdprConsent: IGDPRConsent,
    val appConfig: IAppConfig
) : CoreMainVM(application), IPayments by payments, IAds by ads, INetworkStatus by networkStatus, IGDPRConsent by gdprConsent,
    IAppConfig by appConfig {

    @CallSuper
    override fun start() {
        launchMain { payments.noAdsActive.collect { onNoAdsChange(it) } }
        payments.onActivityStart()
    }

    final override fun resetAdsPreferences() {
        if (appConfig.store == Store.HUAWEI) {
            showGdprResetAds.postValue(Unit)
        } else {
            gdprConsent.loadGdpr {
                if (it) {
                    gdprConsent.showGdpr { adConsentStatus ->
                        saveAdConsentStatus(adConsentStatus)
                    }
                }
            }
        }
    }

    final override val showGdprResetAds: MutableSingleLiveEvent<Unit> = MutableSingleLiveEvent()
    final override var currentIsNoAdsActive: Boolean = false
        private set

    private fun onNoAdsChange(noAdsActive: Boolean) {
        currentIsNoAdsActive = noAdsActive
        if (noAdsActive) ads.disable() else ads.enable()
    }

    @CallSuper
    override fun onCleared() = CoreViewModelsFactory.cleanAds()
}