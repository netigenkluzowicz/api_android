package pl.netigen.coreapi.ads

interface IAdsConfig {
    val bannerAdId: String
    val interstitialAdId: String
    val isAdaptiveBanner: Boolean
    val testDevices: List<String>
    val inDebugMode: Boolean
}