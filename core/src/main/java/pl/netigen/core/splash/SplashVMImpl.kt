package pl.netigen.core.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import pl.netigen.core.config.AppConfig
import pl.netigen.coreapi.ads.IAds
import pl.netigen.coreapi.gdpr.AdConsentStatus
import pl.netigen.coreapi.gdpr.AdConsentStatus.*
import pl.netigen.coreapi.gdpr.CheckGDPRLocationStatus
import pl.netigen.coreapi.gdpr.IGDPRConsent
import pl.netigen.coreapi.network.INetworkStatus
import pl.netigen.coreapi.payments.INoAds
import pl.netigen.coreapi.splash.SplashState
import pl.netigen.coreapi.splash.SplashVM
import pl.netigen.extensions.launch
import pl.netigen.extensions.launchMain
import timber.log.Timber.d

open class SplashVMImpl(
    private val gdprConsent: IGDPRConsent,
    private val ads: IAds,
    private val noAdsPurchases: INoAds,
    private val networkStatus: INetworkStatus,
    private val appConfig: AppConfig,
    val coroutineDispatcherIo: CoroutineDispatcher = Dispatchers.IO
) : SplashVM(), INoAds by noAdsPurchases {
    override val splashState: MutableLiveData<SplashState> = MutableLiveData(SplashState.UNINITIALIZED)
    override val isFirstLaunch: MutableLiveData<Boolean> = MutableLiveData(false)
    override val isNoAdsAvailable: Boolean = appConfig.isNoAdsAvailable
    private var isRunning = false

    override fun start() {
        d(isRunning.toString())
        if (!isRunning) init()
    }

    private fun init() {
        isRunning = true
        launch(coroutineDispatcherIo) { noAdsPurchases.noAdsActive.collect { onAdsFlowChanged(it) } }
        launch(coroutineDispatcherIo) {
            gdprConsent.adConsentStatus
                .onEach {
                    withTimeout(appConfig.maxConsentWaitTime) { if (it == UNINITIALIZED) onFirstLaunch() else onNextLaunch(it) }
                }.catch { onFirstLaunch() }
                .collect()
        }
    }

    private fun onAdsFlowChanged(purchased: Boolean) {
        if (purchased) {
            finish()
        }
    }

    private fun finish() {
        d("called")
        cleanUp()
        updateState(SplashState.FINISHED)
    }

    private fun cleanUp() {
        viewModelScope.cancel()
        isRunning = false
    }

    private fun updateState(splashState: SplashState) = this.splashState.postValue(splashState)

    private fun onFirstLaunch() {
        if (!networkStatus.isConnectedOrConnecting) return showGdprPopUp()
        isFirstLaunch.postValue(true)
        launch(coroutineDispatcherIo) {
            gdprConsent.requestGDPRLocation()
                .onEach {
                    withTimeout(appConfig.maxConsentWaitTime) {
                        onFirstLaunchCheckGdpr(it)
                        yield()
                    }
                }
                .catch { showGdprPopUp() }
                .collect()
        }
    }

    private fun showGdprPopUp() {
        updateState(SplashState.SHOW_GDPR_CONSENT)
    }

    private fun onFirstLaunchCheckGdpr(it: CheckGDPRLocationStatus) {
//        when (it) {
//            CheckGDPRLocationStatus.NON_UE -> initOnNonUeLocation()
//            CheckGDPRLocationStatus.UE -> showGdprPopUp()
//            CheckGDPRLocationStatus.ERROR -> showGdprPopUp()
//        }
    }

    private fun onNextLaunch(it: AdConsentStatus) {
        startLoadingInterstitial()
        if (it == PERSONALIZED_NON_UE) checkConsentNextLaunch()
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
                .onEach { withTimeout(appConfig.maxInterstitialWaitTime) { onLoadInterstitialResult(it) } }
                .catch { onLoadInterstitialResult(false) }
                .collect()
        }
    }

    private fun onLoadInterstitialResult(success: Boolean) = if (success) onInterstitialLoaded() else finish()

    private fun onInterstitialLoaded() {
        cleanUp()
        ads.interstitialAd.showInterstitialAd { finish() }
    }

    private fun checkConsentNextLaunch() {
        launch(coroutineDispatcherIo) {
            gdprConsent.requestGDPRLocation()
                .onEach {
                    withTimeoutOrNull(appConfig.maxConsentWaitTime) {
                        if (it == CheckGDPRLocationStatus.UE) {
                            cleanUp()
                            showGdprPopUp()
                        }
                    }
                }.collect()
        }
    }

    override fun setPersonalizedAds(personalizedAdsApproved: Boolean) {
        val adConsentStatus: AdConsentStatus = if (personalizedAdsApproved) PERSONALIZED_SHOWED else NON_PERSONALIZED_SHOWED
        gdprConsent.saveAdConsentStatus(adConsentStatus)
        ads.personalizedAdsEnabled = personalizedAdsApproved
        startLoadingInterstitial()
    }

    override fun onCleared() {
        d("called")
        if (isRunning) {
            cleanUp()
            updateState(SplashState.UNINITIALIZED)
            isRunning = false
        }
    }
}