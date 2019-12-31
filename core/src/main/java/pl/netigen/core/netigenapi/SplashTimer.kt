package pl.netigen.core.netigenapi

import java.util.*
import kotlin.concurrent.timer

class SplashTimer : ISplashTimer {
    private var timer: Timer? = null

    override
    fun startConsentTimer(onConsentTimeLimit: () -> Unit) {
        timer = timer(period = MAX_CONSENT_WAIT_TIME_MS) { onConsentTimeLimit() }
    }

    override fun startInterstitialTimer(onLoadSplashLimit: () -> Unit) {
        timer = timer(period = MAX_LOAD_INTERSTITIAL_WAIT_TIME_MS) { onLoadSplashLimit() }
    }

    override fun stopTimer() {
        timer?.cancel()
    }
}