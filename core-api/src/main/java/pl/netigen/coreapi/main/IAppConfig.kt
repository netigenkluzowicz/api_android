package pl.netigen.coreapi.main

import pl.netigen.coreapi.ads.IAdsConfig
import pl.netigen.coreapi.gdpr.IGDPRConsentConfig
import pl.netigen.coreapi.rateus.IRateUs
import pl.netigen.coreapi.splash.ISplashConfig

/**
 * Keeps configuration for entire Api/Application:
 * - Ads configuration [IAdsConfig]
 * - UE GDPR consent configuration [IGDPRConsentConfig]
 * - splash base configuration [ISplashConfig]
 * - if no-ads payment is available in application
 * - if debug mode is enabled for ads and payments
 * - target [Store] for application release
 * - if use default ["Rate us"][IRateUs]
 *
 */
interface IAppConfig : IAdsConfig, IGDPRConsentConfig, ISplashConfig {
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
     * Indicates If use default ["Rate us"][IRateUs]
     */
    val useDefaultRateUs: Boolean
}