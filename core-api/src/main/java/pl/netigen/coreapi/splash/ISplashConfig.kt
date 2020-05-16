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
     * Max white time on first splash launch to fetch consent information from web services
     *
     * Default value: [5 seconds][DEFAULT_MAX_CONSENT_WAIT_TIME_MS]
     */
    val maxConsentWaitTime: Long

    /**
     * Max white time for load splash interstitial ad, it is start counting after consent operations are finished
     *
     * Default value: [7 seconds][DEFAULT_SPLASH_MAX_LOAD_INTERSTITIAL_WAIT_TIME_MS]
     */
    val maxInterstitialWaitTime: Long

    companion object {
        const val DEFAULT_MAX_CONSENT_WAIT_TIME_MS = 5_000L
        const val DEFAULT_SPLASH_MAX_LOAD_INTERSTITIAL_WAIT_TIME_MS = 7_000L
    }
}