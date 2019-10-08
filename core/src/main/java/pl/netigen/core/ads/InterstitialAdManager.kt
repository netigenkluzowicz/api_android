package pl.netigen.core.ads

import android.content.Context
import android.os.Handler
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.InterstitialAd
import pl.netigen.core.netigenapi.NetigenViewModel

class InterstitialAdManager(private val viewModel: NetigenViewModel, val activity: AppCompatActivity, val adsManager: AdsManager) {

    private var interstitialAdError: Boolean = false
    val interstitialAdHandler = Handler()

    private var lastInterstitialAdDisplayTime: Long = 0
    private var timeToDelayInterstitial: Long = viewModel.delayBetweenInterstitialAds
    private var isSplashInBackground: Boolean = false
    private var interstitialAd: InterstitialAd

    var minWaitForInterstitialAfterSplash = minWaitTimeForSplashInterstitial
    var maxWaitForInterstitialAfterSplash = maxWaitTimeForSplashInterstitial

    init {
        interstitialAd = InterstitialAd(activity)
        load(activity)
    }

    fun loadIfPossible() {
        if (viewModel.isMultiFullscreenApp) {
            load(activity)
        }
    }

    private fun load(context: Context) {
        if (interstitialAd.isLoading || interstitialAd.isLoaded) return
        interstitialAd = InterstitialAd(context)
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
                if (viewModel.isMultiFullscreenApp)
                    load(activity)
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
        load(activity)
    }

    private fun showInterstitialAd() {
        lastInterstitialAdDisplayTime = SystemClock.elapsedRealtime()
        interstitialAd.show()
    }

    fun launchSplashLoaderOrOpenFragment(openFragment: () -> Unit) {
        lastInterstitialAdDisplayTime = SystemClock.elapsedRealtime()
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
                    if (viewModel.isMultiFullscreenApp)
                        load(activity)
                }
            }
            showInterstitialAd()
        } else {
            onNotLoaded(showInterstitialListener)
        }
    }

    inner class SplashLoader constructor(var openFragment: () -> Unit) : Runnable {

        override fun run() {
            if (refreshHandler()) {
                interstitialAdHandler.postDelayed(this, handlerRefreshTime)
            } else {
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
                    interstitialAdHandler.removeCallbacksAndMessages(null)
                } else if (SystemClock.elapsedRealtime() - lastInterstitialAdDisplayTime < maxWaitForInterstitialAfterSplash) {
                    if (interstitialAdError) {
                        if (adsManager.isOnline()) {
                            interstitialAdHandler.postDelayed(this, handlerRefreshTime)
                        } else {
                            openFragment()
                        }
                    } else {
                        interstitialAdHandler.postDelayed(this, handlerRefreshTime)
                    }
                } else {
                    openFragment()
                }
            }
        }

        private fun refreshHandler(): Boolean {
            return SystemClock.elapsedRealtime() - lastInterstitialAdDisplayTime < minWaitForInterstitialAfterSplash || isSplashInBackground
        }
    }

    interface ShowInterstitialListener {
        fun onShowedOrNotLoaded(success: Boolean)
    }

    companion object {
        const val handlerRefreshTime: Long = 333
        const val minWaitTimeForSplashInterstitial: Long = 2000
        const val maxWaitTimeForSplashInterstitial: Long = 7000
    }
}