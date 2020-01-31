package pl.netigen.core.main

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import pl.netigen.core.ads.AdMobAds
import pl.netigen.core.gdpr.GDPRConsentImpl
import pl.netigen.core.network.NetworkStatus
import pl.netigen.core.splash.SplashVMImpl
import pl.netigen.coreapi.ads.IAds
import pl.netigen.coreapi.ads.IAdsConfig
import pl.netigen.coreapi.gdpr.IGDPRConsent
import pl.netigen.coreapi.main.CoreMainVM
import pl.netigen.coreapi.main.IAppConfig
import pl.netigen.coreapi.main.ICoreViewModelsFactory
import pl.netigen.coreapi.network.INetworkStatus
import pl.netigen.coreapi.payments.IPayments
import pl.netigen.coreapi.splash.SplashVM

abstract class CoreViewModelsFactory(final override val activity: CoreMainActivity) : ICoreViewModelsFactory {
    override val networkStatus get() = getNetworkStatus(activity.application)
    override val ads get() = getAds(activity, appConfig)
    override val gdprConsent get() = getGdprConsent(activity.application, appConfig)

    private fun getCoreMainVm(): CoreMainVM = CoreMainVmImpl(activity.application, ads, getPayments { payments }, networkStatus)
    private fun getSplashVm(): SplashVM = SplashVMImpl(
        activity.application,
        networkStatus = networkStatus,
        ads = ads,
        gdprConsent = gdprConsent,
        noAdsPurchases = getPayments { payments },
        appConfig = appConfig
    )

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

        private fun getAds(activity: ComponentActivity, adsConfig: IAdsConfig): IAds =
            ads ?: synchronized(this) { ads ?: AdMobAds(activity, adsConfig).also { ads = it } }

        private fun getNetworkStatus(application: Application): INetworkStatus =
            networkStatus ?: synchronized(this) { networkStatus ?: NetworkStatus(application).also { networkStatus = it } }

        private fun getGdprConsent(application: Application, appConfig: IAppConfig): IGDPRConsent =
            gdprConsent ?: synchronized(this) { gdprConsent ?: GDPRConsentImpl(application, appConfig).also { gdprConsent = it } }

        private fun getPayments(constructor: () -> IPayments): IPayments =
            payments ?: synchronized(this) { payments ?: constructor().also { payments = it } }

        fun cleanAds() {
            ads = null
        }
    }
}