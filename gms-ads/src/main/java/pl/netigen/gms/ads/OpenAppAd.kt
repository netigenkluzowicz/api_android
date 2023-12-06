package pl.netigen.gms.ads

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.appopen.AppOpenAd
import com.google.android.gms.ads.interstitial.InterstitialAd
import pl.netigen.coreapi.ads.IAdsConfig.Companion.DEFAULT_DELAY_BETWEEN_INTERSTITIAL_ADS_MS
import pl.netigen.coreapi.ads.IInterstitialAd
import pl.netigen.coreapi.ads.IOpenAppAd
import timber.log.Timber.Forest.d
import java.util.Date

/**
 * [IInterstitialAd] implementation with [InterstitialAd] from Google Mobile Ads SDK
 *
 * See: [Interstitial Ads](https://developers.google.com/admob/android/interstitial)
 *
 * @property adMobRequest adMobRequest Provides [AdRequest] for this ad
 * @property adId Current ad [String] identifier
 * @property minDelayBetweenInterstitial Minimum time after one ad was showed to show another ad,
 *
 * for default = [60 seconds][DEFAULT_DELAY_BETWEEN_INTERSTITIAL_ADS_MS]
 *
 * @property enabled Current ad [String] identifier
 * @constructor
 * Initializes ad, starts observing activity [Lifecycle]
 *
 * @param activity [ComponentActivity] for this ad [Context] and [Lifecycle] events
 */
class OpenAppAd(
    private val activity: ComponentActivity,
    private val adMobRequest: IAdMobRequest,
    override val adId: String,
    private val minDelayBetweenInterstitial: Long = DEFAULT_DELAY_BETWEEN_INTERSTITIAL_ADS_MS,
    override var enabled: Boolean = true,
) : IOpenAppAd {
    private var appOpenAd: AppOpenAd? = null
    private var isLoadingAd = false
    var isShowingAd = false
    private var loadTime: Long = 0

    init {
        loadIfNeeded()
    }

    override fun loadIfNeeded() {
        if (isLoadingAd || isLoadedAndValid) {
            return
        }

        isLoadingAd = true
        AppOpenAd.load(
            activity,
            adId,
            adMobRequest.getAdRequest(),
            object : AppOpenAd.AppOpenAdLoadCallback() {
                override fun onAdLoaded(ad: AppOpenAd) {
                    d("ad = [$ad]")
                    appOpenAd = ad
                    isLoadingAd = false
                    loadTime = Date().time
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    d("loadAdError = [$loadAdError]")
                    isLoadingAd = false;
                }
            },
        )
    }

    override val isLoadedAndValid: Boolean
        get() = appOpenAd != null && wasLoadTimeLessThan4HoursAgo()

    override fun showIfCanBeShowed(onClosedOrNotShowed: (Boolean) -> Unit) {
        if (isShowingAd) {
            d("The app open ad is already showing.")
            return
        }

        // If the app open ad is not available yet, invoke the callback then load the ad.
        if (!isLoadedAndValid) {
            d("The app open ad is not ready yet.")
            onClosedOrNotShowed(false)
            loadIfNeeded()
            return
        }

        appOpenAd?.fullScreenContentCallback = object : FullScreenContentCallback() {

            override fun onAdDismissedFullScreenContent() {
                // Called when full screen content is dismissed.
                // Set the reference to null so isAdAvailable() returns false.
                d("Ad dismissed fullscreen content.")
                appOpenAd = null
                isShowingAd = false

                onClosedOrNotShowed(true)
                loadIfNeeded()
            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                // Called when fullscreen content failed to show.
                // Set the reference to null so isAdAvailable() returns false.
                d(adError.message)
                appOpenAd = null
                isShowingAd = false

                onClosedOrNotShowed(false)
                loadIfNeeded()
            }

            override fun onAdShowedFullScreenContent() {
                // Called when fullscreen content is shown.
                d("Ad showed fullscreen content.")
            }
        }
        isShowingAd = true
        appOpenAd?.show(activity)
    }

    private fun wasLoadTimeLessThan4HoursAgo(): Boolean {
        val dateDifference: Long = Date().time - loadTime
        val numMilliSecondsPerHour: Long = 3600000
        return dateDifference < numMilliSecondsPerHour * 4
    }

}
