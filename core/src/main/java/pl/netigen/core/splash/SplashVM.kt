package pl.netigen.core.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import pl.netigen.coreapi.ads.IAds
import pl.netigen.coreapi.gdpr.AdConsentStatus
import pl.netigen.coreapi.gdpr.AdConsentStatus.*
import pl.netigen.coreapi.gdpr.CheckGDPRLocationStatus
import pl.netigen.coreapi.gdpr.IGDPRConsent
import pl.netigen.coreapi.network.INetworkStatus
import pl.netigen.coreapi.payments.INoAds
import pl.netigen.coreapi.splash.ISplashVM
import pl.netigen.coreapi.splash.SplashState
import pl.netigen.extensions.launch
import pl.netigen.extensions.launchMain

@ExperimentalCoroutinesApi
class SplashVM(
    private val gdprConsent: IGDPRConsent,
    private val ads: IAds,
    private val noAdsPurchases: INoAds,
    private val networkStatus: INetworkStatus,
    private val maxConsentWaitTime: Long = DEFAULT_MAX_CONSENT_WAIT_TIME_MS,
    private val maxInterstitialWaitTime: Long = DEFAULT_MAX_LOAD_INTERSTITIAL_WAIT_TIME_MS,
    val coroutineDispatcherIo: CoroutineDispatcher = Dispatchers.IO
) : ViewModel(), ISplashVM {
    override val splashState: MutableLiveData<SplashState> = MutableLiveData(SplashState.UNINITIALIZED)
    override val isFirstLaunch: MutableLiveData<Boolean> = MutableLiveData(false)

    override fun onStart() {
        if (!isRunning()) init()
    }

    private fun init() {
        launch(coroutineDispatcherIo) { noAdsPurchases.noAdsActive.collect { onAdsFlowChanged(it) } }
        launch(coroutineDispatcherIo) {
            gdprConsent.adConsentStatus
                .onEach {
                    withTimeout(maxConsentWaitTime) { if (it == UNINITIALIZED) onFirstLaunch() else onNextLaunch(it) }
                }.catch { onFirstLaunch() }
                .collect()
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
        isFirstLaunch.postValue(true)
        launch(coroutineDispatcherIo) {
            gdprConsent.requestGDPRLocation()
                .onEach {
                    withTimeout(maxConsentWaitTime) {
                        onFirstLaunchCheckGdpr(it)
                    }
                }
                .catch { showGdprPopUp() }
                .collect()
        }
    }

    private fun checkConsentNextLaunch() {
        launch(coroutineDispatcherIo) {
            gdprConsent.requestGDPRLocation()
                .onEach {
                    withTimeoutOrNull(maxConsentWaitTime) {
                        if (it == CheckGDPRLocationStatus.UE) {
                            cleanUp()
                            showGdprPopUp()
                        }
                    }
                }
                .collect()
        }
    }

    override fun onGdprDialogResult(personalizedAdsApproved: Boolean) {
        val adConsentStatus: AdConsentStatus = if (personalizedAdsApproved) PERSONALIZED_SHOWED else NON_PERSONALIZED_SHOWED
        gdprConsent.saveAdConsentStatus(adConsentStatus)
        ads.personalizedAdsEnabled = personalizedAdsApproved
        startLoadingInterstitial()
    }

    private fun onLoadInterstitialResult(success: Boolean) = if (success) onInterstitialLoaded() else finish()

    private fun onInterstitialLoaded() {
        viewModelScope.cancel()
        ads.interstitialAd.showInterstitialAd { finish() }
    }

    private fun initOnNonUeLocation() {
        ads.personalizedAdsEnabled = true
        gdprConsent.saveAdConsentStatus(PERSONALIZED_NON_UE)
        startLoadingInterstitial()
    }

    private fun startLoadingInterstitial() {
        if (!networkStatus.isConnectedOrConnecting) return finish()
        updateState(SplashState.LOADING)
        launchMain {
            ads.interstitialAd.loadInterstitialAd()
                .onEach { withTimeout(maxInterstitialWaitTime) { onLoadInterstitialResult(it) } }
                .catch { onLoadInterstitialResult(false) }
                .collect()
        }
    }

    private fun finish() {
        cleanUp()
        updateState(SplashState.FINISHED)
    }

    private fun updateState(splashState: SplashState) = this.splashState.postValue(splashState)

    private fun showGdprPopUp() {
        updateState(SplashState.GDPR_POP_UP)
    }

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
    }

    companion object {
        const val DEFAULT_MAX_CONSENT_WAIT_TIME_MS = 5000L
        const val DEFAULT_MAX_LOAD_INTERSTITIAL_WAIT_TIME_MS = 7000L
    }
}