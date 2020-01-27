package pl.netigen.core.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pl.netigen.core.gdpr.GDPRConsentImpl
import pl.netigen.core.main.CoreMainActivity
import pl.netigen.core.network.NetworkStatus
import pl.netigen.coreapi.splash.SplashVM

class SplashVmFactory(
    private val coreMainActivity: CoreMainActivity
) : ViewModelProvider.Factory {

    private fun getSplashVm(): SplashVMImpl {
        return SplashVMImpl(
            networkStatus = NetworkStatus(coreMainActivity),
            ads = coreMainActivity.ads,
            gdprConsent = GDPRConsentImpl(coreMainActivity, coreMainActivity.appConfig),
            noAdsPurchases = coreMainActivity.payments,
            appConfig = coreMainActivity.appConfig
        )
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashVM::class.java)) return getSplashVm() as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}