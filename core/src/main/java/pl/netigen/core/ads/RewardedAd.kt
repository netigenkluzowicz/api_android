package pl.netigen.core.ads

import android.util.Log
import androidx.annotation.IntDef
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.reward.RewardedVideoAdListener
import pl.netigen.core.netigenapi.NetigenViewModel
import pl.netigen.core.rewards.RewardItem
import pl.netigen.core.rewards.RewardListenersList
import pl.netigen.core.rewards.RewardsListener

class RewardedAd(var viewModel: NetigenViewModel, val activity: AppCompatActivity, rewardsListener: RewardsListener) : RewardedVideoAdListener {

    @IntDef(RewardError.FAILED_TO_LOAD, RewardError.NOT_LOADED_YET, RewardError.FAILED_TO_REWARD)
    @Retention(AnnotationRetention.SOURCE)
    annotation class RewardError {
        companion object {
            const val FAILED_TO_LOAD = 0
            const val NOT_LOADED_YET = 1
            const val FAILED_TO_REWARD = 2
        }
    }

    private var rewardItems: List<RewardItem> = ArrayList()
    private var rewardsListeners: RewardListenersList = RewardListenersList()
    private var rewardedVideoAd: RewardedVideoAd = MobileAds.getRewardedVideoAdInstance(activity)

    var customRewardedAdId: String? = null

    init {
        rewardedVideoAd.rewardedVideoAdListener = this
        rewardsListeners.add(rewardsListener)
    }

    fun showRewardedVideoAd() {

    }

    fun showRewardedVideoForItems(rewardItems: List<RewardItem>) {
        if (viewModel.isNoAdsBought) return
        this.rewardItems = rewardItems
        if (rewardedVideoAd.isLoaded) {
            rewardedVideoAd.show()
        } else {
            loadIfNotLoading()
            for (listener in rewardsListeners) {
                listener?.onFail(RewardError.NOT_LOADED_YET)
            }
        }
    }

    fun onDestroy() {
        rewardsListeners.clear()
        rewardedVideoAd.destroy(activity)
    }

    override fun onRewardedVideoAdLoaded() {
        Log.i(TAG, "onRewardedVideoAdLoaded: ")
        viewModel.isRewardedAdLoading = false
    }

    override fun onRewardedVideoAdOpened() {
        viewModel.isRewardedAdLoading = false
        Log.i(TAG, "onRewardedVideoAdOpened: ")
    }

    override fun onRewardedVideoStarted() {
        viewModel.isRewardedAdLoading = false
        Log.i(TAG, "onRewardedVideoStarted: ")
    }

    override fun onRewardedVideoAdClosed() {
        Log.i(TAG, "onRewardedVideoAdClosed: ")
        viewModel.isRewardedAdLoading = false
        loadIfNotLoading()
    }

    internal fun loadIfNotLoading() {
        if (!rewardedVideoAd.isLoaded && !viewModel.isRewardedAdLoading) {
            load()
        }
    }

    private fun load() {
        if (customRewardedAdId != null) {
            rewardedVideoAd.loadAd(customRewardedAdId, AdRequest.Builder().build())
        } else {
            rewardedVideoAd.loadAd(viewModel.getRewardedAdId(), AdRequest.Builder().build())
        }
        viewModel.isRewardedAdLoading = true
    }

    internal fun reloadAd() {
        load()
    }

    internal fun loadIfNotLoadingForAdId(rewardedAdId: String?) {
        customRewardedAdId = rewardedAdId
        loadIfNotLoading()
    }

    override fun onRewarded(rewardItem: com.google.android.gms.ads.reward.RewardItem) {
        try {
            rewardsListeners.callOnSuccess(rewardItems)
        } catch (e: Exception) {
            e.printStackTrace()
            rewardsListeners.callOnFail(RewardError.NOT_LOADED_YET)
        }
    }

    override fun onRewardedVideoAdLeftApplication() {
        Log.i(TAG, "onRewardedVideoAdLeftApplication: ")
    }

    override fun onRewardedVideoAdFailedToLoad(i: Int) {
        Log.i(TAG, "onRewardedVideoAdFailedToLoad: i $i")
        viewModel.isRewardedAdLoading = false
        rewardsListeners.callOnFail(RewardError.FAILED_TO_LOAD)
    }

    override fun onRewardedVideoCompleted() {
        Log.i(TAG, "onRewardedVideoCompleted: ")
    }

    fun onResume() {
        rewardedVideoAd.resume(activity)
    }

    fun onPause() {
        rewardedVideoAd.pause(activity)
    }

    fun addListeners(listeners: RewardListenersList) {
        rewardsListeners.addAll(listeners)
    }

    fun removeListeners(listeners: RewardListenersList) {
        rewardsListeners.removeAll(listeners)
    }

    fun removeListener(listener: RewardsListener) {
        rewardsListeners.remove(listener)
    }
}

private const val TAG = "RewardedAd"
