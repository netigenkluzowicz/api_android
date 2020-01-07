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
    private val admobAds: AdmobAds,
    override val adId: AdId<String>,
    private val minDelayBetweenInterstitial: Long = DEFAULT_DELAY_BETWEEN_INTERSTITIAL_ADS,
    override var enabled: Boolean = true
) : IInterstitialAd, LifecycleObserver {
    private var isInBackground: Boolean = false
    private var interstitialAdError: Boolean = false
    private var lastInterstitialAdDisplayTime: Long = 0
    private var interstitialAd: InterstitialAd
    private val interstitialAdListeners: MutableList<InterstitialAdListener> = mutableListOf()
    private val disabled get() = !enabled

    init {
        interstitialAd = InterstitialAd(activity)
        loadIfShouldBeLoaded()
        activity.lifecycle.addObserver(this)
    }

    fun loadIfShouldBeLoaded() {
        if (interstitialAd.isLoading || interstitialAd.isLoaded || disabled) return
        load()
    }

    private fun load() {
        interstitialAd = InterstitialAd(activity)
        interstitialAd.adUnitId = adId.id
        interstitialAd.adListener = object : AdListener() {
            override fun onAdFailedToLoad(errorCode: Int) {
                super.onAdFailedToLoad(errorCode)
                interstitialAdError = true
            }
        }
        interstitialAd.loadAd(admobAds.getAdRequest())
    }

    fun show(showInterstitialListener: ShowInterstitialListener) {
        when {
            disabled -> showInterstitialListener.onShowedOrNotLoaded(false)
            isInBackground -> onCanNotShow(showInterstitialListener)
            interstitialAd.isLoaded -> onLoaded(showInterstitialListener)
            else -> onCanNotShow(showInterstitialListener)
        }
    }

    private fun onLoaded(showInterstitialListener: ShowInterstitialListener) {
        val currentTime = SystemClock.elapsedRealtime()
        interstitialAd.adListener = object : AdListener() {
            override fun onAdClosed() {
                showInterstitialListener.onShowedOrNotLoaded(true)
                loadIfShouldBeLoaded()
            }
        }
        when {
            isInBackground -> onCanNotShow(showInterstitialListener)
            validateLastShowTime(currentTime) -> show()
            else -> showInterstitialListener.onShowedOrNotLoaded(false)
        }
    }

    private fun validateLastShowTime(currentTime: Long) =
        lastInterstitialAdDisplayTime == 0L || lastInterstitialAdDisplayTime + minDelayBetweenInterstitial < currentTime

    private fun onCanNotShow(showInterstitialListener: ShowInterstitialListener) {
        showInterstitialListener.onShowedOrNotLoaded(false)
        loadIfShouldBeLoaded()
    }

    private fun show() {
        lastInterstitialAdDisplayTime = SystemClock.elapsedRealtime()
        interstitialAd.show()
    }

    interface ShowInterstitialListener {
        fun onShowedOrNotLoaded(success: Boolean)
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showInterstitialAd(onClosedOrNotShowed: (Boolean) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        const val DEFAULT_DELAY_BETWEEN_INTERSTITIAL_ADS = 60L * 1000L
    }
}