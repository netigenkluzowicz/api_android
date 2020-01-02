package pl.netigen.core.ads

interface IAds : IInterstitialAds {
    fun setConsentStatus(personalizedAdsApproved: Boolean)
}