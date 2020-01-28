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


class AdMobRewarded(
    private val activity: ComponentActivity,
    private val adMobRequest: IAdMobRequest,
    override val adId: String = "",
    override var enabled: Boolean = adId.isNotEmpty()
) : IRewardedAd, LifecycleObserver {
    override val isLoaded: Boolean get() = isEnabled && rewardedAd.isLoaded
    private val rewardedAd: RewardedAd by lazy { RewardedAd(activity, adId) }
    private val isEnabled: Boolean get() = enabled && adId.isNotEmpty()
    private var retryCount = 0

    init {
        activity.lifecycle.addObserver(this)
    }

    override fun showRewardedAd(onRewardResult: (Boolean) -> Unit) {
        if (!isLoaded) return onRewardResult(false)
        rewardedAd.show(activity, AdCallback(onRewardResult))
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() = load()

    private fun load() {
        if (isEnabled && !isLoaded) {
            rewardedAd.loadAd(adMobRequest.getAdRequest(), AdLoadCallback())
        }
    }

    inner class AdCallback(val onRewardResult: (Boolean) -> Unit) : RewardedAdCallback() {

        override fun onRewardedAdClosed() = onResult(false)

        override fun onUserEarnedReward(@NonNull reward: RewardItem) = onResult(true)

        override fun onRewardedAdFailedToShow(p0: Int) = onResult(false)

        private fun onResult(result: Boolean) {
            onRewardResult(result)
            load()
        }
    }

    inner class AdLoadCallback : RewardedAdLoadCallback() {
        override fun onRewardedAdFailedToLoad(errorCode: Int) {
            if (retryCount <= Companion.MAX_RETRY_COUNT) {
                retryCount++
                load()
            }
        }
    }

    companion object {
        private const val MAX_RETRY_COUNT = 2
    }
}