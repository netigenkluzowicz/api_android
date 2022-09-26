package pl.netigen.hms.ads

import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.huawei.hms.ads.AdParam
import com.huawei.hms.ads.reward.Reward
import com.huawei.hms.ads.reward.RewardAd
import com.huawei.hms.ads.reward.RewardAdLoadListener
import com.huawei.hms.ads.reward.RewardAdStatusListener
import pl.netigen.coreapi.ads.IAdsConfig.Companion.REWARD_AD_MAX_RETRY_COUNT
import pl.netigen.coreapi.ads.IRewardedAd
import timber.log.Timber.Forest.d

class HMSRewarded(
    private val activity: ComponentActivity,
    override val adId: String = "",
    override var enabled: Boolean = adId.isNotEmpty(),
) : IRewardedAd, LifecycleObserver {
    override val isLoaded: Boolean get() = isEnabled && rewardAd.isLoaded
    private var rewardAd = RewardAd(activity, adId)
    private val isEnabled: Boolean get() = enabled && adId.isNotEmpty()
    private var retryCount = 0

    init {
        d("()")
        activity.lifecycle.addObserver(this)
    }

    override fun showRewardedAd(onRewardResult: (Boolean) -> Unit) {
        if (!isLoaded) {
            load()
            return onRewardResult(false)
        }
        rewardAd.show(activity, AdCallback(onRewardResult))
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onCreate() {
        d("()")
        load()
    }

    private fun load() {
        d("()")
        if (isEnabled) rewardAd.loadAd(AdParam.Builder().build(), AdLoadCallback())
    }

    inner class AdCallback(val onRewardResult: (Boolean) -> Unit) : RewardAdStatusListener() {

        override fun onRewardAdClosed() = rewardAdCallbackResult(false)

        override fun onRewarded(p0: Reward?) = rewardAdCallbackResult(true)

        override fun onRewardAdFailedToShow(p0: Int) = rewardAdCallbackResult(false)

        private fun rewardAdCallbackResult(result: Boolean) {
            d("result = [$result]")
            onRewardResult(result)
            load()
        }
    }

    inner class AdLoadCallback : RewardAdLoadListener() {
        override fun onRewardedLoaded() {
            d("()")
        }

        override fun onRewardAdFailedToLoad(errorCode: Int) {
            d("errorCode = [$errorCode]")
            if (retryCount <= REWARD_AD_MAX_RETRY_COUNT) {
                retryCount++
                d("retry load: $retryCount")
                load()
            }
        }
    }
}
