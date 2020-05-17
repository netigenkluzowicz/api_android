package pl.netigen.core.config

import pl.netigen.coreapi.main.IAppConfig
import pl.netigen.coreapi.main.Store
import pl.netigen.coreapi.splash.ISplashConfig.Companion.DEFAULT_MAX_CONSENT_WAIT_TIME_MS
import pl.netigen.coreapi.splash.ISplashConfig.Companion.DEFAULT_SPLASH_MAX_LOAD_INTERSTITIAL_WAIT_TIME_MS

/**
 * [IAppConfig] implementation which provides also [marketLinkPrefix] for linking Application to current [Store]
 *
 */
open class AppConfig(
    override val bannerAdId: String,
    override val interstitialAdId: String,
    override val rewardedAdId: String = "",
    override val bannerLayoutIdName: String = "adsLayout",
    override val isBannerAdaptive: Boolean = true,
    override val testDevices: List<String> = emptyList(),
    override val inDebugMode: Boolean = false,
    override val adMobPublisherIds: Array<String> = arrayOf(NETIGEN_ADMOB_PUBLISHER_ID),
    override val isNoAdsAvailable: Boolean = true,
    override val maxConsentWaitTime: Long = DEFAULT_MAX_CONSENT_WAIT_TIME_MS,
    override val maxInterstitialWaitTime: Long = DEFAULT_SPLASH_MAX_LOAD_INTERSTITIAL_WAIT_TIME_MS,
    final override val store: Store
) : IAppConfig {

    init {
        marketLinkPrefix = if (store == Store.SAMSUNG) "samsungapps://ProductDetail/" else "market://details?id="
    }

    companion object {
        /**
         * Used for linking Application to current [Store],
         * as in [Utils.openMarketLink()][pl.netigen.core.utils.Utils.openMarketLink]
         *
         *
         */
        lateinit var marketLinkPrefix: String
        const val NETIGEN_ADMOB_PUBLISHER_ID = "pub-4699516034931013"
    }
}