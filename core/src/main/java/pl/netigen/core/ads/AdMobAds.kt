package pl.netigen.core.ads

import android.os.Bundle
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.ads.mediation.admob.AdMobAdapter
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import pl.netigen.coreapi.ads.IAds
import pl.netigen.coreapi.ads.IAdsConfig
import pl.netigen.coreapi.ads.IBannerAd
import pl.netigen.coreapi.ads.IInterstitialAd

class AdMobAds(
    activity: AppCompatActivity,
    bannerRelativeLayout: RelativeLayout,
    private val adsConfig: IAdsConfig,
    override var personalizedAdsEnabled: Boolean = false
) : IAds, IAdMobRequest {
    override val bannerAd: IBannerAd
    override val interstitialAd: IInterstitialAd

    init {
        MobileAds.initialize(activity)
        val (bannerId, interstitialId) = getIds(adsConfig.bannerAdId, adsConfig.interstitialAdId)
        bannerAd = AdMobBanner(activity, this, bannerId, bannerRelativeLayout, adsConfig.isAdaptiveBanner)
        interstitialAd = AdMobInterstitial(activity, this, interstitialId)
    }

    private fun getIds(banner: String, interstitial: String): Pair<String, String> {
        val bannerId = if (adsConfig.inDebugMode) (TEST_BANNER_ID) else (banner)
        val interstitialId = if (adsConfig.inDebugMode) (TEST_INTERSTITIAL_ID) else (interstitial)
        return Pair(bannerId, interstitialId)
    }

    override fun getAdRequest(): AdRequest {
        val builder = AdRequest.Builder()
        if (adsConfig.inDebugMode) {
            for (i in adsConfig.testDevices.indices) {
                builder.addTestDevice(adsConfig.testDevices[i])
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
