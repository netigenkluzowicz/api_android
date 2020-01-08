package pl.netigen.core.splash

import pl.netigen.coreapi.splash.ISplashTimer
import java.util.*
import kotlin.concurrent.schedule

class SplashTimer(
    private val consentTimeLimit: Long = DEFAULT_MAX_CONSENT_WAIT_TIME_MS,
    private val maxInterstitialWaitTime: Long = DEFAULT_MAX_LOAD_INTERSTITIAL_WAIT_TIME_MS
) : ISplashTimer {
    private var consentTimer: TimerTask? = null
    private var interstitialTimer: TimerTask? = null

    override fun startConsentTimer(onConsentTimeLimit: () -> Unit) {
        consentTimer?.cancel()
        consentTimer = Timer().schedule(consentTimeLimit) { onConsentTimeLimit() }
    }

    override fun startInterstitialTimer(onLoadSplashLimit: () -> Unit) {
        interstitialTimer?.cancel()
        interstitialTimer = Timer().schedule(maxInterstitialWaitTime) { onLoadSplashLimit() }
    }

    override fun cancelConsentTimer() {
        consentTimer?.cancel()
    }

    override fun cancelInterstitialTimer() {
        interstitialTimer?.cancel()
    }

    override fun cancelTimers() {
        cancelConsentTimer()
        cancelInterstitialTimer()
    }

    companion object {
        const val DEFAULT_MAX_CONSENT_WAIT_TIME_MS = 5000L
        const val DEFAULT_MAX_LOAD_INTERSTITIAL_WAIT_TIME_MS = 7000L
    }
}