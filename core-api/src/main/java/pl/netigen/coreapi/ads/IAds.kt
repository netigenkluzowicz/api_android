package pl.netigen.coreapi.ads

interface IAds {
    var personalizedAdsEnabled: Boolean
    val bannerAd: IBannerAd
    val interstitialAd: IInterstitialAd
    val rewardedAd: IRewardedAd

    fun disable()
    fun enable()
}