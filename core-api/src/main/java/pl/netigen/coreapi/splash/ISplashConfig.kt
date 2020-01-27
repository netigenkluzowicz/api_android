package pl.netigen.coreapi.splash

interface ISplashConfig {
    val isNoAdsAvailable: Boolean
    val maxConsentWaitTime: Long
    val maxInterstitialWaitTime: Long

    companion object {
        const val DEFAULT_MAX_CONSENT_WAIT_TIME_MS = 5000L
        const val DEFAULT_SPLASH_MAX_LOAD_INTERSTITIAL_WAIT_TIME_MS = 7000L
    }
}