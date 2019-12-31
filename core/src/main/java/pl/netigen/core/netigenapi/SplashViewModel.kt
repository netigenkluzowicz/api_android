package pl.netigen.core.netigenapi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.netigen.core.ads.IAds
import pl.netigen.core.ads.LoadInterstitialListener
import pl.netigen.core.gdpr.AdConsentStatus
import pl.netigen.core.gdpr.CheckGDPRLocationStatus
import pl.netigen.core.gdpr.IGDPRConsent

class SplashViewModel : ViewModel(), ISplashViewModel, LoadInterstitialListener {
    override val currentSplashState: MutableLiveData<ISplashViewModel.SplashState> = MutableLiveData()
    private val splashTimer: ISplashTimer = SplashTimer()
    private val gdprConsent: IGDPRConsent = TODO()
    private val ads: IAds = TODO()

    override fun start() {
        if (isFirstLaunch()) {
            onFirstLaunch()
        }
    }

    override fun loadInteristialResult(success: Boolean) = if (success) ads.showInterstitial { finish() } else finish()

    private fun onFirstLaunch() {
        updateState(ISplashViewModel.SplashState.LOADING_FIRST_LAUNCH)
        splashTimer.startConsentTimer(this::showGdprPopUp)
        gdprConsent.checkGDPRLocation {
            splashTimer.stopTimer()
            if (it == CheckGDPRLocationStatus.NON_UE) initOnNonUeLocation()
            else showGdprPopUp()
        }
    }

    private fun initOnNonUeLocation() {
        gdprConsent.saveAdConsentStatus(AdConsentStatus.PERSONALIZED_NON_UE)
        startLoadingInterstitial()
    }

    private fun startLoadingInterstitial() {
        updateState(ISplashViewModel.SplashState.LOADING_INTERSTITIAL)
        ads.addLoadInterstitialListener(this)
        ads.loadInterstitial()
        splashTimer.startInterstitialTimer(this::finish)
    }

    private fun finish() {
        splashTimer.stopTimer()
        ads.removeInterstitialListener(this)
        updateState(ISplashViewModel.SplashState.FINISHED)
    }

    private fun updateState(splashState: ISplashViewModel.SplashState) {
        currentSplashState.value = splashState
    }

    private fun showGdprPopUp() {
        currentSplashState.value = (ISplashViewModel.SplashState.GDPR_POP_UP)
    }

    private fun isFirstLaunch(): Boolean = gdprConsent.lastKnownAdConsentStatus == AdConsentStatus.UNINITIALIZED

}