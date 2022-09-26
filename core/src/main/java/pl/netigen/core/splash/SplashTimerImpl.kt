package pl.netigen.core.splash

import pl.netigen.coreapi.splash.ISplashConfig
import pl.netigen.coreapi.splash.ISplashConfig.Companion.DEFAULT_SPLASH_MAX_LOAD_INTERSTITIAL_WAIT_TIME_MS
import pl.netigen.coreapi.splash.ISplashTimer
import java.util.*
import kotlin.concurrent.schedule

/**
 * [ISplashTimer] implementation
 *
 * @property maxInterstitialWaitTime see: [ISplashConfig.maxInterstitialWaitTime]
 */
class SplashTimerImpl(
    private val maxInterstitialWaitTime: Long = DEFAULT_SPLASH_MAX_LOAD_INTERSTITIAL_WAIT_TIME_MS,
) : ISplashTimer {
    private var interstitialTimer: TimerTask? = null

    override fun startInterstitialTimer(onLoadSplashLimit: () -> Unit) {
        interstitialTimer?.cancel()
        interstitialTimer = Timer().schedule(maxInterstitialWaitTime) { onLoadSplashLimit() }
    }

    override fun cancelInterstitialTimer() {
        interstitialTimer?.cancel()
    }
}
