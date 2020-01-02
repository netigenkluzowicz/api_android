package pl.netigen.splash

interface ISplashTimer {
    fun startConsentTimer(onConsentTimeLimit: () -> Unit)
    fun startInterstitialTimer(onLoadSplashLimit: () -> Unit)
    fun cancelConsentTimer()
    fun cancelInterstitialTimer()
    fun cancelTimers()
}