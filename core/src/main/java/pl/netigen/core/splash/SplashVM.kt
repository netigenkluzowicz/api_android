package pl.netigen.core.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import pl.netigen.coreapi.ads.IAds
import pl.netigen.coreapi.ads.InterstitialAdListener
import pl.netigen.coreapi.gdpr.AdConsentStatus
import pl.netigen.coreapi.gdpr.CheckGDPRLocationStatus
import pl.netigen.coreapi.gdpr.IGDPRConsent
import pl.netigen.coreapi.network.INetworkStatus
import pl.netigen.coreapi.network.NetworkStatusChangeListener
import pl.netigen.coreapi.payments.INoAds
import pl.netigen.coreapi.splash.ISplashTimer
import pl.netigen.coreapi.splash.ISplashVM
import pl.netigen.coreapi.splash.SplashState
import pl.netigen.extensions.launchIO

class SplashVM(
    private val gdprConsent: IGDPRConsent,
    private val ads: IAds,
    private val noAdsPurchases: INoAds,
    private val networkStatus: INetworkStatus,
    private val splashTimer: ISplashTimer = SplashTimer()
) : ViewModel(), ISplashVM, InterstitialAdListener, NetworkStatusChangeListener {
    override val splashState: MutableLiveData<SplashState> = MutableLiveData(SplashState.UNINITIALIZED)

    override fun onStart() {
        launchIO { noAdsPurchases.noAdsActive.collect { onAdsFlowChanged(it) } }
        when {
            isRunning() -> Unit //do Nothing
            !networkStatus.isConnectedOrConnecting -> if (isFirstLaunch()) showGdprPopUp() else finish()
            isFirstLaunch() -> onFirstLaunch()
            else -> onNextLaunch()
        }
    }

    private fun isRunning() = splashState.value != SplashState.UNINITIALIZED && splashState.value != SplashState.FINISHED

    private fun onFirstLaunch() {
        updateState(SplashState.LOADING_FIRST_LAUNCH)
        splashTimer.startConsentTimer(this::showGdprPopUp)
        gdprConsent.requestGDPRLocation {
            splashTimer.cancelConsentTimer()
            when (it) {
                CheckGDPRLocationStatus.NON_UE -> initOnNonUeLocation()
                CheckGDPRLocationStatus.UE -> showGdprPopUp()
                CheckGDPRLocationStatus.ERROR -> showGdprPopUp()
            }
        }
    }

    private fun onNextLaunch() {
        startLoadingInterstitial()
        if (!gdprConsent.isConsentShowed()) checkConsentNextLaunch()

    }

    private fun checkConsentNextLaunch() {
        splashTimer.startConsentTimer { gdprConsent.cancelRequest() }
        gdprConsent.requestGDPRLocation { gdprLocationStatus ->
            splashTimer.cancelConsentTimer()
            if (gdprLocationStatus == CheckGDPRLocationStatus.UE) onLocationChangeToEu()
        }
    }

    private fun onLocationChangeToEu() {
        stopAdLoading()
        showGdprPopUp()
    }

    private fun stopAdLoading() {
        splashTimer.cancelInterstitialTimer()
        ads.interstitialAd.removeInterstitialListener(this)
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
        gdprConsent.cancelRequest()
        splashTimer.cancelTimers()
        ads.interstitialAd.showInterstitialAd { finish() }
    }

    private fun initOnNonUeLocation() {
        ads.setConsentStatus(true)
        gdprConsent.saveAdConsentStatus(AdConsentStatus.PERSONALIZED_NON_UE)
        startLoadingInterstitial()
    }

    private fun startLoadingInterstitial() {
        if (!networkStatus.isConnectedOrConnecting) return finish()
        networkStatus.addNetworkStatusChangeListener(this)
        updateState(SplashState.LOADING_INTERSTITIAL)
        ads.interstitialAd.addInterstitialListener(this)
        ads.interstitialAd.loadInterstitialAd()
        splashTimer.startInterstitialTimer(this::finish)
    }

    private fun finish() {
        cleanUp()
        updateState(SplashState.FINISHED)
    }

    private fun updateState(splashState: SplashState) = this.splashState.postValue(splashState)

    private fun showGdprPopUp() = updateState(SplashState.GDPR_POP_UP)

    private fun isFirstLaunch(): Boolean = gdprConsent.lastKnownAdConsentStatus == AdConsentStatus.UNINITIALIZED

    private fun onAdsFlowChanged(purchased: Boolean) {
        if (purchased) {
            finish()
        }
    }

    override fun onCleared() {
        if (isRunning()) {
            cleanUp()
            updateState(SplashState.UNINITIALIZED)
        }
    }

    private fun cleanUp() {
        viewModelScope.cancel()
        gdprConsent.cancelRequest()
        splashTimer.cancelTimers()
        networkStatus.removeNetworkStatusChangeListener(this)
        ads.interstitialAd.removeInterstitialListener(this)
    }

    override fun onNetworkStatusChanged(isConnected: Boolean) {
        if (!isConnected && splashState.value == SplashState.LOADING_INTERSTITIAL) finish()
    }
}