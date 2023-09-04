package pl.netigen.coreapi.ads

import android.widget.RelativeLayout

/**
 * Keeps configuration for Ads
 * - ads identifiers,
 * - banner type and layout id
 * - list of test devices
 * - debug/release configuration
 */
interface IAdsConfig : YandexAdsConfig {
    /**
     * [IBannerAd] ad identifier
     */
    val bannerAdId: String

    /**
     * [IInterstitialAd] ad identifier
     */
    val interstitialAdId: String

    /**
     * [rewardedAdId] ad identifier,
     *
     * for default set to empty String (""), indicating it is not used in application
     */
    val rewardedAdId: String

    /**
     * Id of [RelativeLayout] for banner ad placement
     */
    val bannerLayoutIdName: String

    /**
     * List of test devices ids
     *
     * See: [Admob instruction](https://developers.google.com/admob/android/test-ads#enable_test_devices)
     */
    val testDevices: List<String>

    /**
     * Sets debug mode on/off, when:
     * - true - test ads Ids provided by Admob or HMS Ads will be used on all devices
     * - false - production ads will be displayed excluding test devices provided in [testDevices]
     */
    val inDebugMode: Boolean

    val debugForceYandex : Boolean

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
