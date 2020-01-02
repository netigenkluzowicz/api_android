package pl.netigen.core.ads

interface IAds : IInterstitialAd {
    fun setConsentStatus(personalizedAdsApproved: Boolean)
    fun destroy()
}