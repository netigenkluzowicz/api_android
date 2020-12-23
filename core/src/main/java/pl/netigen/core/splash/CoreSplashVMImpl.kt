package pl.netigen.core.splash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.isActive
import pl.netigen.coreapi.ads.IAds
import pl.netigen.coreapi.gdpr.AdConsentStatus
import pl.netigen.coreapi.gdpr.AdConsentStatus.*
import pl.netigen.coreapi.gdpr.CheckGDPRLocationStatus
import pl.netigen.coreapi.gdpr.IGDPRConsent
import pl.netigen.coreapi.main.IAppConfig
import pl.netigen.coreapi.network.INetworkStatus
import pl.netigen.coreapi.payments.INoAds
import pl.netigen.coreapi.splash.ISplashTimer
import pl.netigen.coreapi.splash.SplashState
import pl.netigen.coreapi.splash.SplashVM
import pl.netigen.extensions.launch
import timber.log.Timber.d

/**
 * [SplashVM] implementation
 *
 * @property gdprConsent [IGDPRConsent] instance
 * @property ads [IAds] instance
 * @property noAdsPurchases [INoAds] instance
 * @property networkStatus [INetworkStatus] instance
 * @property appConfig [IAppConfig] instance
 * @property splashTimer ISplashTimer instance, for default it's [SplashTimerImpl]
 *
 * @param application provides [Application] context for this [AndroidViewModel]
 */
class CoreSplashVMImpl(
        application: Application,
        override val gdprConsent: IGDPRConsent,
        private val ads: IAds,
        private val noAdsPurchases: INoAds,
        private val networkStatus: INetworkStatus,
        private val appConfig: IAppConfig,
        private val splashTimer: ISplashTimer = SplashTimerImpl(appConfig.maxConsentWaitTime, appConfig.maxInterstitialWaitTime),
        val coroutineDispatcherIo: CoroutineDispatcher = Dispatchers.IO
) : SplashVM(application), INoAds by noAdsPurchases, IAppConfig by appConfig {
    override val splashState: MutableLiveData<SplashState> = MutableLiveData(SplashState.UNINITIALIZED)
    override val isFirstLaunch: MutableLiveData<Boolean> = MutableLiveData(false)
    private var isRunning = false
    private var finished = false

    override fun start() {
        d(isRunning.toString())
        if (!isRunning) init()
    }

    private fun init() {
        d("()")
        isRunning = true
        launch(coroutineDispatcherIo) {
            noAdsPurchases.noAdsActive.collect {
                if (isActive) {
                    onAdsFlowChanged(it)
                }
            }
        }

        splashTimer.startConsentTimer { onFirstLaunch() }
        launch {
            gdprConsent.adConsentStatus.collect {
                if (isActive) {
                    splashTimer.cancelTimers()
                    when {
                        finished -> finish()
                        it == UNINITIALIZED -> onFirstLaunch()
                        else -> onNextLaunch(it)
                    }
                }
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
        cancelJobs()
        splashTimer.cancelTimers()
        finished = true
        updateState(SplashState.FINISHED)
    }


    private fun updateState(splashState: SplashState) = this.splashState.postValue(splashState)

    private fun onFirstLaunch() {
        d("()")
        if (finished) return finish()
        if (!networkStatus.isConnectedOrConnecting) return showGdprPopUp()
        isFirstLaunch.postValue(true)
        splashTimer.startConsentTimer { showGdprPopUp() }
        launch {
            gdprConsent.requestGDPRLocation().collect {
                if (isActive) {
                    d("requestGDPRLocation: + $it")
                    splashTimer.cancelTimers()
                    onFirstLaunchCheckGdpr(it)
                }
            }
        }
    }

    private fun showGdprPopUp() {
        d("()")
        cancelJobs()
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
            CheckGDPRLocationStatus.FORM_SHOW_REQUIRED -> loadForm()
        }
    }

    private fun loadForm() {
        launch(coroutineDispatcherIo) {
            noAdsPurchases.noAdsActive.collect {
                if (isActive) {
                    onAdsFlowChanged(it)
                }
            }
        }

        launch {
            gdprConsent.loadForm().collect {
                when (it) {
                    false -> showGdprPopUp()
                    true -> showForm()
                }
            }
        }
    }

    private fun showForm() {
        launch {
            gdprConsent.showForm().collect {
                when (it) {
                    false -> showGdprPopUp()
                    true -> startLoadingInterstitial()
                }
            }
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
        when {
            finished -> finish()
            ads.interstitialAd.isLoaded -> onLoadInterstitialResult(true)
            else -> loadInterstitial()
        }
    }

    private fun loadInterstitial() {
        splashTimer.startInterstitialTimer { onLoadInterstitialResult(false) }
        launch {
            ads.interstitialAd.load().collect {
                if (isActive) {
                    splashTimer.cancelTimers()
                    onLoadInterstitialResult(it)
                }
            }
        }
    }

    private fun onLoadInterstitialResult(success: Boolean) = if (success) onInterstitialLoaded() else finish()

    private fun onInterstitialLoaded() {
        d("()")
        ads.interstitialAd.showIfCanBeShowed(true) { finish() }
        cancelJobs()
    }

    private fun cancelJobs() {
        if (viewModelScope.isActive) {
            viewModelScope.cancel("CancelJobs")
        }
    }

    private fun checkConsentNextLaunch() {
        launch {
            gdprConsent.requestGDPRLocation().collect {
                if (isActive) {
                    d("requestGDPRLocation: + $it")
                    splashTimer.cancelConsentTimer()
                    if (it == CheckGDPRLocationStatus.UE) {
                        splashTimer.cancelTimers()
                        showGdprPopUp()
                    } else if (it == CheckGDPRLocationStatus.FORM_SHOW_REQUIRED) {
                        splashTimer.cancelTimers()
                        loadForm()
                    }
                }
            }
        }
    }

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