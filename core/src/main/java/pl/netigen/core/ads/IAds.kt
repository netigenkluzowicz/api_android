package pl.netigen.core.ads

interface IAds {
    fun addLoadInterstitialListener(loadInterstitialListener: LoadInterstitialListener)
    fun removeInterstitialListener(loadInterstitialListener: LoadInterstitialListener)
    fun loadInterstitial()
    fun showInterstitial(onClosedOrNotShowed: (Boolean) -> Unit)
}