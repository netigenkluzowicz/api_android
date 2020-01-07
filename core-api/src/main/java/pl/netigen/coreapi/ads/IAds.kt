package pl.netigen.coreapi.ads

interface IAds {
    fun setConsentStatus(personalizedAdsApproved: Boolean)
    fun disable()
    fun enable()
    val bannerAd : IBannerAd
    val interstitialAd : IInterstitialAd
}