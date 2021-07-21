package pl.netigen.gms.ads

import android.content.Context
import android.os.SystemClock
import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import pl.netigen.coreapi.ads.IAdsConfig.Companion.DEFAULT_DELAY_BETWEEN_INTERSTITIAL_ADS_MS
import pl.netigen.coreapi.ads.IInterstitialAd
import timber.log.Timber
import timber.log.Timber.d

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
    private val activity: ComponentActivity,
    private val adMobRequest: IAdMobRequest,
    override val adId: String,
    private val minDelayBetweenInterstitial: Long = DEFAULT_DELAY_BETWEEN_INTERSTITIAL_ADS_MS,
    override var enabled: Boolean = true
) : IInterstitialAd, LifecycleObserver {
    override var isInBackground: Boolean = false
    private var lastInterstitialAdDisplayTime: Long = 0
    private var interstitialAd: InterstitialAd? = null
    private val disabled get() = !enabled

    init {
        d(this.toString())
        activity.lifecycle.addObserver(this)
    }

    override fun load(): Flow<Boolean> =
        callbackFlow {
            val callback = object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    try {
                        d(loadAdError.message)
                        interstitialAd = null
                        trySendBlocking(false)
                    } catch (e: Exception) {
                        Timber.e(e)
                    } finally {
                        channel.close()
                    }
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    try {
                        d("()")
                        this@AdMobInterstitial.interstitialAd = interstitialAd
                        trySendBlocking(true)
                    } catch (e: Exception) {
                        Timber.e(e)
                    } finally {
                        channel.close()
                    }
                }
            }
            InterstitialAd.load(activity, adId, adMobRequest.getAdRequest(), callback)
            awaitClose {}
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
        d("onClosedOrNotShowed = [$onClosedOrNotShowed]")
        interstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                d("onAdDismissedFullScreenContent")
                onAdClosed(onClosedOrNotShowed)
            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
                Timber.e(adError?.message)
                onAdClosed(onClosedOrNotShowed)
            }

            override fun onAdShowedFullScreenContent() {
                d("onAdShowedFullScreenContent")
                onAdClosed(onClosedOrNotShowed)
            }
        }
        val interstitialAd1 = interstitialAd
        if (interstitialAd1 != null) {
            lastInterstitialAdDisplayTime = SystemClock.elapsedRealtime()
            interstitialAd1.show(activity)
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

        InterstitialAd.load(activity, adId, adMobRequest.getAdRequest(), object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                d("loadAdError = [$loadAdError]")
                interstitialAd = null

            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                d("interstitialAd = [$interstitialAd]")
                this@AdMobInterstitial.interstitialAd = interstitialAd
            }
        })
    }

    private fun validateLastShowTime(currentTime: Long) =
        lastInterstitialAdDisplayTime == 0L || lastInterstitialAdDisplayTime + minDelayBetweenInterstitial < currentTime

    private fun onCanNotShow(onClosedOrNotShowed: (Boolean) -> Unit) {
        d("onClosedOrNotShowed = [$onClosedOrNotShowed]")
        onClosedOrNotShowed(false)
        loadIfShouldBeLoaded()
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

    override fun showIfCanBeShowed(forceShow: Boolean, onClosedOrNotShowed: (Boolean) -> Unit) = when {
        disabled -> {
            d("disabled")
            onClosedOrNotShowed(false)
        }
        isInBackground -> {
            d("isInBackground")
            onCanNotShow(onClosedOrNotShowed)
        }
        isLoaded -> {
            onInterstitialReadyToShow(forceShow, onClosedOrNotShowed)
        }
        else -> {
            d("notLoaded")
            onCanNotShow(onClosedOrNotShowed)
        }
    }
}