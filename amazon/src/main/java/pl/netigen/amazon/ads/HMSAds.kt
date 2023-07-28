package pl.netigen.amazon.ads

import androidx.activity.ComponentActivity
import com.huawei.hms.ads.HwAds
import com.huawei.hms.ads.RequestOptions
import com.huawei.hms.ads.consent.constant.ConsentStatus
import pl.netigen.coreapi.ads.*
import timber.log.Timber

class HMSAds(
    activity: ComponentActivity,
    private val adsConfig: IAdsConfig,
) : IAds {
    override var personalizedAdsEnabled = false
        set(value) {
            setPersonalizedRequestOptions(value)
            field = value
        }

    private fun setPersonalizedRequestOptions(value: Boolean) {
        var requestOptions = HwAds.getRequestOptions() ?: RequestOptions()
        requestOptions = requestOptions.toBuilder()
            .setNonPersonalizedAd(if (value) ConsentStatus.PERSONALIZED.value else ConsentStatus.NON_PERSONALIZED.value)
            .build()
        HwAds.setRequestOptions(requestOptions)
    }

    override val bannerAd: IBannerAd
    override val interstitialAd: IInterstitialAd
    override val rewardedAd: IRewardedAd

    init {
        Timber.d(this.toString())
        // Initialize the HUAWEI Ads SDK.
        HwAds.init(activity)
        val (bannerId, interstitialId, rewardedId) = getIds(adsConfig.bannerAdId, adsConfig.interstitialAdId, adsConfig.rewardedAdId)
        bannerAd = HMSBanner(activity, bannerId, adsConfig.bannerLayoutIdName)
        interstitialAd = HMSInterstitial(activity, interstitialId)
        rewardedAd = HMSRewarded(activity, rewardedId)
    }

    private fun getIds(banner: String, interstitial: String, rewarded: String): Triple<String, String, String> {
        val bannerId = if (adsConfig.inDebugMode) (TEST_BANNER_ID) else (banner)
        val interstitialId = if (adsConfig.inDebugMode) (TEST_INTERSTITIAL_ID) else (interstitial)
        val rewardedId = if (adsConfig.inDebugMode && rewarded.isNotEmpty()) (TEST_REWARDED_ID) else (rewarded)
        return Triple(bannerId, interstitialId, rewardedId)
    }

    override fun enable() = setEnabled(true)
    override fun interstitialAdIsInBackground(isInBackground: Boolean) = Unit
    override fun disable() = setEnabled(false)

    private fun setEnabled(enabled: Boolean) {
        Timber.d("enabled = [$enabled]")
        bannerAd.enabled = enabled
        interstitialAd.enabled = enabled
        rewardedAd.enabled = enabled
    }

    companion object {
        const val TEST_BANNER_ID: String = "testw6vs28auh3"
        const val TEST_INTERSTITIAL_ID: String = "teste9ih9j0rc3"
        const val TEST_REWARDED_ID: String = "testx9dtjwj8hp"
    }
}
