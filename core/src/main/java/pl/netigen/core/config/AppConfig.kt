package pl.netigen.core.config

import pl.netigen.coreapi.main.IAppConfig

class AppConfig(
    override val bannerAdId: String,
    override val interstitialAdId: String,
    override val isAdaptiveBanner: Boolean = true,
    override val testDevices: List<String> = emptyList(),
    override val inDebugMode: Boolean = false,
    override val adMobPublisherIds: Array<String> = arrayOf("pub-4699516034931013"),
    override val isNoAdsAvailable: Boolean = true
) : IAppConfig