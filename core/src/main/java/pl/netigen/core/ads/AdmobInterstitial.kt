package pl.netigen.core.ads

import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.InterstitialAd
import pl.netigen.core.netigenapi.NetigenViewModel

class AdmobInterstitial(
    private val viewModel: NetigenViewModel,
    val activity: AppCompatActivity,
    private val admobAds: AdmobAds,
    private val minDelayBetweenInterstitial: Long = viewModel.delayBetweenInterstitialAds
) {
    private var isInBackground: Boolean = false
    private var interstitialAdError: Boolean = false
    private var lastInterstitialAdDisplayTime: Long = 0
    private var interstitialAd: InterstitialAd

    init {
        interstitialAd = InterstitialAd(activity)
        loadIfShouldBeLoaded()
    }

    fun loadIfShouldBeLoaded() {
        if (interstitialAd.isLoading || interstitialAd.isLoaded || viewModel.isNoAdsBought) return
        load()
    }

    private fun load() {
        interstitialAd = InterstitialAd(activity)
        interstitialAd.adUnitId = viewModel.getInterstitialAdId()
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
            viewModel.isNoAdsBought -> showInterstitialListener.onShowedOrNotLoaded(false)
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

    fun onResume() {
        isInBackground = false
    }

    fun onPause() {
        isInBackground = true
    }
}