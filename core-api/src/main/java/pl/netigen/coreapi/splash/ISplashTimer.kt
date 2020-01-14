package pl.netigen.coreapi.splash

interface ISplashTimer {
    fun startInterstitialTimer(onLoadSplashLimit: () -> Unit)
    fun cancelInterstitialTimer()
    fun cancelTimers()
}