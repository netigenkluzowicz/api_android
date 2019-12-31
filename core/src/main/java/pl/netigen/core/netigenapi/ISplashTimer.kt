package pl.netigen.core.netigenapi

interface ISplashTimer {
    fun startConsentTimer(onConsentTimeLimit: () -> Unit)
    fun startInterstitialTimer(onLoadSplashLimit: () -> Unit)
    fun stopTimer()
}

const val MAX_CONSENT_WAIT_TIME_MS = 5000L
const val MAX_LOAD_INTERSTITIAL_WAIT_TIME_MS = 7000L