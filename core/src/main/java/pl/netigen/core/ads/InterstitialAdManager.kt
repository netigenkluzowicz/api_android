package pl.netigen.core.ads

import android.os.Handler
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.InterstitialAd
import pl.netigen.core.netigenapi.NetigenViewModel

class InterstitialAdManager(private val viewModel: NetigenViewModel, val activity: AppCompatActivity, val adsManager: AdsManager) {

    private var interstitialAdError: Boolean = false
    private val interstitialAdHandler = Handler()

    private var adLoadingStartTime = 0L
    private var lastInterstitialAdDisplayTime: Long = 0
    private var timeToDelayInterstitial: Long = viewModel.delayBetweenInterstitialAds
    private var interstitialAd: InterstitialAd

    var maxWaitForInterstitialAfterSplash = maxWaitTimeForSplashInterstitial

    init {
        interstitialAd = InterstitialAd(activity)
        load()
    }

    fun load() {
        if (interstitialAd.isLoading || interstitialAd.isLoaded) return
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
        if (viewModel.isNoAdsBought) {
            showInterstitialListener.onShowedOrNotLoaded(false)
            return
        }
        if (interstitialAd.isLoaded) {
            onLoaded(showInterstitialListener)
        } else {
            onNotLoaded(showInterstitialListener)
        }
    }

    private fun onLoaded(showInterstitialListener: ShowInterstitialListener) {
        val currentTime = SystemClock.elapsedRealtime()
        interstitialAd.adListener = object : AdListener() {
            override fun onAdClosed() {
                showInterstitialListener.onShowedOrNotLoaded(true)
                load()
            }
        }
        if (shouldShowAd(currentTime)) {
            showInterstitialAd()
        } else {
            showInterstitialListener.onShowedOrNotLoaded(false)
        }
    }

    private fun shouldShowAd(currentTime: Long) =
            lastInterstitialAdDisplayTime == 0L || lastInterstitialAdDisplayTime + timeToDelayInterstitial < currentTime

    private fun onNotLoaded(showInterstitialListener: ShowInterstitialListener) {
        showInterstitialListener.onShowedOrNotLoaded(false)
        load()
    }

    private fun showInterstitialAd() {
        lastInterstitialAdDisplayTime = SystemClock.elapsedRealtime()
        interstitialAd.show()
    }

    fun launchSplashLoaderOrOpenFragment(openFragment: () -> Unit) {
        interstitialAdHandler.removeCallbacksAndMessages(null)
        if (viewModel.isNoAdsBought) {
            openFragment()
            return
        }
        if (interstitialAd.isLoaded) {
            showInterstitialAfterSplash(object : ShowInterstitialListener {
                override fun onShowedOrNotLoaded(success: Boolean) {
                    openFragment()
                }
            })
        } else if (!adsManager.isOnline()) {
            openFragment()
        } else {
            adLoadingStartTime = SystemClock.elapsedRealtime()
            val splashScreenLoader = SplashLoader { openFragment() }
            interstitialAdHandler.postDelayed(splashScreenLoader, handlerRefreshTime)
        }
    }

    private fun showInterstitialAfterSplash(showInterstitialListener: ShowInterstitialListener) {
        if (viewModel.isNoAdsBought) {
            showInterstitialListener.onShowedOrNotLoaded(false)
            return
        }
        if (interstitialAd.isLoaded) {
            interstitialAd.adListener = object : AdListener() {
                override fun onAdClosed() {
                    showInterstitialListener.onShowedOrNotLoaded(true)
                    load()
                }
            }
            showInterstitialAd()
        } else {
            onNotLoaded(showInterstitialListener)
        }
    }

    inner class SplashLoader constructor(var openFragment: () -> Unit) : Runnable {

        override fun run() {
            when {
                interstitialAd.isLoaded -> {
                    showAd()
                    interstitialAdHandler.removeCallbacksAndMessages(null)
                }
                doesMaxLoadingTimeNotPassed() -> {
                    onAdLoading()
                }
                else -> openFragment()
            }
        }

        private fun showAd() {
            showInterstitialAfterSplash(object : ShowInterstitialListener {
                override fun onShowedOrNotLoaded(success: Boolean) {
                    openFragment()
                }
            })
        }

        private fun doesMaxLoadingTimeNotPassed() =
                SystemClock.elapsedRealtime() - adLoadingStartTime < maxWaitForInterstitialAfterSplash

        private fun onAdLoading() {
            if (interstitialAdError) {
                onAdError()
            } else {
                refreshHandler()
            }
        }

        private fun onAdError() {
            if (adsManager.isOnline()) {
                refreshHandler()
            } else {
                openFragment()
            }
        }

        private fun refreshHandler() {
            interstitialAdHandler.postDelayed(this, handlerRefreshTime)
        }
    }

    interface ShowInterstitialListener {
        fun onShowedOrNotLoaded(success: Boolean)
    }

    fun onDestroy(){
        interstitialAdHandler.removeCallbacksAndMessages(null)
    }

    companion object {
        const val handlerRefreshTime: Long = 333
        const val maxWaitTimeForSplashInterstitial: Long = 7000
    }
}