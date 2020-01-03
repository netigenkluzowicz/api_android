package pl.netigen.core.splash

interface ISplashTimer {
    fun startConsentTimer(onConsentTimeLimit: () -> Unit)
    fun startInterstitialTimer(onLoadSplashLimit: () -> Unit)
    fun cancelConsentTimer()
    fun cancelInterstitialTimer()
    fun cancelTimers()
}