package pl.netigen.gms.ads

import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.InterstitialAd
import pl.netigen.coreapi.ads.AdId
import pl.netigen.coreapi.ads.IInterstitialAd
import pl.netigen.coreapi.ads.InterstitialAdListener

class AdmobInterstitial(
    private val activity: AppCompatActivity,
    private val admobRequest: IAdmobRequest,
    override val adId: AdId<String>,
    private val minDelayBetweenInterstitial: Long = DEFAULT_DELAY_BETWEEN_INTERSTITIAL_ADS,
    override var enabled: Boolean = true
) : IInterstitialAd, LifecycleObserver {
    private var isInBackground: Boolean = false
    private var lastInterstitialAdDisplayTime: Long = 0
    private var interstitialAd: InterstitialAd
    private val interstitialAdListeners: MutableList<InterstitialAdListener> = mutableListOf()
    private val disabled get() = !enabled

    init {
        interstitialAd = InterstitialAd(activity)
        activity.lifecycle.addObserver(this)
    }

    private fun loadIfShouldBeLoaded() {
        if (interstitialAd.isLoading || interstitialAd.isLoaded || disabled) return
        loadInterstitialAd()
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
    fun onResume() {
        isInBackground = false
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        isInBackground = true
    }

    override fun addInterstitialListener(interstitialAdListener: InterstitialAdListener) {
        interstitialAdListeners.add(interstitialAdListener)
    }

    override fun removeInterstitialListener(interstitialAdListener: InterstitialAdListener) {
        interstitialAdListeners.remove(interstitialAdListener)
    }

    override fun loadInterstitialAd() {

        interstitialAd = InterstitialAd(activity)
        interstitialAd.adUnitId = adId.id
        interstitialAd.adListener = object : AdListener() {
            override fun onAdFailedToLoad(errorCode: Int) {
                postLoadedCallback(false)
            }

            override fun onAdLoaded() {
                postLoadedCallback(true)
            }
        }
        interstitialAd.loadAd(admobRequest.getAdRequest())
    }

    private fun postLoadedCallback(value: Boolean) {
        for (interstitialAdListener in interstitialAdListeners) {
            interstitialAdListener.loadInterstitialResult(value)
        }
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