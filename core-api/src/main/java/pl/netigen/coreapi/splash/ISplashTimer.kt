package pl.netigen.coreapi.splash

/**
 * Timer utils for [CoreSplashVMImpl][pl.netigen.core.splash.CoreSplashVMImpl]
 *
 */
interface ISplashTimer {
    /**
     * Starts consent timer
     *
     * see: [ISplashConfig.maxConsentWaitTime]
     *
     * @param onConsentTimeLimit is called when time for fetch consent expires
     */
    fun startConsentTimer(onConsentTimeLimit: () -> Unit)

    /**
     * Starts splash interstitial ad timer
     *
     * see: [ISplashConfig.maxInterstitialWaitTime]
     *
     * @param onLoadSplashLimit is called when time for load interstitial ad expires
     */
    fun startInterstitialTimer(onLoadSplashLimit: () -> Unit)
    fun cancelConsentTimer()
    fun cancelInterstitialTimer()
    fun cancelTimers()
}