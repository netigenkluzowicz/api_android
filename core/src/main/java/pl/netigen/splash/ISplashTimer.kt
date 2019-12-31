package pl.netigen.splash

interface ISplashTimer {
    fun startConsentTimer(onConsentTimeLimit: () -> Unit)
    fun startInterstitialTimer(onLoadSplashLimit: () -> Unit)
    fun cancelConsentTimer()
    fun cancelInterstitialTimer()
    fun cancelTimers()
}

const val MAX_CONSENT_WAIT_TIME_MS = 5000L
const val MAX_LOAD_INTERSTITIAL_WAIT_TIME_MS = 7000L