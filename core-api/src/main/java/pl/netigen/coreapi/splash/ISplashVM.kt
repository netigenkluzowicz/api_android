package pl.netigen.coreapi.splash

import androidx.lifecycle.LiveData

interface ISplashVM {
    fun onStart()
    val splashState: LiveData<SplashState>
    val isFirstLaunch: LiveData<Boolean>
    fun onGdprDialogResult(personalizedAdsApproved: Boolean)
}