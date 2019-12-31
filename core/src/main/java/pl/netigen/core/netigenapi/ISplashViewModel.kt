package pl.netigen.core.netigenapi

import androidx.lifecycle.LiveData

interface ISplashViewModel {
    fun onStart()
    val currentSplashState: LiveData<SplashState>


    enum class SplashState {
        IDLE, LOADING_INTERSTITIAL, LOADING_FIRST_LAUNCH, GDPR_POP_UP, FINISHED
    }
}