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
    private val splashTimer: ISplashTimer = SplashTimerImpl(appConfig.maxInterstitialWaitTime),
    val coroutineDispatcherIo: CoroutineDispatcher = Dispatchers.IO
) : SplashVM(application), INoAds by noAdsPurchases, IAppConfig by appConfig {
    private var currentState = SplashState.UNINITIALIZED
    override val splashState: MutableLiveData<SplashState> = MutableLiveData(currentState)
    override val isFirstLaunch: MutableLiveData<Boolean> = MutableLiveData(false)
    private var isRunning = false
    private var finished = false
    private var isPurchased = false

    override fun start() {
        d("()")
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
        if (!networkStatus.isConnectedOrConnecting || finished) return finish()
        loadGdprStatus()
        loadInterstitial()
    }

    private fun loadGdprStatus() {
        d("()")
        gdprConsent.requestGDPRLocation {
            when (it) {
                CheckGDPRLocationStatus.UE -> loadForm()
                CheckGDPRLocationStatus.NON_UE -> setPersonalizedAds(true)
                CheckGDPRLocationStatus.ERROR -> setPersonalizedAds(false)
            }
        }
    }

    private fun onAdsFlowChanged(purchased: Boolean) {
        d("purchased = [$purchased]")
        isPurchased = purchased
        if (purchased) {
            finish()
        }
    }

    private fun finish() {
        d("()")
        cancelJobs()
        splashTimer.cancelInterstitialTimer()
        finished = true
        updateState(SplashState.FINISHED)
    }


    private fun updateState(splashState: SplashState) {
        currentState = splashState
        this.splashState.postValue(currentState)
    }

    private fun loadForm() {
        d("()")
        if (isPurchased) return
        gdprConsent.loadGdpr {
            when (it) {
                false -> updateState(SplashState.LOADING)
                true -> showForm()
            }
        }
    }

    private fun showForm() {
        d("()")
        if (isPurchased) return
        updateState(SplashState.SHOW_GDPR_POP_UP)
        gdprConsent.showGdpr {
            when (it) {
                PERSONALIZED_NON_UE -> setPersonalizedAds(true)
                PERSONALIZED_SHOWED -> setPersonalizedAds(true)
                NON_PERSONALIZED_SHOWED -> setPersonalizedAds(false)
                UNINITIALIZED -> setPersonalizedAds(false)
                NON_PERSONALIZED_ERROR -> setPersonalizedAds(false)
            }
            ads.interstitialAd.showIfCanBeShowed(true) { finish() }
        }
    }

    private fun loadInterstitial() {
        d("()")
        splashTimer.startInterstitialTimer {
            if (currentState != SplashState.SHOW_GDPR_POP_UP) {
                onLoadInterstitialResult(false)
            }
        }
        ads.interstitialAd.load {
            splashTimer.cancelInterstitialTimer()
            if (!finished && currentState != SplashState.SHOW_GDPR_POP_UP) {
                onLoadInterstitialResult(it)
            }
        }
    }

    private fun onLoadInterstitialResult(success: Boolean) = if (success) onInterstitialLoaded() else finish()

    private fun onInterstitialLoaded() {
        d("()")
        ads.interstitialAd.showIfCanBeShowed(true) { finish() }
    }

    private fun cancelJobs() {
        d("()")
        if (viewModelScope.isActive) {
            viewModelScope.cancel("CancelJobs")
        }
    }

    override fun setPersonalizedAds(personalizedAdsApproved: Boolean) {
        d("personalizedAdsApproved = [$personalizedAdsApproved]")
        val adConsentStatus: AdConsentStatus = if (personalizedAdsApproved) PERSONALIZED_SHOWED else NON_PERSONALIZED_SHOWED
        gdprConsent.saveAdConsentStatus(adConsentStatus)
        ads.personalizedAdsEnabled = personalizedAdsApproved
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