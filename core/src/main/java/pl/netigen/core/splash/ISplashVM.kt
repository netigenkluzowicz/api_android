package pl.netigen.core.splash

import androidx.lifecycle.LiveData

interface ISplashVM {
    fun onStart()
    val splashState: LiveData<SplashState>
    fun onGdprDialogResult(personalizedAdsApproved: Boolean)
}