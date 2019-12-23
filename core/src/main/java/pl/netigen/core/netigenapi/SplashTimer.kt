package pl.netigen.core.netigenapi

import java.util.*
import kotlin.concurrent.timer

class SplashTimer(
    override var onConsentTimeLimit: () -> Unit,
    override var onLoadSplashLimit: () -> Unit
) : ISplashTimer {
    private var timer: Timer? = null

    override fun startConsentTimer() {
        timer = timer(period = MAX_CONSENT_WAIT_TIME_MS) { onConsentTimeLimit() }
    }

    override fun startInterstitialTimer() {
        timer = timer(period = MAX_LOAD_INTERSTITIAL_WAIT_TIME_MS) { onLoadSplashLimit() }
    }

    override fun stopTimer() {
        timer?.cancel()
    }

    override fun destroy() {
        timer?.cancel()
        onConsentTimeLimit = {}
        onLoadSplashLimit = {}
    }
}