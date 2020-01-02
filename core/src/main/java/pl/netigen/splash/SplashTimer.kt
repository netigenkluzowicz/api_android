package pl.netigen.splash

import java.util.*
import kotlin.concurrent.timer

class SplashTimer(
    private val consentTimeLimit: Long = DEFAULT_MAX_CONSENT_WAIT_TIME_MS,
    private val maxInterstitialWaitTime: Long = DEFAULT_MAX_LOAD_INTERSTITIAL_WAIT_TIME_MS
) : ISplashTimer {
    private var consentTimer: Timer? = null
    private var interstitialTimer: Timer? = null

    override fun startConsentTimer(onConsentTimeLimit: () -> Unit) {
        consentTimer = timer(period = consentTimeLimit) { onConsentTimeLimit() }
    }

    override fun startInterstitialTimer(onLoadSplashLimit: () -> Unit) {
        interstitialTimer = timer(period = maxInterstitialWaitTime) { onLoadSplashLimit() }
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
}

const val DEFAULT_MAX_CONSENT_WAIT_TIME_MS = 5000L
const val DEFAULT_MAX_LOAD_INTERSTITIAL_WAIT_TIME_MS = 7000L