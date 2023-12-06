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

    init {
        Timber.d("init started")
        MobileAds.initialize(activity) {
            Timber.d("initialization complete")
        }
        val (bannerId, interstitialId, rewardedId) = getIds(adsConfig.bannerAdId, adsConfig.interstitialAdId, adsConfig.rewardedAdId)
        bannerAd = AdMobBanner(
            activity = activity,
            adMobRequest = this,
            adId = bannerId,
            bannerLayoutIdName = adsConfig.bannerLayoutIdName,
            yandexAdId = if (adsConfig.inDebugMode) "demo-banner-yandex" else adsConfig.bannerYandexAdId,
        )
        interstitialAd = AdMobInterstitial(
            activity = activity,
            adMobRequest = this,
            adId = interstitialId,
            yandexAdId = if (adsConfig.inDebugMode) "demo-interstitial-yandex" else adsConfig.interstitialYandexAdId,
        )
        rewardedAd = AdMobRewarded(
            activity = activity,
            adMobRequest = this,
            adId = rewardedId,
            yandexAdId = if (adsConfig.inDebugMode) "demo-rewarded-yandex" else adsConfig.rewardedYandexAdId,
        )
        openAppAd = OpenAppAd(
            activity = activity,
            adMobRequest = this,
            adId = getOpenAppId(),
        )

        val requestConfiguration = RequestConfiguration.Builder()
            .setTestDeviceIds(adsConfig.testDevices)
            .build()
        MobileAds.setRequestConfiguration(requestConfiguration)
    }

    private fun getOpenAppId() =
        if (adsConfig.inDebugMode && adsConfig.openAppAdId.isNotEmpty()) (TEST_OPEN_APP_ID) else (adsConfig.openAppAdId)

    private fun getIds(banner: String, interstitial: String, rewarded: String): Triple<String, String, String> {
        val bannerId = if (adsConfig.inDebugMode) (TEST_BANNER_ID) else (banner)
        val interstitialId = if (adsConfig.inDebugMode) (TEST_INTERSTITIAL_ID) else (interstitial)
        val rewardedId = if (adsConfig.inDebugMode && rewarded.isNotEmpty()) (TEST_REWARDED_ID) else (rewarded)
        return Triple(bannerId, interstitialId, rewardedId)
    }

    override fun enableYandex() {
        bannerAd.enableYandex()
        interstitialAd.enableYandex()
        rewardedAd.enableYandex()
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
        const val TEST_OPEN_APP_ID: String = "ca-app-pub-3940256099942544/9257395921"
    }
}
