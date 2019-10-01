package pl.netigen.core.ads

import android.content.Context
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.InterstitialAd
import pl.netigen.core.netigenapi.NetigenViewModel

class InterstitialAdManager(private val viewModel: NetigenViewModel, val activity: AppCompatActivity, val adsManager: AdsManager) {

    private val REFRESH_TIME: Long = 333
    private val DEFAULT_MIN_WAIT: Long = 2000
    private val DEFAULT_MAX_WAIT: Long = 7000

    val interstitialAdHandler = Handler()

    private var lastInterstitialAdDisplayTime: Long = 0
    private var interstitialAdError: Boolean = false
    private var timeToDelayInterstitial: Long = 0
    private var isSplashInBackground: Boolean = false

    private var interstitialAd: InterstitialAd

    var minWaitForInterstitialAfterSplash = DEFAULT_MIN_WAIT
    var maxWaitForInterstitialAfterSplash = DEFAULT_MAX_WAIT

    init {
        interstitialAd = InterstitialAd(activity)
        load(activity)
    }

    fun loadIfPossible() {
        if (interstitialAdError && viewModel.isMultiFullscreenApp) {
            load(activity)
        }
    }

    fun setDelayBetweenAds(delayTime: Long) {
        this.timeToDelayInterstitial = delayTime
    }

    fun show(showInterstitialListener: ShowInterstitialListener) {
        if (viewModel.isNoAdsBought) {
            showInterstitialListener.onShowedOrNotLoaded(false)
            return
        }
        if (interstitialAd.isLoaded)
            onLoaded(showInterstitialListener)
        else
            onNotLoaded(showInterstitialListener)
    }

    private fun onNotLoaded(showInterstitialListener: ShowInterstitialListener) {
        showInterstitialListener.onShowedOrNotLoaded(false)
        if (!interstitialAd.isLoading)
            load(activity)
    }

    private fun onLoaded(showInterstitialListener: ShowInterstitialListener) {
        interstitialAd.adListener = object : AdListener() {
            override fun onAdClosed() {
                showInterstitialListener.onShowedOrNotLoaded(true)
                if (viewModel.isMultiFullscreenApp)
                    load(activity)
            }
        }
        if (timeToDelayInterstitial == 0L)
            interstitialAd.show()
        else {
            val currentTime = System.currentTimeMillis()
            if (lastInterstitialAdDisplayTime == 0L || lastInterstitialAdDisplayTime + timeToDelayInterstitial < currentTime) {
                interstitialAd.show()
                lastInterstitialAdDisplayTime = currentTime
            }
        }
    }

    private fun load(context: Context) {
        interstitialAd = InterstitialAd(context)
        interstitialAd.adUnitId = viewModel.interstitialAdId
        interstitialAd.adListener = object : AdListener() {
            override fun onAdFailedToLoad(errorCode: Int) {
                super.onAdFailedToLoad(errorCode)
                interstitialAdError = true
            }
        }
        interstitialAd.loadAd(adsManager.getAdRequest())
    }

    fun launchSplashLoaderOrOpenFragment(openFragment: () -> Unit) {
        lastInterstitialAdDisplayTime = System.currentTimeMillis()
        interstitialAdHandler.removeCallbacksAndMessages(null)
        if (viewModel.isNoAdsBought) {
            openFragment()
            return
        }
        if (interstitialAd.isLoaded) {
            show(object : ShowInterstitialListener {
                override fun onShowedOrNotLoaded(success: Boolean) {
                    if (success) openFragment()
                }
            })
        } else if (!adsManager.isOnline()) {
            openFragment()
        } else {
            val splashScreenLoader = SplashLoader { openFragment() }
            interstitialAdHandler.postDelayed(splashScreenLoader, REFRESH_TIME)
        }
    }

    inner class SplashLoader constructor(var openFragment: () -> Unit) : Runnable {

        override fun run() {
            if (refreshHandler()) {
                interstitialAdHandler.postDelayed(this, REFRESH_TIME)
            } else {
                if (viewModel.isNoAdsBought) {
                    openFragment()
                    return
                }
                if (interstitialAd.isLoaded) {
                    show(object : ShowInterstitialListener {
                        override fun onShowedOrNotLoaded(success: Boolean) {
                            if (success) openFragment()
                        }
                    })
                    interstitialAdHandler.removeCallbacksAndMessages(null)
                } else if (System.currentTimeMillis() - lastInterstitialAdDisplayTime < maxWaitForInterstitialAfterSplash) {
                    if (interstitialAdError) {
                        if (adsManager.isOnline()) {
                            interstitialAdHandler.postDelayed(this, REFRESH_TIME)
                        } else {
                            openFragment()
                        }
                    } else {
                        interstitialAdHandler.postDelayed(this, REFRESH_TIME)
                    }
                } else {
                    openFragment()
                }
            }
        }

        private fun refreshHandler(): Boolean {
            return System.currentTimeMillis() - lastInterstitialAdDisplayTime < minWaitForInterstitialAfterSplash || isSplashInBackground
        }
    }

    interface ShowInterstitialListener {
        fun onShowedOrNotLoaded(success: Boolean)
    }
}