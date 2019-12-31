package pl.netigen.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.netigen.core.ads.IAds
import pl.netigen.core.ads.LoadInterstitialListener
import pl.netigen.core.gdpr.AdConsentStatus
import pl.netigen.core.gdpr.CheckGDPRLocationStatus
import pl.netigen.core.gdpr.IGDPRConsent

class SplashViewModel(
    private val gdprConsent: IGDPRConsent,
    private val ads: IAds,
    private val splashTimer: ISplashTimer = SplashTimer()
) : ViewModel(), ISplashViewModel, LoadInterstitialListener {
    override val currentSplashState: MutableLiveData<SplashState> = MutableLiveData(SplashState.IDLE)

    override fun onStart() = when {
        isRunning() -> Unit //do Nothing
        isFirstLaunch() -> onFirstLaunch()
        else -> onNextLaunch()
    }

    private fun isRunning() =
        currentSplashState.value != SplashState.IDLE && currentSplashState.value != SplashState.FINISHED

    private fun onNextLaunch() {
        startLoadingInterstitial()
        if (gdprConsent.isConsentShowed()) return
        checkConsentNextLaunch()
    }

    private fun checkConsentNextLaunch() {
        splashTimer.startConsentTimer { gdprConsent.cancelRequest() }
        gdprConsent.requestGDPRLocation { gdprLocationStatus ->
            splashTimer.cancelConsentTimer()
            if (gdprLocationStatus == CheckGDPRLocationStatus.UE) {
                splashTimer.cancelInterstitialTimer()
                ads.removeInterstitialListener(this)
                showGdprPopUp()
            }
        }
    }

    override fun onGdprDialogResult(personalizedAdsApproved: Boolean) {
        val adConsentStatus: AdConsentStatus =
            if (personalizedAdsApproved) {
                AdConsentStatus.PERSONALIZED_SHOWED
            } else {
                AdConsentStatus.NON_PERSONALIZED_SHOWED
            }
        gdprConsent.saveAdConsentStatus(adConsentStatus)
        ads.setConsentStatus(personalizedAdsApproved)
        startLoadingInterstitial()
    }

    override fun loadInterstitialResult(success: Boolean) = if (success) onInterstitialLoaded() else finish()

    private fun onInterstitialLoaded() {
        splashTimer.cancelTimers()
        ads.showInterstitialAd { finish() }
    }

    private fun onFirstLaunch() {
        updateState(SplashState.LOADING_FIRST_LAUNCH)
        splashTimer.startConsentTimer(this::showGdprPopUp)
        gdprConsent.requestGDPRLocation {
            splashTimer.cancelConsentTimer()
            if (it == CheckGDPRLocationStatus.NON_UE) initOnNonUeLocation()
            else showGdprPopUp()
        }
    }

    private fun initOnNonUeLocation() {
        ads.setConsentStatus(true)
        gdprConsent.saveAdConsentStatus(AdConsentStatus.PERSONALIZED_NON_UE)
        startLoadingInterstitial()
    }

    private fun startLoadingInterstitial() {
        updateState(SplashState.LOADING_INTERSTITIAL)
        ads.addLoadInterstitialListener(this)
        ads.loadInterstitialAd()
        splashTimer.startInterstitialTimer(this::finish)
    }

    private fun finish() {
        splashTimer.cancelTimers()
        ads.removeInterstitialListener(this)
        updateState(SplashState.FINISHED)
    }

    private fun updateState(splashState: SplashState) {
        currentSplashState.value = splashState
    }

    private fun showGdprPopUp() = updateState(SplashState.GDPR_POP_UP)

    private fun isFirstLaunch(): Boolean = gdprConsent.lastKnownAdConsentStatus == AdConsentStatus.UNINITIALIZED

}