package pl.netigen.core.main

import androidx.lifecycle.ViewModel
import pl.netigen.core.ads.AdMobAds
import pl.netigen.core.gdpr.GDPRConsentImpl
import pl.netigen.core.network.NetworkStatus
import pl.netigen.core.splash.SplashVMImpl
import pl.netigen.coreapi.main.CoreMainVM
import pl.netigen.coreapi.main.ICoreViewModelsFactory
import pl.netigen.coreapi.splash.SplashVM

abstract class CoreViewModelsFactory(override val mainActivity: CoreMainActivity) : ICoreViewModelsFactory {
    override val ads by lazy {
        AdMobAds(activity = mainActivity, adsConfig = appConfig)
    }
    override val networkStatus by lazy { NetworkStatus(mainActivity) }

    override fun getSplashVm(): SplashVM = SplashVMImpl(
        mainActivity.application,
        networkStatus = networkStatus,
        ads = ads,
        gdprConsent = GDPRConsentImpl(mainActivity, appConfig),
        noAdsPurchases = payments,
        appConfig = appConfig
    )

    override fun getCoreMainVm(): CoreMainVM = CoreMainVmImpl(mainActivity.application, ads, payments)

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashVM::class.java)) return getSplashVm() as T
        if (modelClass.isAssignableFrom(CoreMainVM::class.java)) return getCoreMainVm() as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}