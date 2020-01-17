package pl.netigen.coreapi.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

abstract class SplashVM : ViewModel() {
    abstract fun onStart()
    abstract val splashState: LiveData<SplashState>
    abstract val isFirstLaunch: LiveData<Boolean>
    abstract fun setPersonalizedAds(personalizedAdsApproved: Boolean)
}