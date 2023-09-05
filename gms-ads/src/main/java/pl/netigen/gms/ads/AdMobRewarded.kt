package pl.netigen.gms.ads

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.google.android.gms.ads.*
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.yandex.mobile.ads.common.AdRequestConfiguration
import com.yandex.mobile.ads.common.AdRequestError
import com.yandex.mobile.ads.common.ImpressionData
import com.yandex.mobile.ads.rewarded.Reward
import com.yandex.mobile.ads.rewarded.RewardedAdEventListener
import com.yandex.mobile.ads.rewarded.RewardedAdLoadListener
import com.yandex.mobile.ads.rewarded.RewardedAdLoader
import pl.netigen.coreapi.ads.IAdsConfig.Companion.REWARD_AD_MAX_RETRY_COUNT
import pl.netigen.coreapi.ads.IRewardedAd
import timber.log.Timber.Forest.d

/**
 * [IRewardedAd] implementation with [RewardedAd] from Google Mobile Ads SDK
 *
 * @property adMobRequest adMobRequest Provides [AdRequest] for this ad
 * @property adId Current ad [String] identifier
 * @property enabled Current ad [String] identifier
 * @constructor
 * Initializes ad, starts observing activity [Lifecycle]
 *
 * @param activity [ComponentActivity] for this ad [Context] and [Lifecycle] events
 */
class AdMobRewarded(
    private val activity: ComponentActivity,
    private val adMobRequest: IAdMobRequest,
    override val adId: String = "",
    override val yandexAdId: String = "",
    override var enabled: Boolean = adId.isNotEmpty(),
) : IRewardedAd, LifecycleObserver {
    override val isLoaded: Boolean get() = isEnabled && rewardedAd != null
    private var rewardedAd: RewardedAd? = null
    private val isEnabled: Boolean get() = enabled && adId.isNotEmpty()
    private var retryCount = 0
    private var yandexActive = false
    private var yandexAd: com.yandex.mobile.ads.rewarded.RewardedAd? = null


    init {
        d("()")
        activity.lifecycle.addObserver(this)
    }

    override fun showRewardedAd(onRewardResult: (Boolean) -> Unit) {
        d("onRewardResult = [$onRewardResult]")
        if (yandexActive && yandexAdId.isNotEmpty()) showYandex(onRewardResult) else showAdmob(onRewardResult)
    }

    private fun showYandex(onRewardResult: (Boolean) -> Unit) {
        val ad = yandexAd
        if (ad == null || !enabled) {
            loadYandex()
            onRewardResult(false)
            return
        }

        ad.setAdEventListener(
            object : RewardedAdEventListener {
                override fun onAdShown() = Unit

                override fun onAdFailedToShow(p0: com.yandex.mobile.ads.common.AdError) {
                    onRewardResult(false)
                }

                override fun onAdDismissed() {
                    onRewardResult(false)
                    yandexAd?.setAdEventListener(null)
                    yandexAd = null
                    loadYandex()
                }

                override fun onAdClicked() = Unit

                override fun onAdImpression(p0: ImpressionData?) = Unit

                override fun onRewarded(p0: Reward) {
                    onRewardResult(true)
                }
            },
        )
        ad.show(activity)
    }

    private fun showAdmob(onRewardResult: (Boolean) -> Unit) {
        if (!isLoaded) {
            load()
            onRewardResult(false)
            return
        }
        val rewardedAd1 = rewardedAd
        var success = false
        if (rewardedAd1 != null) {
            rewardedAd1.show(activity) {
                success = true
            }
            rewardedAd1.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                    d("adError = [$adError]")
                    onRewardResult(false)
                    super.onAdFailedToShowFullScreenContent(adError)
                }

                override fun onAdShowedFullScreenContent() {
                    d("()")
                    super.onAdShowedFullScreenContent()
                }

                override fun onAdDismissedFullScreenContent() {
                    d("()")
                    onRewardResult(success)
                    rewardedAd = null
                    load()
                    super.onAdDismissedFullScreenContent()
                }
            }
        } else {
            onRewardResult(false)
        }
    }

    override fun enableYandex() {
        yandexActive = true
        loadYandex()
    }

    private fun loadYandex() {
        RewardedAdLoader(activity).apply {
            setAdLoadListener(
                object : RewardedAdLoadListener {
                    override fun onAdLoaded(ad: com.yandex.mobile.ads.rewarded.RewardedAd) {
                        d("ad = [$ad]")
                        yandexAd = ad
                    }

                    override fun onAdFailedToLoad(p0: AdRequestError) {
                        d("p0 = [$p0]")
                    }

                },
            )
        }.also { it.loadAd(AdRequestConfiguration.Builder(yandexAdId).build()) }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onCreate() {
        d("()")
        if (enabled) {
            load()
        }
    }

    private fun load() {
        RewardedAd.load(
            activity,
            adId,
            adMobRequest.getAdRequest(),
            object : RewardedAdLoadCallback() {
                override fun onAdLoaded(rewardedAd: RewardedAd) {
                    d("rewardedAd = [$rewardedAd]")
                    this@AdMobRewarded.rewardedAd = rewardedAd
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    d("loadAdError = [${loadAdError.message}]")
                    if (enabled && retryCount <= REWARD_AD_MAX_RETRY_COUNT) {
                        retryCount++
                        d("retry load: $retryCount")
                        load()
                    }
                }
            },
        )
    }
}
