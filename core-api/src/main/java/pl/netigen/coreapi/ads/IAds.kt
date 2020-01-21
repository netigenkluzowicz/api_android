package pl.netigen.coreapi.ads

interface IAds {
    var personalizedAdsEnabled: Boolean
    val bannerAd: IBannerAd
    val interstitialAd: IInterstitialAd

    fun disable()
    fun enable()
}