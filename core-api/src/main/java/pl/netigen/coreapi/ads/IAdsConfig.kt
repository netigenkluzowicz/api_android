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
        /**
         * Minimum time after one [IInterstitialAd] ad was showed to show another ad, for default 60 seconds
         */
        const val DEFAULT_DELAY_BETWEEN_INTERSTITIAL_ADS_MS = 60_000L

        /**
         * Maximum number of reload tries of [IRewardedAd] after loading ad failure
         */
        const val REWARD_AD_MAX_RETRY_COUNT = 2
    }
}