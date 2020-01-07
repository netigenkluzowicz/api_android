package pl.netigen.core.admob

import android.app.Activity
import android.os.Bundle
import com.google.ads.mediation.admob.AdMobAdapter
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import pl.netigen.core.ads.IAds
import pl.netigen.core.ads.IBannerAd
import pl.netigen.core.ads.IInterstitialAd

class AdmobAds(
    activity: Activity,
    override val bannerAd: IBannerAd,
    override val interstitialAd: IInterstitialAd,
    private val testDevices: List<String> = emptyList(),
    private val isInDebugMode: Boolean = true,
    private var personalizedAdsApproved: Boolean = false
) : IAds {

    init {
        MobileAds.initialize(activity)
    }

    fun getAdRequest(): AdRequest {
        val builder = AdRequest.Builder()
        if (isInDebugMode) {
            for (i in testDevices.indices) {
                builder.addTestDevice(testDevices[i])
            }
        }
        if (personalizedAdsApproved) return builder.build()

        val extras = Bundle()
        extras.putString("npa", "1")
        return builder.addNetworkExtrasBundle(AdMobAdapter::class.java, extras).build()
    }

    override fun enable() = setEnabled(true)

    override fun disable() = setEnabled(false)

    override fun setConsentStatus(personalizedAdsApproved: Boolean) {
        this.personalizedAdsApproved = personalizedAdsApproved
    }

    private fun setEnabled(enabled: Boolean) {
        bannerAd.enabled = enabled
        interstitialAd.enabled = enabled
    }
}
