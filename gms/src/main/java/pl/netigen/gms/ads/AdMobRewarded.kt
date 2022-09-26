package pl.netigen.gms.ads

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.google.android.gms.ads.*
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
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
    override var enabled: Boolean = adId.isNotEmpty(),
) : IRewardedAd, LifecycleObserver {
    override val isLoaded: Boolean get() = isEnabled && rewardedAd != null
    private var rewardedAd: RewardedAd? = null
    private val isEnabled: Boolean get() = enabled && adId.isNotEmpty()
    private var retryCount = 0

    init {
        d("()")
        activity.lifecycle.addObserver(this)
    }

    override fun showRewardedAd(onRewardResult: (Boolean) -> Unit) {
        d("onRewardResult = [$onRewardResult]")
        if (!isLoaded) {
            load()
            return onRewardResult(false)
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
