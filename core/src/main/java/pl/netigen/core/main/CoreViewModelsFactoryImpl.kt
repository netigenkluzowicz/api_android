package pl.netigen.core.main

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

abstract class CoreViewModelsFactory(override val activity: CoreMainActivity) : ICoreViewModelsFactory {
    override val networkStatus get() = getNetworkStatus(activity)
    override val admob get() = getAds(activity, appConfig)
    override val gdprConsent get() = getGdprConsent(activity, appConfig)

    private fun getCoreMainVm(): CoreMainVM = CoreMainVmImpl(activity.application, admob, getPayments { payments }, networkStatus)
    private fun getSplashVm(): SplashVM = SplashVMImpl(
        activity.application,
        networkStatus = networkStatus,
        ads = admob,
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

        fun getAds(activity: ComponentActivity, adsConfig: IAdsConfig): IAds =
            ads ?: synchronized(this) { ads ?: AdMobAds(activity, adsConfig).also { ads = it } }

        fun getNetworkStatus(activity: ComponentActivity): INetworkStatus =
            networkStatus ?: synchronized(this) { networkStatus ?: NetworkStatus(activity).also { networkStatus = it } }

        fun getGdprConsent(activity: ComponentActivity, appConfig: IAppConfig): IGDPRConsent =
            gdprConsent ?: synchronized(this) { gdprConsent ?: GDPRConsentImpl(activity, appConfig).also { gdprConsent = it } }

        fun getPayments(constructor: () -> IPayments): IPayments =
            payments ?: synchronized(this) { payments ?: constructor().also { payments = it } }

    }
}