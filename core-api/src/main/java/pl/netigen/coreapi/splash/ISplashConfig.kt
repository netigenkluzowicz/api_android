package pl.netigen.coreapi.splash

/**
 * Base Splash configuration
 *
 */
interface ISplashConfig {
    /**
     * Set if there is or isn't no-ads payment in application
     */
    val isNoAdsAvailable: Boolean

    /**
     * Max white time for load splash interstitial ad, it is start counting after consent operations are finished
     *
     * Default value: [7 seconds][DEFAULT_SPLASH_MAX_LOAD_INTERSTITIAL_WAIT_TIME_MS]
     */
    val maxInterstitialWaitTime: Long

    companion object {
        const val DEFAULT_SPLASH_MAX_LOAD_INTERSTITIAL_WAIT_TIME_MS = 7_000L
    }
}