package pl.netigen.core.splash

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import pl.netigen.coreapi.ads.IAds
import pl.netigen.coreapi.gdpr.AdConsentStatus
import pl.netigen.coreapi.gdpr.AdConsentStatus.*
import pl.netigen.coreapi.gdpr.CheckGDPRLocationStatus
import pl.netigen.coreapi.gdpr.IGDPRConsent
import pl.netigen.coreapi.main.IAppConfig
import pl.netigen.coreapi.network.INetworkStatus
import pl.netigen.coreapi.payments.INoAds
import pl.netigen.coreapi.splash.SplashState
import pl.netigen.coreapi.splash.SplashVM
import pl.netigen.extensions.launch
import pl.netigen.extensions.launchIO
import timber.log.Timber.d

class SplashVMImpl(
    application: Application,
    private val gdprConsent: IGDPRConsent,
    private val ads: IAds,
    private val noAdsPurchases: INoAds,
    private val networkStatus: INetworkStatus,
    private val appConfig: IAppConfig,
    val coroutineDispatcherIo: CoroutineDispatcher = Dispatchers.IO,
    val coroutineDispatcherMain: CoroutineDispatcher = Dispatchers.Main
) : SplashVM(application), INoAds by noAdsPurchases {
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
        try {
            launchWithTimeout(appConfig.maxConsentWaitTime, gdprConsent.adConsentStatus) {
                if (it == UNINITIALIZED) onFirstLaunch() else onNextLaunch(it)
            }
        } catch (e: TimeoutCancellationException) {
            onFirstLaunch()
        }
    }

    private fun <T> launchWithTimeout(
        timeOut: Long,
        flow: Flow<T>,
        coroutineDispatcher: CoroutineDispatcher = coroutineDispatcherIo,
        action: suspend (value: T) -> Unit
    ) {
        launch(coroutineDispatcher) { withTimeout(timeOut) { flow.collect(action) } }
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
    }

    private fun updateState(splashState: SplashState) = this.splashState.postValue(splashState)

    private fun onFirstLaunch() {
        if (!networkStatus.isConnectedOrConnecting) return showGdprPopUp()
        isFirstLaunch.postValue(true)
        try {
            launchWithTimeout(appConfig.maxConsentWaitTime, gdprConsent.requestGDPRLocation()) { onFirstLaunchCheckGdpr(it) }
        } catch (e: TimeoutCancellationException) {
            showGdprPopUp()
        }
    }

    private fun showGdprPopUp() {
        launch(coroutineDispatcherIo) { noAdsPurchases.noAdsActive.collect { onAdsFlowChanged(it) } }
        updateState(SplashState.SHOW_GDPR_CONSENT)
    }

    private fun onFirstLaunchCheckGdpr(it: CheckGDPRLocationStatus) {
        when (it) {
            CheckGDPRLocationStatus.NON_UE -> initOnNonUeLocation()
            CheckGDPRLocationStatus.UE -> showGdprPopUp()
            CheckGDPRLocationStatus.ERROR -> showGdprPopUp()
        }
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
        launchIO {
            try {
                withTimeout(appConfig.maxInterstitialWaitTime) {
                    withContext(coroutineDispatcherMain) {
                        if (ads.interstitialAd.isLoaded) {
                            onLoadInterstitialResult(true)
                        } else {
                            ads.interstitialAd.loadInterstitialAd().collect { onLoadInterstitialResult(it) }
                        }
                    }
                }
            } catch (e: TimeoutCancellationException) {
                d(e)
                withContext(coroutineDispatcherMain) {
                    onLoadInterstitialResult(false)
                }
            }
        }

    }

    private fun onLoadInterstitialResult(success: Boolean) = if (success) onInterstitialLoaded() else finish()

    private fun onInterstitialLoaded() {
        cleanUp()
        ads.interstitialAd.showInterstitialAd { finish() }
    }

    private fun checkConsentNextLaunch() =
        launchWithTimeout(appConfig.maxConsentWaitTime, gdprConsent.requestGDPRLocation()) { onFirstLaunchCheckGdpr(it) }

    override fun setPersonalizedAds(personalizedAdsApproved: Boolean) {
        val adConsentStatus: AdConsentStatus = if (personalizedAdsApproved) PERSONALIZED_SHOWED else NON_PERSONALIZED_SHOWED
        gdprConsent.saveAdConsentStatus(adConsentStatus)
        ads.personalizedAdsEnabled = personalizedAdsApproved
        startLoadingInterstitial()
    }

    override fun onCleared() {
        if (isRunning) {
            cleanUp()
            updateState(SplashState.UNINITIALIZED)
            isRunning = false
        }
    }
}