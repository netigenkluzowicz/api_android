package pl.netigen.core.config

import pl.netigen.coreapi.main.IAppConfig
import pl.netigen.coreapi.splash.ISplashConfig.Companion.DEFAULT_MAX_CONSENT_WAIT_TIME_MS
import pl.netigen.coreapi.splash.ISplashConfig.Companion.DEFAULT_SPLASH_MAX_LOAD_INTERSTITIAL_WAIT_TIME_MS

class AppConfig(
    override val bannerAdId: String,
    override val interstitialAdId: String,
    override val rewardedAdId: String = "",
    override val bannerLayoutIdName: String = "adsLayout",
    override val isBannerAdaptive: Boolean = true,
    override val testDevices: List<String> = emptyList(),
    override val inDebugMode: Boolean = false,
    override val adMobPublisherIds: Array<String> = arrayOf("pub-4699516034931013"),
    override val isNoAdsAvailable: Boolean = true,
    override val maxConsentWaitTime: Long = DEFAULT_MAX_CONSENT_WAIT_TIME_MS,
    override val maxInterstitialWaitTime: Long = DEFAULT_SPLASH_MAX_LOAD_INTERSTITIAL_WAIT_TIME_MS,
    isSamsung: Boolean = false
) : IAppConfig {

    init {
        marketLinkPrefix = if (isSamsung) "samsungapps://ProductDetail/" else "market://details?id="
    }

    companion object {
        lateinit var marketLinkPrefix: String
    }
}