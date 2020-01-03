package pl.netigen.core.ads

import android.widget.RelativeLayout

interface IAds {
    fun setConsentStatus(personalizedAdsApproved: Boolean)
    fun disable()
    fun enable()
    val bannerRelativeLayout : RelativeLayout
    val bannerAd : IBannerAd
    val interstitialAd : IInterstitialAd
}