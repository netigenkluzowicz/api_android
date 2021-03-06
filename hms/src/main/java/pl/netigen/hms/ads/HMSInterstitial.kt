package pl.netigen.hms.ads

import android.os.SystemClock
import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.huawei.hms.ads.AdListener
import com.huawei.hms.ads.AdParam
import com.huawei.hms.ads.InterstitialAd
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import pl.netigen.coreapi.ads.IAdsConfig.Companion.DEFAULT_DELAY_BETWEEN_INTERSTITIAL_ADS_MS
import pl.netigen.coreapi.ads.IInterstitialAd
import timber.log.Timber
import timber.log.Timber.d


class HMSInterstitial(
    activity: ComponentActivity,
    override val adId: String,
    private val minDelayBetweenInterstitial: Long = DEFAULT_DELAY_BETWEEN_INTERSTITIAL_ADS_MS,
    override var enabled: Boolean = true
) : IInterstitialAd, LifecycleObserver {
    override var isInBackground: Boolean = false
    private var onClosedOrNotShowed: ((Boolean) -> Unit)? = null
    private var lastInterstitialAdDisplayTime: Long = 0
    private var interstitialAd: InterstitialAd = InterstitialAd(activity)
    private val disabled get() = !enabled

    init {
        d(this.toString())
        interstitialAd.adId = adId
        activity.lifecycle.addObserver(this)
    }

    override fun load(): Flow<Boolean> =
        callbackFlow {
            val callback = object : AdListener() {
                override fun onAdFailed(errorCode: Int) {
                    super.onAdFailed(errorCode)
                    d("errorCode = [$errorCode]")
                    try {
                        d(errorCode.toString())
                        interstitialAd.adListener = null
                        sendBlocking(false)
                    } catch (e: Exception) {
                        Timber.e(e)
                    } finally {
                        channel.close()
                    }
                }

                override fun onAdLoaded() {
                    super.onAdLoaded()
                    d("()")
                    try {
                        d("()")
                        interstitialAd.adListener = null
                        sendBlocking(true)
                    } catch (e: Exception) {
                        Timber.e(e)
                    } finally {
                        channel.close()
                    }
                }
            }
            interstitialAd.adListener = callback
            interstitialAd.loadAd(AdParam.Builder().build())

            awaitClose {}
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
        this.onClosedOrNotShowed = onClosedOrNotShowed
        d("onClosedOrNotShowed = [$onClosedOrNotShowed]")
        interstitialAd.adListener = object : AdListener() {
            override fun onAdClosed() {
                super.onAdClosed()
                d("()")
                onClosedOrNotShowed(true)
                this@HMSInterstitial.onClosedOrNotShowed = null
                loadIfShouldBeLoaded()
                interstitialAd.adListener = null
            }

            override fun onAdLoaded() {
                super.onAdLoaded()
                d("()")
            }

            override fun onAdFailed(errorCode: Int) {
                super.onAdFailed(errorCode)
                d("errorCode = [$errorCode]")
            }


            override fun onAdClicked() {
                super.onAdClicked()
                d("()")
            }

            override fun onAdLeave() {
                super.onAdLeave()
                d("()")
            }

            override fun onAdOpened() {
                super.onAdOpened()
                d("()")
            }
        }
        lastInterstitialAdDisplayTime = SystemClock.elapsedRealtime()
        interstitialAd.show()
    }

    override fun loadIfShouldBeLoaded() {
        d("()")
        if (interstitialAd.isLoading || interstitialAd.isLoaded || disabled) return
        interstitialAd.adListener = object : AdListener() {
            override fun onAdLoaded() {
                super.onAdLoaded()
                d("()")
            }

            override fun onAdFailed(errorCode: Int) {
                super.onAdFailed(errorCode)
                d("errorCode = [$errorCode]")
            }
        }
        interstitialAd.loadAd(AdParam.Builder().build())
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
        if (onClosedOrNotShowed != null) {
            onClosedOrNotShowed?.let { it(true) }
            onClosedOrNotShowed = null
        }
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
}