package pl.netigen.core.main

import android.app.Application
import androidx.lifecycle.ViewModel
import pl.netigen.core.network.NetworkStatus
import pl.netigen.core.splash.CoreSplashVMImpl
import pl.netigen.coreapi.ads.IAds
import pl.netigen.coreapi.gdpr.IGDPRConsent
import pl.netigen.coreapi.main.CoreMainVM
import pl.netigen.coreapi.main.ICoreViewModelsFactory
import pl.netigen.coreapi.network.INetworkStatus
import pl.netigen.coreapi.payments.IPayments
import pl.netigen.coreapi.splash.SplashVM
import timber.log.Timber

abstract class CoreViewModelsFactory(final override val activity: CoreMainActivity) : ICoreViewModelsFactory {
    override val networkStatus get() = getNetworkStatus(activity.application)

    private fun getCoreMainVm(): CoreMainVM =
        CoreMainVmImpl(activity.application, singletonAds(), singletonPayments(), networkStatus, singletonConsent())

    private fun getSplashVm(): SplashVM = CoreSplashVMImpl(
        activity.application,
        networkStatus = networkStatus,
        ads = singletonAds(),
        gdprConsent = singletonConsent(),
        noAdsPurchases = singletonPayments(),
        appConfig = appConfig
    )

    private fun singletonConsent() = getGdprConsent { gdprConsent }

    private fun singletonPayments() = getPayments { payments }

    private fun singletonAds() = getAds { ads }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashVM::class.java)) return getSplashVm() as T
        if (modelClass.isAssignableFrom(CoreMainVM::class.java)) return getCoreMainVm() as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    companion object {
        @Volatile
        private var ads: IAds? = null

        @Volatile
        private var networkStatus: INetworkStatus? = null

        @Volatile
        private var gdprConsent: IGDPRConsent? = null

        @Volatile
        private var payments: IPayments? = null

        private fun getAds(constructor: () -> IAds): IAds =
            ads ?: synchronized(this) {
                ads ?: constructor().also {
                    Timber.d("$it")
                    ads = it
                }
            }

        private fun getNetworkStatus(application: Application): INetworkStatus =
            networkStatus ?: synchronized(this) { networkStatus ?: NetworkStatus(application).also { networkStatus = it } }

        private fun getGdprConsent(constructor: () -> IGDPRConsent): IGDPRConsent =
            gdprConsent ?: synchronized(this) { gdprConsent ?: constructor().also { gdprConsent = it } }

        private fun getPayments(constructor: () -> IPayments): IPayments =
            payments ?: synchronized(this) { payments ?: constructor().also { payments = it } }

        fun cleanAds() {
            Timber.d("()")
            ads = null
        }
    }
}