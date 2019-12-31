package pl.netigen.splash

import androidx.lifecycle.LiveData

interface ISplashViewModel {
    fun onStart()
    val currentSplashState: LiveData<SplashState>
    fun onGdprDialogResult(personalizedAdsApproved: Boolean)
}