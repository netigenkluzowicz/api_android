package pl.netigen.core.splash

import pl.netigen.coreapi.splash.ISplashTimer
import java.util.*
import kotlin.concurrent.schedule

class SplashTimer(
    private val maxInterstitialWaitTime: Long = DEFAULT_MAX_LOAD_INTERSTITIAL_WAIT_TIME_MS
) : ISplashTimer {
    private var interstitialTimer: TimerTask? = null

    override fun startInterstitialTimer(onLoadSplashLimit: () -> Unit) {
        interstitialTimer?.cancel()
        interstitialTimer = Timer().schedule(maxInterstitialWaitTime) { onLoadSplashLimit() }
    }

    override fun cancelInterstitialTimer() {
        interstitialTimer?.cancel()
    }

    override fun cancelTimers() {
        cancelInterstitialTimer()
    }

    companion object {
        const val DEFAULT_MAX_LOAD_INTERSTITIAL_WAIT_TIME_MS = 7000L
    }
}