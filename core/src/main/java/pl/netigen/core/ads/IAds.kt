package pl.netigen.core.ads

interface IAds : IInterstitialAd {
    fun setConsentStatus(personalizedAdsApproved: Boolean)
    fun disable()
    fun enable()
}