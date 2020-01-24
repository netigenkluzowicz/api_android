package pl.netigen.coreapi.main

import pl.netigen.coreapi.ads.IAdsConfig
import pl.netigen.coreapi.gdpr.IGDPRConsentConfig

interface IAppConfig : IAdsConfig, IGDPRConsentConfig {
     val isNoAdsAvailable: Boolean
}