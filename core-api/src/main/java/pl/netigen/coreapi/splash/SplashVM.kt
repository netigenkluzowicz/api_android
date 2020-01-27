package pl.netigen.coreapi.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import pl.netigen.coreapi.payments.INoAds

abstract class SplashVM : ViewModel(), INoAds {
    abstract val splashState: LiveData<SplashState>
    abstract val isFirstLaunch: LiveData<Boolean>
    abstract val isNoAdsAvailable: Boolean

    abstract fun setPersonalizedAds(personalizedAdsApproved: Boolean)
    abstract fun start()
}