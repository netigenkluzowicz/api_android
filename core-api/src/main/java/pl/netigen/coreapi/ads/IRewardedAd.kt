package pl.netigen.coreapi.ads

interface IRewardedAd : IAd {
    val isLoaded: Boolean
    fun showRewardedAd(onRewardResult: (Boolean) -> Unit)
}