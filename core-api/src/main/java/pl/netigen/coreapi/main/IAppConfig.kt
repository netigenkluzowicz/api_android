package pl.netigen.coreapi.main

import pl.netigen.coreapi.ads.IAdsConfig
import pl.netigen.coreapi.rateus.IRateUs
import pl.netigen.coreapi.splash.ISplashConfig

/**
 * Keeps configuration for entire Api/Application:
 * - Ads configuration [IAdsConfig]
 * - splash base configuration [ISplashConfig]
 * - if no-ads payment is available in application
 * - if debug mode is enabled for ads and payments
 * - target [Store] for application release
 * - if use default ["Rate us"][IRateUs]
 *
 */
interface IAppConfig : IAdsConfig, ISplashConfig {
    override val isNoAdsAvailable: Boolean

    /**
     * Sets debug mode on/off, when:
     * - true - test ads will be displayed, and GMS Payments will be showing Toast debug messages
     * - false - production ads will be displayed excluding test devices provided in [IAdsConfig.testDevices]
     */
    override val inDebugMode: Boolean

    /**
     * Current release target Store, one of:
     * - [Google Play](https://play.google.com/store)
     * - [Samsung Galaxy Store](https://www.samsung.com/apps/galaxy-store/)
     * - [Huawei AppGallery](https://huaweimobileservices.com/appgallery/)
     */
    val store: Store

    /**
     * Number of days after which the information about the application update will be shown to the user
     */
    val daysForFlexibleUpdate: Int

    companion object {
        const val DEFAULT_DAYS_FOR_FLEXIBLE_UPDATE: Int = 3
    }
}