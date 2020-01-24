package pl.netigen.core.config

import pl.netigen.coreapi.main.IAppConfig
import pl.netigen.coreapi.splash.ISplashConfig.Companion.DEFAULT_MAX_CONSENT_WAIT_TIME_MS
import pl.netigen.coreapi.splash.ISplashConfig.Companion.DEFAULT_MAX_LOAD_INTERSTITIAL_WAIT_TIME_MS

class AppConfig(
    override val bannerAdId: String,
    override val interstitialAdId: String,
    override val isBannerAdaptive: Boolean = true,
    override val testDevices: List<String> = emptyList(),
    override val inDebugMode: Boolean = false,
    override val adMobPublisherIds: Array<String> = arrayOf("pub-4699516034931013"),
    override val isNoAdsAvailable: Boolean = true,
    override val maxConsentWaitTime: Long = DEFAULT_MAX_CONSENT_WAIT_TIME_MS,
    override val maxInterstitialWaitTime: Long = DEFAULT_MAX_LOAD_INTERSTITIAL_WAIT_TIME_MS
) : IAppConfig {
    companion object {
        lateinit var marketLinkPrefix: String
    }
}