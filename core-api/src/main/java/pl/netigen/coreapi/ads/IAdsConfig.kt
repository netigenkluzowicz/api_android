package pl.netigen.coreapi.ads

interface IAdsConfig {
    val bannerAdId: String
    val interstitialAdId: String
    val rewardedAdId: String
    val isBannerAdaptive: Boolean
    val bannerLayoutIdName: String
    val testDevices: List<String>
    val inDebugMode: Boolean


    companion object {
        const val DEFAULT_DELAY_BETWEEN_INTERSTITIAL_ADS = 60L * 1000L
    }
}