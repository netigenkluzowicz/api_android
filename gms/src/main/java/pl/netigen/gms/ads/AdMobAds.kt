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

class AdMobAds(
    activity: AppCompatActivity,
    bannerAdId: String,
    interstitialAdId: String,
    bannerRelativeLayout: RelativeLayout,
    override var personalizedAdsEnabled: Boolean = false,
    isAdaptiveBanner: Boolean = true,
    private val testDevices: List<String> = emptyList(),
    private val isInDebugMode: Boolean = false
) : IAds, IAdMobRequest {
    override val bannerAd: IBannerAd
    override val interstitialAd: IInterstitialAd

    init {
        MobileAds.initialize(activity)
        val (bannerId, interstitialId) = getIds(bannerAdId, interstitialAdId)
        bannerAd = AdmobBanner(activity, this, bannerId, bannerRelativeLayout, isAdaptiveBanner)
        interstitialAd = AdMobInterstitial(activity, this, interstitialId)
    }

    private fun getIds(bannerAdId: String, interstitialAdId: String): Pair<AdId<String>, AdId<String>> {
        val bannerId = if (isInDebugMode) AdId(TEST_BANNER_ID) else AdId(bannerAdId)
        val interstitialId = if (isInDebugMode) AdId(TEST_INTERSTITIAL_ID) else AdId(interstitialAdId)
        return Pair(bannerId, interstitialId)
    }

    override fun getAdRequest(): AdRequest {
        val builder = AdRequest.Builder()
        if (isInDebugMode) {
            for (i in testDevices.indices) {
                builder.addTestDevice(testDevices[i])
            }
        }
        if (personalizedAdsEnabled) return builder.build()

        val extras = Bundle()
        extras.putString("npa", "1")
        return builder.addNetworkExtrasBundle(AdMobAdapter::class.java, extras).build()
    }

    override fun enable() = setEnabled(true)

    override fun disable() = setEnabled(false)

    private fun setEnabled(enabled: Boolean) {
        bannerAd.enabled = enabled
        interstitialAd.enabled = enabled
    }

    companion object {
        const val TEST_BANNER_ID: String = "ca-app-pub-3940256099942544/6300978111"
        const val TEST_INTERSTITIAL_ID: String = "ca-app-pub-3940256099942544/1033173712"
        const val TEST_REWARDED_ID: String = "ca-app-pub-3940256099942544/5224354917"
    }
}
