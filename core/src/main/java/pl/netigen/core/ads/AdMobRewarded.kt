package pl.netigen.core.ads

import androidx.activity.ComponentActivity
import androidx.annotation.NonNull
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdCallback
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import pl.netigen.coreapi.ads.IRewardedAd
import timber.log.Timber.d


class AdMobRewarded(
    private val activity: ComponentActivity,
    private val adMobRequest: IAdMobRequest,
    override val adId: String = "",
    override var enabled: Boolean = adId.isNotEmpty()
) : IRewardedAd, LifecycleObserver {
    override val isLoaded: Boolean get() = isEnabled && rewardedAd.isLoaded
    private var rewardedAd = RewardedAd(activity, adId)
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
        rewardedAd.show(activity, AdCallback(onRewardResult))
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onCreate() {
        d("()")
        load()
    }

    private fun load() {
        if (isEnabled && !isLoaded) {
            d("()")
            rewardedAd.loadAd(adMobRequest.getAdRequest(), AdLoadCallback())
        }
    }

    inner class AdCallback(val onRewardResult: (Boolean) -> Unit) : RewardedAdCallback() {
        private var success = false

        override fun onRewardedAdClosed() = rewardAdCallbackResult(success)

        override fun onUserEarnedReward(@NonNull reward: RewardItem) {
            success = true
        }

        override fun onRewardedAdFailedToShow(p0: Int) = rewardAdCallbackResult(false)

        private fun rewardAdCallbackResult(result: Boolean) {
            d("result = [$result]")
            onRewardResult(result)
            load()
        }
    }

    inner class AdLoadCallback : RewardedAdLoadCallback() {
        override fun onRewardedAdLoaded() {
            d("()")
        }

        override fun onRewardedAdFailedToLoad(errorCode: Int) {
            d("errorCode = [$errorCode]")
            if (retryCount <= Companion.MAX_RETRY_COUNT) {
                retryCount++
                d("retry load: $retryCount")
                load()
            }
        }
    }

    companion object {
        private const val MAX_RETRY_COUNT = 2
    }
}