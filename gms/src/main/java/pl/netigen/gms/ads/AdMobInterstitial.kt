package pl.netigen.gms.ads

import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.InterstitialAd
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import pl.netigen.coreapi.ads.AdId
import pl.netigen.coreapi.ads.IInterstitialAd

@ExperimentalCoroutinesApi
class AdMobInterstitial(
    private val activity: AppCompatActivity,
    private val adMobRequest: IAdMobRequest,
    override val adId: AdId<String>,
    private val minDelayBetweenInterstitial: Long = DEFAULT_DELAY_BETWEEN_INTERSTITIAL_ADS,
    override var enabled: Boolean = true
) : IInterstitialAd, LifecycleObserver {
    private var isInBackground: Boolean = false
    private var lastInterstitialAdDisplayTime: Long = 0
    private var interstitialAd: InterstitialAd
    private val disabled get() = !enabled

    init {
        interstitialAd = InterstitialAd(activity)
        activity.lifecycle.addObserver(this)
    }

    override fun loadInterstitialAd(): Flow<Boolean> =
        callbackFlow {
            interstitialAd = InterstitialAd(activity)
            interstitialAd.adUnitId = adId.id
            val callback = object : AdListener() {
                override fun onAdFailedToLoad(errorCode: Int) {
                    offer(false)
                    channel.close()
                }

                override fun onAdLoaded() {
                    offer(true)
                    channel.close()
                }
            }
            interstitialAd.adListener = callback
            interstitialAd.loadAd(adMobRequest.getAdRequest())
            awaitClose {
                if (interstitialAd.adListener == callback) {
                    interstitialAd.adListener = null
                }
            }
        }

    private fun onLoaded(onClosedOrNotShowed: (Boolean) -> Unit) {
        val currentTime = SystemClock.elapsedRealtime()
        interstitialAd.adListener = object : AdListener() {
            override fun onAdClosed() {
                onClosedOrNotShowed(true)
                loadIfShouldBeLoaded()
            }
        }
        when {
            isInBackground -> onClosedOrNotShowed(false)
            validateLastShowTime(currentTime) -> show()
            else -> onClosedOrNotShowed(false)
        }
    }

    private fun loadIfShouldBeLoaded() {
        if (interstitialAd.isLoading || interstitialAd.isLoaded || disabled) return
        loadInterstitialAd()
    }
    private fun validateLastShowTime(currentTime: Long) =
        lastInterstitialAdDisplayTime == 0L || lastInterstitialAdDisplayTime + minDelayBetweenInterstitial < currentTime

    private fun onCanNotShow(onClosedOrNotShowed: (Boolean) -> Unit) {
        onClosedOrNotShowed(false)
        loadIfShouldBeLoaded()
    }

    private fun show() {
        lastInterstitialAdDisplayTime = SystemClock.elapsedRealtime()
        interstitialAd.show()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        isInBackground = false
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private fun onPause() {
        isInBackground = true
    }

    override fun showInterstitialAd(onClosedOrNotShowed: (Boolean) -> Unit) {
        when {
            disabled -> onClosedOrNotShowed(false)
            isInBackground -> onCanNotShow(onClosedOrNotShowed)
            interstitialAd.isLoaded -> onLoaded(onClosedOrNotShowed)
            else -> onCanNotShow(onClosedOrNotShowed)
        }
    }

    companion object {
        const val DEFAULT_DELAY_BETWEEN_INTERSTITIAL_ADS = 60L * 1000L
    }
}