package pl.netigen.coreapi.ads

interface IAds {
    var personalizedAdsEnabled: Boolean
    fun disable()
    fun enable()
    val bannerAd: IBannerAd
    val interstitialAd: IInterstitialAd
}