package pl.netigen.gms.ads

import androidx.activity.ComponentActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import pl.netigen.coreapi.ads.IAds
import pl.netigen.coreapi.ads.IAdsConfig
import pl.netigen.coreapi.ads.IBannerAd
import pl.netigen.coreapi.ads.IInterstitialAd
import pl.netigen.coreapi.ads.IOpenAppAd
import pl.netigen.coreapi.ads.IRewardedAd
import timber.log.Timber

/**
 * [IAds] implementation with Google Mobile Ads SDK
 *
 * See: [Mobile Ads SDK](https://developers.google.com/admob/android/quick-start)
 *
 * @property adsConfig Current ads configuration
 * @constructor
 * Initializes SDK, creates ads and sets up test devices
 *
 * @param activity Activity context used for ads
 */
class AdMobAds(
    activity: ComponentActivity,
    private val adsConfig: IAdsConfig,
) : IAds, IAdMobRequest {
    override var personalizedAdsEnabled = false
    override val bannerAd: IBannerAd
    override val interstitialAd: IInterstitialAd
    override val rewardedAd: IRewardedAd
    override val openAppAd: IOpenAppAd
        get() = object : IOpenAppAd {
            override val isLoadedAndValid: Boolean = false

            override fun load(onLoadSuccess: (Boolean) -> Unit) {
            }

            override fun showIfCanBeShowed(forceShow: Boolean, onClosedOrNotShowed: (Boolean) -> Unit) {
            }

            override var enabled: Boolean = false
            override val adId: String = ""

        }

    init {
        Timber.d("init started")
        val requestConfiguration = MobileAds.getRequestConfiguration()
            .toBuilder()
            .setTagForChildDirectedTreatment(RequestConfiguration.TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE)
            .setTagForUnderAgeOfConsent(RequestConfiguration.TAG_FOR_UNDER_AGE_OF_CONSENT_TRUE)
            .setMaxAdContentRating(RequestConfiguration.MAX_AD_CONTENT_RATING_G)
            .build()
        MobileAds.setRequestConfiguration(requestConfiguration)
        MobileAds.initialize(activity) {
            Timber.d("initialization complete")
        }
        val (bannerId, interstitialId, rewardedId) = getIds(adsConfig.bannerAdId, adsConfig.interstitialAdId, adsConfig.rewardedAdId)
        bannerAd = AdMobBanner(
            activity = activity,
            adMobRequest = this,
            adId = bannerId,
            bannerLayoutIdName = adsConfig.bannerLayoutIdName,
        )
        interstitialAd = AdMobInterstitial(
            activity = activity,
            adMobRequest = this,
            adId = interstitialId,
        )
        rewardedAd = AdMobRewarded(
            activity = activity,
            adMobRequest = this,
            adId = rewardedId,
        )

    }

    private fun getIds(banner: String, interstitial: String, rewarded: String): Triple<String, String, String> {
        val bannerId = if (adsConfig.inDebugMode) (TEST_BANNER_ID) else (banner)
        val interstitialId = if (adsConfig.inDebugMode) (TEST_INTERSTITIAL_ID) else (interstitial)
        val rewardedId = if (adsConfig.inDebugMode && rewarded.isNotEmpty()) (TEST_REWARDED_ID) else (rewarded)
        return Triple(bannerId, interstitialId, rewardedId)
    }

    override fun getAdRequest(): AdRequest = AdRequest.Builder().build()

    override fun enable() = setEnabled(true)

    override fun disable() = setEnabled(false)

    private fun setEnabled(enabled: Boolean) {
        Timber.d("enabled = [$enabled]")
        bannerAd.enabled = enabled
        interstitialAd.enabled = enabled
        rewardedAd.enabled = enabled
    }

    override fun interstitialAdIsInBackground(isInBackground: Boolean) {
        interstitialAd.isInBackground = isInBackground
    }

    companion object {
        const val TEST_BANNER_ID: String = "ca-app-pub-3940256099942544/6300978111"
        const val TEST_INTERSTITIAL_ID: String = "ca-app-pub-3940256099942544/1033173712"
        const val TEST_REWARDED_ID: String = "ca-app-pub-3940256099942544/5224354917"
    }
}
