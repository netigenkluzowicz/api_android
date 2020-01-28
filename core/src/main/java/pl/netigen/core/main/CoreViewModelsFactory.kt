package pl.netigen.core.main

import androidx.lifecycle.ViewModel
import pl.netigen.core.ads.AdMobAds
import pl.netigen.core.config.AppConfig
import pl.netigen.core.gdpr.GDPRConsentImpl
import pl.netigen.core.network.NetworkStatus
import pl.netigen.core.splash.SplashVMImpl
import pl.netigen.coreapi.main.CoreMainVM
import pl.netigen.coreapi.main.ICoreViewModelsFactory
import pl.netigen.coreapi.payments.IPayments
import pl.netigen.coreapi.splash.SplashVM

class CoreViewModelsFactory(
    override val coreMainActivity: CoreMainActivity,
    override val appConfig: AppConfig,
    override val payments: IPayments
) : ICoreViewModelsFactory {
    override val ads by lazy { AdMobAds(activity = coreMainActivity, adsConfig = appConfig) }
    override val networkStatus by lazy { NetworkStatus(coreMainActivity) }

    private fun getSplashVm(): SplashVMImpl = SplashVMImpl(
        coreMainActivity.application,
        networkStatus = networkStatus,
        ads = ads,
        gdprConsent = GDPRConsentImpl(coreMainActivity, appConfig),
        noAdsPurchases = payments,
        appConfig = appConfig
    )

    private fun getCoreMainVm(): CoreMainVmImpl = CoreMainVmImpl(coreMainActivity.application, ads, payments)

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashVM::class.java)) return getSplashVm() as T
        if (modelClass.isAssignableFrom(CoreMainVM::class.java)) return getCoreMainVm() as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}