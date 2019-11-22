package pl.netigen.core.ads

import android.os.Handler
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.InterstitialAd
import pl.netigen.core.netigenapi.NetigenViewModel

class InterstitialAdManager(private val viewModel: NetigenViewModel, val activity: AppCompatActivity, val adsManager: AdsManager) {
    private var isInBackground: Boolean = false
    private var interstitialAdError: Boolean = false
    private val interstitialAdHandler = Handler()

    private var adLoadingStartTime = 0L
    private var lastInterstitialAdDisplayTime: Long = 0
    private var timeToDelayInterstitial: Long = viewModel.delayBetweenInterstitialAds
    private var interstitialAd: InterstitialAd

    var maxWaitForInterstitialAfterSplash = maxWaitTimeForSplashInterstitial

    init {
        interstitialAd = InterstitialAd(activity)
        loadIfShouldBeLoaded()
    }

    fun loadIfShouldBeLoaded() {
        if (interstitialAd.isLoading || interstitialAd.isLoaded || viewModel.isNoAdsBought) return
        interstitialAd = InterstitialAd(activity)
        interstitialAd.adUnitId = viewModel.getInterstitialAdId()
        interstitialAd.adListener = object : AdListener() {
            override fun onAdFailedToLoad(errorCode: Int) {
                super.onAdFailedToLoad(errorCode)
                interstitialAdError = true
            }
        }
        interstitialAd.loadAd(adsManager.getAdRequest())
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
        lastInterstitialAdDisplayTime == 0L || lastInterstitialAdDisplayTime + timeToDelayInterstitial < currentTime

    private fun onCanNotShow(showInterstitialListener: ShowInterstitialListener) {
        showInterstitialListener.onShowedOrNotLoaded(false)
        loadIfShouldBeLoaded()
    }

    private fun show() {
        lastInterstitialAdDisplayTime = SystemClock.elapsedRealtime()
        interstitialAd.show()
    }

    fun launchSplashLoaderOrOpenFragment(openFragment: () -> Unit) {
        interstitialAdHandler.removeCallbacksAndMessages(null)
        when {
            viewModel.isNoAdsBought -> openFragment()
            interstitialAd.isLoaded -> onLoadedOnSplash(openFragment)
            !adsManager.isOnline() -> openFragment()
            else -> startBackgroundLoading(openFragment)
        }
    }

    private fun onLoadedOnSplash(openFragment: () -> Unit) {
        interstitialAdHandler.removeCallbacksAndMessages(null)
        onLoaded(object : ShowInterstitialListener {
            override fun onShowedOrNotLoaded(success: Boolean) = openFragment()
        })
    }

    private fun startBackgroundLoading(openFragment: () -> Unit) {
        adLoadingStartTime = SystemClock.elapsedRealtime()
        val splashScreenLoader = SplashLoader { openFragment() }
        interstitialAdHandler.postDelayed(splashScreenLoader, handlerRefreshTime)
    }

    inner class SplashLoader constructor(var openFragment: () -> Unit) : Runnable {

        override fun run() {
            when {
                isInBackground -> onAdLoading()
                interstitialAd.isLoaded -> onLoadedOnSplash(openFragment)
                doesMaxLoadingTimeNotPassed() -> onAdLoading()
                else -> openFragment()
            }
        }

        private fun doesMaxLoadingTimeNotPassed() = SystemClock.elapsedRealtime() - adLoadingStartTime < maxWaitForInterstitialAfterSplash

        private fun onAdLoading() = if (interstitialAdError) onAdError() else refreshHandler()

        private fun onAdError() = if (adsManager.isOnline()) refreshHandler() else openFragment()

        private fun refreshHandler() {
            interstitialAdHandler.postDelayed(this, handlerRefreshTime)
        }
    }

    interface ShowInterstitialListener {
        fun onShowedOrNotLoaded(success: Boolean)
    }

    fun onDestroy() = interstitialAdHandler.removeCallbacksAndMessages(null)

    fun onResume() {
        isInBackground = false
    }

    fun onPause() {
        isInBackground = true
    }

    companion object {
        const val handlerRefreshTime: Long = 333
        const val maxWaitTimeForSplashInterstitial: Long = 7000
    }
}