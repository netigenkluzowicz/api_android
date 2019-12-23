package pl.netigen.core.netigenapi

interface ISplashTimer {
    fun startConsentTimer()
    fun startInterstitialTimer()
    fun stopTimer()
    val onConsentTimeLimit: () -> Unit
    val onLoadSplashLimit: () -> Unit
    fun destroy()
}

const val MAX_CONSENT_WAIT_TIME_MS = 5000L
const val MAX_LOAD_INTERSTITIAL_WAIT_TIME_MS = 7000L