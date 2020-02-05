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
import timber.log.Timber.e

class CoreMainVmImpl(
    application: Application,
    val ads: IAds,
    val payments: IPayments,
    val networkStatus: INetworkStatus,
    gdprConsent: IGDPRConsent
) : CoreMainVM(application), IPayments by payments, IAds by ads, INetworkStatus by networkStatus, IGDPRConsent by gdprConsent {

    override fun start() {
        launchMain {
            try {
                payments.noAdsActive.collect {
                    try {
                        onNoAdsChange(it)
                    } catch (e: Exception) {
                        e(e)
                    }
                }
            } catch (e: Exception) {
                e(e)
            }
        }
    }

    override fun resetAdsPreferences() = showGdprResetAds.postValue(Unit)

    override val showGdprResetAds: MutableSingleLiveEvent<Unit> = MutableSingleLiveEvent()

    private fun onNoAdsChange(noAdsActive: Boolean) = if (noAdsActive) ads.disable() else ads.enable()

    override fun onCleared() = CoreViewModelsFactory.cleanAds()
}