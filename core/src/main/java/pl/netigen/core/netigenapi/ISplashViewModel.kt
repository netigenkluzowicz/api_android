package pl.netigen.core.netigenapi

import androidx.lifecycle.LiveData

interface ISplashViewModel {
    fun start()
    val currentSplashState: LiveData<SplashState>

    enum class SplashState {
        LOADING_INTERSTITIAL, LOADING_FIRST_LAUNCH, GDPR_POP_UP, FINISHED
    }
}