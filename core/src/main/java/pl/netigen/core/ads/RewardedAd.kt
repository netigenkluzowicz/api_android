package pl.netigen.core.ads

import android.app.Activity
import android.util.Log
import androidx.annotation.IntDef
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.reward.RewardedVideoAdListener
import pl.netigen.core.netigenapi.NetigenViewModel
import pl.netigen.core.rewards.RewardItem
import pl.netigen.core.rewards.RewardListenersList
import pl.netigen.core.rewards.RewardsListener

class RewardedAd(var viewModel: NetigenViewModel, val activity: Activity) : RewardedVideoAdListener {

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
    private lateinit var rewardedVideoAd: RewardedVideoAd

    internal fun createRewardedVideo(rewardsListener: RewardsListener) {
        rewardsListeners.add(rewardsListener)
        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(activity)
        rewardedVideoAd.rewardedVideoAdListener = this
    }

    internal fun loadRewardedVideo(rewardedAdId: String?) {
        if (!rewardedVideoAd.isLoaded && !viewModel.isRewardedAdLoading) {
            rewardedVideoAd.loadAd(rewardedAdId, AdRequest.Builder().build())
            viewModel.isRewardedAdLoading = true
        }
    }

    fun showRewardedVideoForItems(rewardItems: List<RewardItem>, listeners: List<RewardsListener>?) {
        if (listeners != null)
            rewardsListeners.addAll(listeners)
        if (viewModel.isNoAdsBought()) return
        this.rewardItems = rewardItems
        if (rewardedVideoAd != null && rewardedVideoAd.isLoaded) {
            rewardedVideoAd.show()
        } else {
            if (!viewModel.isRewardedAdLoading) {
                loadRewardedVideo(viewModel.getRewardedAdId())
            }
            if (rewardsListeners != null) {
                for (listener in rewardsListeners) {
                    listener?.onFail(RewardError.NOT_LOADED_YET)
                }
            }
        }
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
        loadRewardedVideo(viewModel.getRewardedAdId())
    }

    override fun onRewarded(rewardItem: com.google.android.gms.ads.reward.RewardItem) {
        Log.i(TAG, "onRewarded: rewardItem type" + rewardItem.type + " amount " + rewardItem.amount)
        try {
            if (rewardsListeners != null) {
                rewardsListeners.callOnSuccess(rewardItems)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            if (rewardsListeners != null) {
                rewardsListeners.callOnFail(RewardError.NOT_LOADED_YET)
            }
        }
    }

    override fun onRewardedVideoAdLeftApplication() {
        Log.i(TAG, "onRewardedVideoAdLeftApplication: ")
    }

    override fun onRewardedVideoAdFailedToLoad(i: Int) {
        Log.i(TAG, "onRewardedVideoAdFailedToLoad: i $i")
        viewModel.isRewardedAdLoading = false
        if (rewardsListeners != null) {
            rewardsListeners.callOnFail(RewardError.FAILED_TO_LOAD)
        }
    }

    override fun onRewardedVideoCompleted() {
        Log.i(TAG, "onRewardedVideoCompleted: ")
    }

    private val TAG = "RewardedAd"

}