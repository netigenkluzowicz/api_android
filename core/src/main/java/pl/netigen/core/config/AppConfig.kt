package pl.netigen.core.config

import pl.netigen.core.config.AppConfig.Companion.marketLinkPrefix
import pl.netigen.coreapi.main.IAppConfig
import pl.netigen.coreapi.main.IAppConfig.Companion.DEFAULT_DAYS_FOR_FLEXIBLE_UPDATE
import pl.netigen.coreapi.main.Store
import pl.netigen.coreapi.splash.ISplashConfig.Companion.DEFAULT_SPLASH_MAX_LOAD_INTERSTITIAL_WAIT_TIME_MS

/**
 * [IAppConfig] implementation which provides also [marketLinkPrefix] for linking Application to current [Store]
 *
 */
open class AppConfig(
    override val bannerAdId: String,
    override val interstitialAdId: String,
    override val rewardedAdId: String = "",
    override val bannerYandexAdId: String = "",
    override val interstitialYandexAdId: String = "",
    override val rewardedYandexAdId: String = "",
    final override val store: Store,
    override val bannerLayoutIdName: String = "adsLayout",
    override val testDevices: List<String> = emptyList(),
    override val inDebugMode: Boolean = false,
    override val inYandexDebugMode: Boolean = false,
    override val isNoAdsAvailable: Boolean = true,
    override val donateActive: Boolean = true,
    override val maxInterstitialWaitTime: Long = DEFAULT_SPLASH_MAX_LOAD_INTERSTITIAL_WAIT_TIME_MS,
    override val daysForFlexibleUpdate: Int = DEFAULT_DAYS_FOR_FLEXIBLE_UPDATE,
    override val showInterstitialAdOnSplash: Boolean = true,
) : IAppConfig {

    init {
        marketLinkPrefix = when (store) {
            Store.SAMSUNG -> "samsungapps://ProductDetail/"
            Store.AMAZON -> "amzn://apps/android?p="
            else -> "market://details?id="
        }
    }

    companion object {
        /**
         * Used for linking Application to current [Store],
         * as in [Utils.openMarketLink()][pl.netigen.core.utils.Utils.openMarketLink]

         */
        lateinit var marketLinkPrefix: String
        const val NETIGEN_ADMOB_PUBLISHER_ID = "pub-4699516034931013"
    }
}
