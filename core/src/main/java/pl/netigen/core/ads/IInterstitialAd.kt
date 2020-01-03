package pl.netigen.core.ads

interface IInterstitialAd : IAd {
    fun addInterstitialListener(interstitialAdListener: InterstitialAdListener)
    fun removeInterstitialListener(interstitialAdListener: InterstitialAdListener)
    fun loadInterstitialAd()
    fun showInterstitialAd(onClosedOrNotShowed: (Boolean) -> Unit)
}