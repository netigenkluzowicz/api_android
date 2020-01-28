package pl.netigen.coreapi.ads

interface IRewardedAd : IAd {
    val cashedAdsCount: Int
    val isLoaded: Boolean
    fun showRewardedAd(onRewardResult: (Boolean) -> Unit)
}