package pl.netigen.core.ads

import android.os.SystemClock
import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.InterstitialAd
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext
import pl.netigen.coreapi.ads.IInterstitialAd
import timber.log.Timber
import timber.log.Timber.d


class AdMobInterstitial(
    activity: ComponentActivity,
    private val adMobRequest: IAdMobRequest,
    override val adId: String,
    private val minDelayBetweenInterstitial: Long = DEFAULT_DELAY_BETWEEN_INTERSTITIAL_ADS,
    override var enabled: Boolean = true
) : IInterstitialAd, LifecycleObserver {
    private var isInBackground: Boolean = false
    private var lastInterstitialAdDisplayTime: Long = 0
    private var interstitialAd: InterstitialAd = InterstitialAd(activity)
    private val disabled get() = !enabled

    init {
        d(this.toString())
        interstitialAd.adUnitId = adId
        activity.lifecycle.addObserver(this)
    }

    override fun load(): Flow<Boolean> =
        callbackFlow {
            val callback = object : AdListener() {
                override fun onAdFailedToLoad(errorCode: Int) {
                    d(errorCode.toString())
                    interstitialAd.adListener = null
                    offer(false)
                    channel.close()
                }

                override fun onAdLoaded() {
                    d("()")
                    interstitialAd.adListener = null
                    offer(true)
                    channel.close()
                }
            }
            withContext(Dispatchers.Main) {
                interstitialAd.adListener = callback
                interstitialAd.loadAd(adMobRequest.getAdRequest())
            }
            try {
                awaitClose { }
            } catch (e: Exception) {
                Timber.e(e)
            }
        }

    override val isLoaded: Boolean
        get() = interstitialAd.isLoaded

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
        interstitialAd.adListener = object : AdListener() {
            override fun onAdClosed() {
                d("onAdClosed")
                onClosedOrNotShowed(true)
                loadIfShouldBeLoaded()
                interstitialAd.adListener = null
            }
        }
        lastInterstitialAdDisplayTime = SystemClock.elapsedRealtime()
        interstitialAd.show()
    }

    override fun loadIfShouldBeLoaded() {
        d("()")
        if (interstitialAd.isLoading || interstitialAd.isLoaded || disabled) return
        interstitialAd.adListener = object : AdListener() {
            override fun onAdLoaded() = d("()")
            override fun onAdFailedToLoad(errorCode: Int) = d("p0 = [$errorCode]")
        }
        interstitialAd.loadAd(adMobRequest.getAdRequest())
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

    override fun showIfCanBeShowed(forceShow: Boolean, onClosedOrNotShowed: (Boolean) -> Unit) {
        d("forceShow = [$forceShow], onClosedOrNotShowed = [$onClosedOrNotShowed]")
        when {
            disabled -> onClosedOrNotShowed(false)
            isInBackground -> onCanNotShow(onClosedOrNotShowed)
            interstitialAd.isLoaded -> onInterstitialReadyToShow(forceShow, onClosedOrNotShowed)
            else -> onCanNotShow(onClosedOrNotShowed)
        }
    }

    companion object {
        const val DEFAULT_DELAY_BETWEEN_INTERSTITIAL_ADS = 60L * 1000L
    }
}