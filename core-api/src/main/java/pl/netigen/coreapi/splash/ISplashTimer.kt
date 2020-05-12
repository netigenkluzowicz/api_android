package pl.netigen.coreapi.splash

/**
 * Timer utils for splash
 *
 */
interface ISplashTimer {
    fun startConsentTimer(onConsentTimeLimit: () -> Unit)
    fun startInterstitialTimer(onLoadSplashLimit: () -> Unit)
    fun cancelConsentTimer()
    fun cancelInterstitialTimer()
    fun cancelTimers()
}