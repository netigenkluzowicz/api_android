package pl.netigen.core.netigenapi

import java.util.*
import kotlin.concurrent.timer

class SplashTimer : ISplashTimer {
    private var consentTimer: Timer? = null
    private var interstitialTimer: Timer? = null

    override
    fun startConsentTimer(onConsentTimeLimit: () -> Unit) {
        consentTimer = timer(period = MAX_CONSENT_WAIT_TIME_MS) { onConsentTimeLimit() }
    }

    override fun startInterstitialTimer(onLoadSplashLimit: () -> Unit) {
        interstitialTimer = timer(period = MAX_LOAD_INTERSTITIAL_WAIT_TIME_MS) { onLoadSplashLimit() }
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