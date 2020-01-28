package pl.netigen.coreapi.ads

interface IAdsConfig {
    val bannerAdId: String
    val interstitialAdId: String
    val rewardedAdId: String
    val isBannerAdaptive: Boolean
    val bannerLayoutIdName : String
    val testDevices: List<String>
    val inDebugMode: Boolean
}