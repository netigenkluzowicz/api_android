package pl.netigen.gms.ads

import android.content.Context
import android.os.SystemClock
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.yandex.mobile.ads.common.AdRequestConfiguration
import com.yandex.mobile.ads.common.AdRequestError
import com.yandex.mobile.ads.common.ImpressionData
import com.yandex.mobile.ads.interstitial.InterstitialAdEventListener
import com.yandex.mobile.ads.interstitial.InterstitialAdLoadListener
import com.yandex.mobile.ads.interstitial.InterstitialAdLoader
import pl.netigen.coreapi.ads.IAdsConfig.Companion.DEFAULT_DELAY_BETWEEN_INTERSTITIAL_ADS_MS
import pl.netigen.coreapi.ads.IInterstitialAd
import timber.log.Timber
import timber.log.Timber.Forest.d

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
class AdMobInterstitial(
    activity: ComponentActivity,
    private val adMobRequest: IAdMobRequest,
    override val adId: String,
    override val yandexAdId: String,
    private val minDelayBetweenInterstitial: Long = DEFAULT_DELAY_BETWEEN_INTERSTITIAL_ADS_MS,
    override var enabled: Boolean = true,
) : IInterstitialAd, LifecycleObserver {
    override var isInBackground: Boolean = false
    private var lastInterstitialAdDisplayTime: Long = 0
    private var interstitialAd: InterstitialAd? = null
    private val disabled get() = !enabled
    private var currentActivity: ComponentActivity = activity
    private var yandexActive = false
    private var yandexAd: com.yandex.mobile.ads.interstitial.InterstitialAd? = null

    init {
        d(this.toString())
        activity.lifecycle.addObserver(this)
    }

    override fun load(onLoadSuccess: (Boolean) -> Unit) {
        if (yandexActive) loadYandex(onLoadSuccess) else loadAdmob(onLoadSuccess)
    }

    private fun loadYandex(onLoadSuccess: (Boolean) -> Unit) {
        InterstitialAdLoader(currentActivity).apply {
            setAdLoadListener(
                object : InterstitialAdLoadListener {
                    override fun onAdLoaded(ad: com.yandex.mobile.ads.interstitial.InterstitialAd) {
                        d("ad = [$ad]")
                        onLoadSuccess(true)
                        yandexAd = ad
                    }

                    override fun onAdFailedToLoad(error: AdRequestError) {
                        d("error = [$error]")
                        onLoadSuccess(false)
                    }
                },
            )
        }.also { it.loadAd(AdRequestConfiguration.Builder(yandexAdId).build()) }
    }

    private fun loadAdmob(onLoadSuccess: (Boolean) -> Unit) {
        val requestLoadActivity = currentActivity
        InterstitialAd.load(
            currentActivity,
            adId,
            adMobRequest.getAdRequest(),
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    d(loadAdError.message)
                    interstitialAd = null
                    onLoadSuccess(false)
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    if (currentActivity != requestLoadActivity) {
                        this@AdMobInterstitial.interstitialAd = null
                        onLoadSuccess(false)
                        loadIfShouldBeLoaded()
                    } else {
                        this@AdMobInterstitial.interstitialAd = interstitialAd
                        onLoadSuccess(true)
                    }
                }
            },
        )
    }

    override fun onResume(activity: AppCompatActivity) {
        if (currentActivity != activity) {
            currentActivity.lifecycle.removeObserver(this)
            currentActivity = activity
            currentActivity.lifecycle.addObserver(this)
            interstitialAd = null
            loadIfShouldBeLoaded()
        }
    }

    override fun enableYandex() {
        yandexActive = true
        loadYandex {}
    }

    override val isLoaded: Boolean
        get() = interstitialAd != null

    private fun onInterstitialReadyToShow(forceShow: Boolean = false, onClosedOrNotShowed: (Boolean) -> Unit) {
        d("forceShow = [$forceShow], onClosedOrNotShowed = [$onClosedOrNotShowed]")
        val currentTime = SystemClock.elapsedRealtime()
        when {
            isInBackground -> onClosedOrNotShowed(false)
            forceShow || validateLastShowTime(currentTime) -> show(onClosedOrNotShowed)
            else -> onClosedOrNotShowed(false)
        }
    }

    private fun show(onClosedOrNotShowed: (Boolean) -> Unit) {
        interstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                d("onAdDismissedFullScreenContent")
                onAdClosed(onClosedOrNotShowed)
            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                Timber.e(adError.message)
                onAdClosed(onClosedOrNotShowed)
            }

            override fun onAdShowedFullScreenContent() {
                d("onAdShowedFullScreenContent")
            }
        }
        val interstitialAd1 = interstitialAd
        if (interstitialAd1 != null) {
            lastInterstitialAdDisplayTime = SystemClock.elapsedRealtime()
            interstitialAd1.show(currentActivity)
        } else {
            onClosedOrNotShowed(false)
        }
    }

    fun onAdClosed(onClosedOrNotShowed: (Boolean) -> Unit) {
        d("onAdClosed")
        onClosedOrNotShowed(true)
        interstitialAd = null
        loadIfShouldBeLoaded()
    }

    override fun loadIfShouldBeLoaded() {
        d("()")
        if (interstitialAd != null || disabled) return
        load {}
    }


    private fun validateLastShowTime(currentTime: Long) =
        lastInterstitialAdDisplayTime == 0L || lastInterstitialAdDisplayTime + minDelayBetweenInterstitial < currentTime

    private fun onCanNotShow(onClosedOrNotShowed: (Boolean) -> Unit) {
        onClosedOrNotShowed(false)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        d("()")
        isInBackground = false
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private fun onPause() {
        d("()")
        isInBackground = true
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        currentActivity.lifecycle.removeObserver(this)
        interstitialAd = null
    }


    override fun showIfCanBeShowed(forceShow: Boolean, onClosedOrNotShowed: (Boolean) -> Unit) = when {
        disabled -> {
            d("disabled")
            onClosedOrNotShowed(false)
        }

        isInBackground -> {
            d("isInBackground")
            onCanNotShow(onClosedOrNotShowed)
        }

        yandexActive -> showYandexAd(forceShow, onClosedOrNotShowed)

        isLoaded -> {
            onInterstitialReadyToShow(forceShow, onClosedOrNotShowed)
        }

        else -> {
            d("notLoaded")
            onCanNotShow(onClosedOrNotShowed)
        }
    }

    private fun showYandexAd(forceShow: Boolean, onClosedOrNotShowed: (Boolean) -> Unit) {
        val currentTime = SystemClock.elapsedRealtime()
        when {
            isInBackground -> {
                onClosedOrNotShowed(false)
                return
            }

            forceShow || validateLastShowTime(currentTime) -> Unit
            else -> {
                onClosedOrNotShowed(false)
                return
            }
        }
        val ad = yandexAd
        if (ad == null) {
            onClosedOrNotShowed(false)
            loadYandex { }
            return
        }
        ad.setAdEventListener(
            object : InterstitialAdEventListener {
                override fun onAdShown() = Unit

                override fun onAdFailedToShow(p0: com.yandex.mobile.ads.common.AdError) {
                    onClosedOrNotShowed(false)
                    reloadYandex()
                }

                override fun onAdDismissed() {
                    onClosedOrNotShowed(true)
                    reloadYandex()
                }

                override fun onAdClicked() {
                    onClosedOrNotShowed(true)
                    reloadYandex()
                }

                override fun onAdImpression(p0: ImpressionData?) = Unit

            },
        )
        ad.show(currentActivity)
    }

    private fun reloadYandex() {
        yandexAd = null
        loadYandex { }
    }
}
