package pl.netigen.coreapi.main

import pl.netigen.coreapi.ads.IAdsConfig
import pl.netigen.coreapi.gdpr.IGDPRConsentConfig
import pl.netigen.coreapi.splash.ISplashConfig

interface IAppConfig : IAdsConfig, IGDPRConsentConfig, ISplashConfig {
    override val isNoAdsAvailable: Boolean
    override val inDebugMode: Boolean
}