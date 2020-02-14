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

class CoreSplashVMImpl(
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
    private var finished = false

    override fun start() {
        d(isRunning.toString())
        if (!isRunning) init()
    }

    private fun init() {
        isRunning = true
        launch(coroutineDispatcherIo) {
            noAdsPurchases.noAdsActive.collect {
                if (isActive) {
                    onAdsFlowChanged(it)
                }
            }
        }
        launchWithTimeout(
            appConfig.maxConsentWaitTime,
            gdprConsent.adConsentStatus,
            { onFirstLaunch() }
        ) {
            when {
                finished -> finish()
                it == UNINITIALIZED -> onFirstLaunch()
                else -> onNextLaunch(it)
            }
        }
    }

    private fun <T> launchWithTimeout(
        timeOut: Long,
        flow: Flow<T>,
        onError: () -> Unit,
        action: suspend (value: T) -> Unit
    ) {
        launch(coroutineDispatcherIo) {
            try {
                withTimeout(timeOut) {
                    if (isActive) {
                        flow.collect(action)
                    }
                }
            } catch (e: TimeoutCancellationException) {
                d(e)
                onError()
            }
        }
    }

    private fun onAdsFlowChanged(purchased: Boolean) {
        d("purchased = [$purchased]")
        if (purchased) {
            finish()
        }
    }

    private fun finish() {
        d("()")
        finished = true
        updateState(SplashState.FINISHED)
    }


    private fun updateState(splashState: SplashState) = this.splashState.postValue(splashState)

    private fun onFirstLaunch() {
        d("()")
        if (finished) return finish()
        if (!networkStatus.isConnectedOrConnecting) return showGdprPopUp()
        isFirstLaunch.postValue(true)
        launchWithTimeout(
            appConfig.maxConsentWaitTime,
            gdprConsent.requestGDPRLocation(),
            { showGdprPopUp() }
        ) { onFirstLaunchCheckGdpr(it) }

    }

    private fun showGdprPopUp() {
        d("()")
        launch(coroutineDispatcherIo) {
            noAdsPurchases.noAdsActive.collect {
                if (isActive) {
                    onAdsFlowChanged(it)
                }
            }
        }
        updateState(SplashState.SHOW_GDPR_CONSENT)
    }

    private fun onFirstLaunchCheckGdpr(it: CheckGDPRLocationStatus) {
        d("it = [$it]")
        when (it) {
            CheckGDPRLocationStatus.NON_UE -> initOnNonUeLocation()
            CheckGDPRLocationStatus.UE -> showGdprPopUp()
            CheckGDPRLocationStatus.ERROR -> showGdprPopUp()
        }
    }

    private fun onNextLaunch(adConsentStatus: AdConsentStatus) {
        d("it = [$adConsentStatus]")
        startLoadingInterstitial()
        if (adConsentStatus == PERSONALIZED_NON_UE) checkConsentNextLaunch()
    }

    private fun initOnNonUeLocation() {
        d("()")
        ads.personalizedAdsEnabled = true
        gdprConsent.saveAdConsentStatus(PERSONALIZED_NON_UE)
        startLoadingInterstitial()
    }

    private fun startLoadingInterstitial() {
        d("()")
        if (!networkStatus.isConnectedOrConnecting || finished) return finish()
        updateState(SplashState.LOADING)
        launchIO {
            try {
                if (isActive) {
                    withTimeout(appConfig.maxInterstitialWaitTime) {
                        if (isActive) {
                            withContext(coroutineDispatcherMain) {
                                when {
                                    finished -> finish()
                                    ads.interstitialAd.isLoaded -> onLoadInterstitialResult(true)
                                    else -> ads.interstitialAd.load().collect { onLoadInterstitialResult(it) }
                                }
                            }
                        }
                    }
                }
            } catch (e: TimeoutCancellationException) {
                d(e)
                if (isActive) {
                    withContext(coroutineDispatcherMain) {
                        onLoadInterstitialResult(false)
                    }
                }
            }
        }

    }

    private fun onLoadInterstitialResult(success: Boolean) = if (success) onInterstitialLoaded() else finish()

    private fun onInterstitialLoaded() {
        d("()")
        viewModelScope.cancel("")
        ads.interstitialAd.showIfCanBeShowed { finish() }
    }

    private fun checkConsentNextLaunch() =
        launchWithTimeout(appConfig.maxConsentWaitTime, gdprConsent.requestGDPRLocation(), {}) { onFirstLaunchCheckGdpr(it) }

    override fun setPersonalizedAds(personalizedAdsApproved: Boolean) {
        d("personalizedAdsApproved = [$personalizedAdsApproved]")
        val adConsentStatus: AdConsentStatus = if (personalizedAdsApproved) PERSONALIZED_SHOWED else NON_PERSONALIZED_SHOWED
        gdprConsent.saveAdConsentStatus(adConsentStatus)
        ads.personalizedAdsEnabled = personalizedAdsApproved
        startLoadingInterstitial()
    }

    override fun onCleared() {
        d("()")
        if (isRunning) {
            updateState(SplashState.UNINITIALIZED)
            isRunning = false
            finished = false
        }
    }
}