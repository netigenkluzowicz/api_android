package pl.netigen.core.main

import androidx.lifecycle.ViewModel
import pl.netigen.core.network.NetworkStatus
import pl.netigen.core.splash.CoreSplashVMImpl
import pl.netigen.coreapi.main.CoreMainVM
import pl.netigen.coreapi.main.ICoreViewModelsFactory
import pl.netigen.coreapi.splash.SplashVM

abstract class CoreViewModelsFactory(final override val activity: CoreMainActivity) : ICoreViewModelsFactory {
    override val networkStatus by lazy { NetworkStatus(activity.application) }

    private fun getCoreMainVm(): CoreMainVM = CoreMainVmImpl(activity.application, ads, payments, networkStatus, gdprConsent)
    private fun getSplashVm(): SplashVM = CoreSplashVMImpl(
        activity.application,
        networkStatus = networkStatus,
        ads = ads,
        gdprConsent = gdprConsent,
        noAdsPurchases = payments,
        appConfig = appConfig
    )

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashVM::class.java)) return getSplashVm() as T
        if (modelClass.isAssignableFrom(CoreMainVM::class.java)) return getCoreMainVm() as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}