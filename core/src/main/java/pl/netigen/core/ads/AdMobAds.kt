package pl.netigen.core.ads

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.google.ads.mediation.admob.AdMobAdapter
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import pl.netigen.coreapi.ads.*
import timber.log.Timber

class AdMobAds(
    activity: ComponentActivity,
    private val adsConfig: IAdsConfig,
    override var personalizedAdsEnabled: Boolean = false
) : IAds, IAdMobRequest {
    override val bannerAd: IBannerAd
    override val interstitialAd: IInterstitialAd
    override val rewardedAd: IRewardedAd

    init {
        Timber.d("()")
        MobileAds.initialize(activity)
        val (bannerId, interstitialId, rewardedId) = getIds(adsConfig.bannerAdId, adsConfig.interstitialAdId, adsConfig.rewardedAdId)
        bannerAd = AdMobBanner(activity, this, bannerId, adsConfig.bannerLayoutIdName, adsConfig.isBannerAdaptive)
        interstitialAd = AdMobInterstitial(activity, this, interstitialId)
        rewardedAd = AdMobRewarded(activity, this, rewardedId)
    }

    private fun getIds(banner: String, interstitial: String, rewarded: String): Triple<String, String, String> {
        val bannerId = if (adsConfig.inDebugMode) (TEST_BANNER_ID) else (banner)
        val interstitialId = if (adsConfig.inDebugMode) (TEST_INTERSTITIAL_ID) else (interstitial)
        val rewardedId = if (adsConfig.inDebugMode && rewarded.isNotEmpty()) (TEST_REWARDED_ID) else (rewarded)
        return Triple(bannerId, interstitialId, rewardedId)
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
        Timber.d("enabled = [$enabled]")
        bannerAd.enabled = enabled
        interstitialAd.enabled = enabled
        rewardedAd.enabled = enabled
    }

    companion object {
        const val TEST_BANNER_ID: String = "ca-app-pub-3940256099942544/6300978111"
        const val TEST_INTERSTITIAL_ID: String = "ca-app-pub-3940256099942544/1033173712"
        const val TEST_REWARDED_ID: String = "ca-app-pub-3940256099942544/5224354917"
    }
}
