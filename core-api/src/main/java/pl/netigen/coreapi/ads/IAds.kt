package pl.netigen.coreapi.ads

/**
 * Universal interface for managing ads, such as:
 *  - initializing ads SDk
 *  - displaying and loading [IBannerAd], [IInterstitialAd] and [IRewardedAd]
 *  - turning on/off personalized ads
 *  - enabling and disabling all ads
 *  - provides time minimum limit between IInterstitialAd displays
 *  - showing test/production ads
 *
 * See: [Mobile Ads SDK](https://developer.huawei.com/consumer/en/monetize)
 *
 * See: [HUAWEI Ads SDK](https://developer.huawei.com/consumer/en/doc/development/HMS-Guides/ads-sdk-guide)
 */
interface IAds {
    /**
     * Set this to turn off/on personalized ads
     */
    var personalizedAdsEnabled: Boolean

    /**
     * Provides [IBannerAd]
     */
    val bannerAd: IBannerAd

    /**
     * Provides [IInterstitialAd]
     */
    val interstitialAd: IInterstitialAd

    /**
     * Provides [IRewardedAd]
     */
    val rewardedAd: IRewardedAd

    /**
     * Provides [IOpenAppAd]
     */
    val openAppAd: IOpenAppAd

    /**
     * Disable all ads, hide banners, cancel and/or stop loading ads
     *
     */
    fun disable()

    /**
     * Enables ads, starts loading and showing them
     *
     */
    fun enable()
    fun interstitialAdIsInBackground(isInBackground: Boolean)
}
