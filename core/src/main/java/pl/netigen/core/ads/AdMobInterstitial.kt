package pl.netigen.core.ads

import android.os.SystemClock
import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.InterstitialAd
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import pl.netigen.coreapi.ads.IInterstitialAd
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
        interstitialAd.adUnitId = adId
        activity.lifecycle.addObserver(this)
    }

    override fun loadInterstitialAd(): Flow<Boolean> =
        callbackFlow {
            val callback = object : AdListener() {
                override fun onAdFailedToLoad(errorCode: Int) {
                    d(errorCode.toString())
                    interstitialAd.adListener = null
                    offer(false)
                    channel.close()
                }

                override fun onAdLoaded() {
                    d("called")
                    interstitialAd.adListener = null
                    offer(true)
                    channel.close()
                }
            }
            interstitialAd.adListener = callback
            interstitialAd.loadAd(adMobRequest.getAdRequest())
            awaitClose { }
        }

    override val isLoaded: Boolean
        get() = interstitialAd.isLoaded

    private fun onLoaded(forceShow: Boolean = false, onClosedOrNotShowed: (Boolean) -> Unit) {
        d("called")
        val currentTime = SystemClock.elapsedRealtime()
        when {
            isInBackground -> onClosedOrNotShowed(false)
            forceShow || validateLastShowTime(currentTime) -> show(onClosedOrNotShowed)
            else -> onClosedOrNotShowed(false)
        }
    }

    private fun show(onClosedOrNotShowed: (Boolean) -> Unit) {
        interstitialAd.adListener = object : AdListener() {
            override fun onAdClosed() {
                onClosedOrNotShowed(true)
                loadIfShouldBeLoaded()
                interstitialAd.adListener = null
            }
        }
        lastInterstitialAdDisplayTime = SystemClock.elapsedRealtime()
        interstitialAd.show()
    }

    private fun loadIfShouldBeLoaded() {
        if (interstitialAd.isLoading || interstitialAd.isLoaded || disabled) return
        interstitialAd.loadAd(adMobRequest.getAdRequest())
    }

    private fun validateLastShowTime(currentTime: Long) =
        lastInterstitialAdDisplayTime == 0L || lastInterstitialAdDisplayTime + minDelayBetweenInterstitial < currentTime

    private fun onCanNotShow(onClosedOrNotShowed: (Boolean) -> Unit) {
        d("called")
        onClosedOrNotShowed(false)
        loadIfShouldBeLoaded()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        isInBackground = false
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private fun onPause() {
        isInBackground = true
    }

    override fun showInterstitialAd(forceShow: Boolean, onClosedOrNotShowed: (Boolean) -> Unit) {
        when {
            disabled -> onClosedOrNotShowed(false)
            isInBackground -> onCanNotShow(onClosedOrNotShowed)
            interstitialAd.isLoaded -> onLoaded(forceShow, onClosedOrNotShowed)
            else -> onCanNotShow(onClosedOrNotShowed)
        }
    }

    companion object {
        const val DEFAULT_DELAY_BETWEEN_INTERSTITIAL_ADS = 60L * 1000L
    }
}