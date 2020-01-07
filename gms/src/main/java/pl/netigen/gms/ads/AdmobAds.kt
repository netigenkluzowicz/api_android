package pl.netigen.gms.ads

import android.os.Bundle
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.ads.mediation.admob.AdMobAdapter
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import pl.netigen.coreapi.ads.AdId
import pl.netigen.coreapi.ads.IAds
import pl.netigen.coreapi.ads.IBannerAd
import pl.netigen.coreapi.ads.IInterstitialAd

class AdmobAds(
    activity: AppCompatActivity,
    bannerId: String,
    interstitialAdId: String,
    bannerRelativeLayout: RelativeLayout,
    private val testDevices: List<String> = emptyList(),
    private val isInDebugMode: Boolean = false,
    private var personalizedAdsApproved: Boolean = false
) : IAds, IAdmobRequest {
    override val bannerAd: IBannerAd
    override val interstitialAd: IInterstitialAd

    init {
        MobileAds.initialize(activity)
        bannerAd = AdmobBanner(activity, this, AdId(bannerId), bannerRelativeLayout)
        interstitialAd = AdmobInterstitial(activity, this, AdId(interstitialAdId))
    }

    override fun getAdRequest(): AdRequest {
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
