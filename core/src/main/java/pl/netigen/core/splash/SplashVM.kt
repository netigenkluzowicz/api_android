package pl.netigen.core.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withTimeout
import pl.netigen.coreapi.ads.IAds
import pl.netigen.coreapi.ads.InterstitialAdListener
import pl.netigen.coreapi.gdpr.AdConsentStatus
import pl.netigen.coreapi.gdpr.AdConsentStatus.*
import pl.netigen.coreapi.gdpr.CheckGDPRLocationStatus
import pl.netigen.coreapi.gdpr.IGDPRConsent
import pl.netigen.coreapi.network.INetworkStatus
import pl.netigen.coreapi.network.NetworkStatusChangeListener
import pl.netigen.coreapi.payments.INoAds
import pl.netigen.coreapi.splash.ISplashTimer
import pl.netigen.coreapi.splash.ISplashVM
import pl.netigen.coreapi.splash.SplashState
import pl.netigen.extensions.launch

class SplashVM(
    private val gdprConsent: IGDPRConsent,
    private val ads: IAds,
    private val noAdsPurchases: INoAds,
    private val networkStatus: INetworkStatus,
    private val splashTimer: ISplashTimer = SplashTimer(),
    private val maxConsentWaitTime: Long = (SplashTimer.DEFAULT_MAX_CONSENT_WAIT_TIME_MS),
    val coroutineDispatcherIo: CoroutineDispatcher = Dispatchers.IO
) : ViewModel(), ISplashVM, InterstitialAdListener, NetworkStatusChangeListener {
    override val splashState: MutableLiveData<SplashState> = MutableLiveData(SplashState.UNINITIALIZED)

    override fun onStart() {
        if (!isRunning()) init()
    }

    private fun init() {
        launch(coroutineDispatcherIo) { noAdsPurchases.noAdsActive.collect { onAdsFlowChanged(it) } }
        launch(coroutineDispatcherIo) {
            gdprConsent.adConsentStatus
                .catch {
                    onFirstLaunch()
                }
                .first {
                    if (it == UNINITIALIZED)
                        onFirstLaunch() else
                        onNextLaunch(it)
                    true
                }
        }
    }

    private fun onNextLaunch(it: AdConsentStatus) {
        if (it == PERSONALIZED_NON_UE) checkConsentNextLaunch()
        startLoadingInterstitial()
    }

    private fun onFirstLaunchCheckGdpr(it: CheckGDPRLocationStatus) {
        when (it) {
            CheckGDPRLocationStatus.NON_UE -> initOnNonUeLocation()
            CheckGDPRLocationStatus.UE -> showGdprPopUp()
            CheckGDPRLocationStatus.ERROR -> showGdprPopUp()
        }
    }

    private fun isRunning() = splashState.value != SplashState.UNINITIALIZED && splashState.value != SplashState.FINISHED

    private fun onFirstLaunch() {
        if (!networkStatus.isConnectedOrConnecting) return showGdprPopUp()
        updateState(SplashState.LOADING_FIRST_LAUNCH)
        launch(coroutineDispatcherIo) {
            gdprConsent.requestGDPRLocation()
                .catch { showGdprPopUp() }
                .onEach {
                    withTimeout(maxConsentWaitTime) {
                        onFirstLaunchCheckGdpr(it)
                    }
                }
        }
    }

    private fun checkConsentNextLaunch() {
        launch(coroutineDispatcherIo) {
            gdprConsent.requestGDPRLocation()
                .onEach {
                    withTimeout(maxConsentWaitTime) {
                        if (it == CheckGDPRLocationStatus.UE) onLocationChangeToEu()
                    }
                }
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
                PERSONALIZED_SHOWED
            } else {
                NON_PERSONALIZED_SHOWED
            }
        gdprConsent.saveAdConsentStatus(adConsentStatus)
        ads.setConsentStatus(personalizedAdsApproved)
        startLoadingInterstitial()
    }

    override fun loadInterstitialResult(success: Boolean) = if (success) onInterstitialLoaded() else finish()

    private fun onInterstitialLoaded() {
        viewModelScope.cancel()
        splashTimer.cancelTimers()
        ads.interstitialAd.showInterstitialAd { finish() }
    }

    private fun initOnNonUeLocation() {
        ads.setConsentStatus(true)
        gdprConsent.saveAdConsentStatus(PERSONALIZED_NON_UE)
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
        splashTimer.cancelTimers()
        networkStatus.removeNetworkStatusChangeListener(this)
        ads.interstitialAd.removeInterstitialListener(this)
    }

    override fun onNetworkStatusChanged(isConnected: Boolean) {
        if (!isConnected && splashState.value == SplashState.LOADING_INTERSTITIAL) finish()
    }
}