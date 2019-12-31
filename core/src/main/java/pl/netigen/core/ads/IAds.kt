package pl.netigen.core.ads

interface IAds {
    fun addLoadInterstitialListener(loadInterstitialListener: LoadInterstitialListener)
    fun removeInterstitialListener(loadInterstitialListener: LoadInterstitialListener)
    fun loadInterstitialAd()
    fun showInterstitialAd(onClosedOrNotShowed: (Boolean) -> Unit)
    fun setConsentStatus(personalizedAdsApproved: Boolean)
    fun onNoAdsBought()
}