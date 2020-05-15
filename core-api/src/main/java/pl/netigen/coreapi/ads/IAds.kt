package pl.netigen.coreapi.ads

/**
 * Universal interface for managing ads, such as:
 *
 *  - displaying and loading [IBannerAd], [IInterstitialAd] and [IRewardedAd]
 *  - turning on/off personalized ads
 *  - enabling and disabling all ads
 *  - provides time minimum limit between IInterstitialAd displays
 *
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
     * Disable all ads, hide banners, cancel and/or stop loading ads
     *
     */
    fun disable()

    /**
     * Enables ads, starts loading and showing them
     *
     */
    fun enable()
}