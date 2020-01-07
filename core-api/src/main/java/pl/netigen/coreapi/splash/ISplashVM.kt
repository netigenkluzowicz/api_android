package pl.netigen.coreapi.splash

import androidx.lifecycle.LiveData

interface ISplashVM {
    fun onStart()
    val splashState: LiveData<SplashState>
    fun onGdprDialogResult(personalizedAdsApproved: Boolean)
}