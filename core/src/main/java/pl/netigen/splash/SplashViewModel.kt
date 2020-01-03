package pl.netigen.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.netigen.core.ads.IAds
import pl.netigen.core.ads.InterstitialAdListener
import pl.netigen.core.gdpr.AdConsentStatus
import pl.netigen.core.gdpr.CheckGDPRLocationStatus
import pl.netigen.core.gdpr.IGDPRConsent
import pl.netigen.core.network.INetworkStatus
import pl.netigen.core.network.NetworkStatus
import pl.netigen.core.network.NetworkStatusChangeListener
import pl.netigen.core.purchases.INoAdsPurchases
import pl.netigen.core.purchases.NoAdsPurchaseListener

class SplashViewModel(
    private val gdprConsent: IGDPRConsent,
    private val ads: IAds,
    private val noAdsPurchases: INoAdsPurchases,
    private val networkStatus: INetworkStatus = NetworkStatus(),
    private val splashTimer: ISplashTimer = SplashTimer()
) : ViewModel(), ISplashViewModel, InterstitialAdListener, NoAdsPurchaseListener, NetworkStatusChangeListener {
    override val currentSplashState: MutableLiveData<SplashState> = MutableLiveData(SplashState.IDLE)

    override fun onStart() {
        if (noAdsPurchases.isNoAdsActive()) return finish()
        noAdsPurchases.addNoAdsPurchaseListener(this)
        when {
            isRunning() -> Unit //do Nothing
            !networkStatus.lastKnownStatus -> if (isFirstLaunch()) showGdprPopUp() else finish()
            isFirstLaunch() -> onFirstLaunch()
            else -> onNextLaunch()
        }
    }

    private fun isRunning() = currentSplashState.value != SplashState.IDLE && currentSplashState.value != SplashState.FINISHED

    private fun onFirstLaunch() {
        updateState(SplashState.LOADING_FIRST_LAUNCH)
        splashTimer.startConsentTimer(this::showGdprPopUp)
        gdprConsent.requestGDPRLocation {
            splashTimer.cancelConsentTimer()
            if (it == CheckGDPRLocationStatus.NON_UE) initOnNonUeLocation()
            else showGdprPopUp()
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
        if (!networkStatus.lastKnownStatus) return finish()
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

    private fun updateState(splashState: SplashState) {
        currentSplashState.value = splashState
    }

    private fun showGdprPopUp() = updateState(SplashState.GDPR_POP_UP)

    private fun isFirstLaunch(): Boolean = gdprConsent.lastKnownAdConsentStatus == AdConsentStatus.UNINITIALIZED

    override fun onNoAdsPurchaseChanged(purchased: Boolean) {
        if (purchased) {
            ads.disable()
            finish()
        } else {
            ads.enable()
        }
    }

    override fun onCleared() = cleanUp()

    private fun cleanUp() {
        gdprConsent.cancelRequest()
        splashTimer.cancelTimers()
        networkStatus.removeNetworkStatusChangeListener(this)
        noAdsPurchases.removeNoAdsPurchaseListener(this)
        ads.interstitialAd.removeInterstitialListener(this)
    }

    override fun onNetworkStatusChanged(isConnected: Boolean) {
        if (!isConnected && currentSplashState.value == SplashState.LOADING_INTERSTITIAL) finish()
    }
}