package pl.netigen.gms.ads

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.google.android.gms.ads.*
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import pl.netigen.coreapi.ads.IAdsConfig.Companion.REWARD_AD_MAX_RETRY_COUNT
import pl.netigen.coreapi.ads.IRewardedAd
import timber.log.Timber.Forest.d

/**
 * [IRewardedAd] implementation with [RewardedAd] from Google Mobile Ads SDK.
 *
 * @property adMobRequest Provides [AdRequest] for this ad.
 * @property adId Current ad identifier.
 * @property enabled Indicates whether this ad is active.
 *
 * Initializes the ad and registers a [DefaultLifecycleObserver] on the provided [LifecycleOwner].
 *
 * @param activity [ComponentActivity] used as [Context] and as a [LifecycleOwner]
 * for lifecycle callbacks.
 */
class AdMobRewarded(
    private val activity: ComponentActivity,
    private val adMobRequest: IAdMobRequest,
    override val adId: String = "",
    override var enabled: Boolean = adId.isNotEmpty(),
) : IRewardedAd, DefaultLifecycleObserver {
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
        showAdmob(onRewardResult)
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


    override fun onCreate(owner: LifecycleOwner) {
        d("()")
        if (enabled) {
            load()
        }
    }

    override fun onDestroy(owner: LifecycleOwner) {
        activity.lifecycle.removeObserver(this)
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
