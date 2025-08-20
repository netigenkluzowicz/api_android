package pl.netigen.gms.ads

import android.content.Context
import android.os.SystemClock
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import pl.netigen.coreapi.ads.IAdsConfig.Companion.DEFAULT_DELAY_BETWEEN_INTERSTITIAL_ADS_MS
import pl.netigen.coreapi.ads.IInterstitialAd
import timber.log.Timber
import timber.log.Timber.Forest.d

/**
 * [IInterstitialAd] implementation backed by [InterstitialAd].
 *
 * See: [Interstitial Ads](https://developers.google.com/admob/android/interstitial)
 *
 * Registers itself as a [DefaultLifecycleObserver] on the provided
 * [LifecycleOwner] (`ComponentActivity`) to react to lifecycle events.
 *
 * @property adMobRequest provides [AdRequest] for this ad
 * @property adId identifier of the interstitial placement
 * @property minDelayBetweenInterstitial minimum time between interstitial impressions
 *   (default: [DEFAULT_DELAY_BETWEEN_INTERSTITIAL_ADS_MS] ms)
 * @property enabled whether this ad is currently active
 *
 * @constructor Initializes the ad and registers lifecycle observation.
 * @param activity host [ComponentActivity] used as [Context]
 * and [LifecycleOwner] for lifecycle callbacks
 */
class AdMobInterstitial(
    activity: ComponentActivity,
    private val adMobRequest: IAdMobRequest,
    override val adId: String,
    private val minDelayBetweenInterstitial: Long = DEFAULT_DELAY_BETWEEN_INTERSTITIAL_ADS_MS,
    override var enabled: Boolean = true,
) : IInterstitialAd, DefaultLifecycleObserver {
    override var isInBackground: Boolean = false
    private var lastInterstitialAdDisplayTime: Long = 0
    private var interstitialAd: InterstitialAd? = null
    private val disabled get() = !enabled
    private var currentActivity: ComponentActivity = activity
    private var isLoading = false

    init {
        d(this.toString())
        activity.lifecycle.addObserver(this)
    }

    override fun load(onLoadSuccess: (Boolean) -> Unit) {
        isLoading = true
        loadAdmob(onLoadSuccess)
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
                    isLoading = false
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
                    isLoading = false
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
        if (interstitialAd != null || disabled || isLoading) return
        load {}
    }


    private fun validateLastShowTime(currentTime: Long) =
        lastInterstitialAdDisplayTime == 0L || lastInterstitialAdDisplayTime + minDelayBetweenInterstitial < currentTime

    private fun onCanNotShow(onClosedOrNotShowed: (Boolean) -> Unit) {
        onClosedOrNotShowed(false)
    }

    override fun onResume(owner: LifecycleOwner) {
        d("()")
        isInBackground = false
    }

    override fun onPause(owner: LifecycleOwner) {
        d("()")
        isInBackground = true
    }

    override fun onDestroy(owner: LifecycleOwner) {
        d("()")
        owner.lifecycle.removeObserver(this)
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

        isLoaded -> {
            onInterstitialReadyToShow(forceShow, onClosedOrNotShowed)
        }

        else -> {
            d("notLoaded")
            onCanNotShow(onClosedOrNotShowed)
        }
    }

}
