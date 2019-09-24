package pl.netigen.core.ads

import android.app.Activity
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
    private lateinit var rewardedVideoAd: RewardedVideoAd
    var customRewardedAdId: String? = null

    internal fun init() {
        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(activity)
        rewardedVideoAd.rewardedVideoAdListener = this
    }

    internal fun loadRewardedVideo() {
        if (!rewardedVideoAd.isLoaded && !viewModel.isRewardedAdLoading) {
            rewardedVideoAd.loadAd(viewModel.config.rewardedAdId, AdRequest.Builder().build())
            viewModel.isRewardedAdLoading = true
        }
    }

    internal fun loadRewardedVideoForAdId(rewardedAdId: String?) {
        customRewardedAdId = rewardedAdId
        if (!rewardedVideoAd.isLoaded && !viewModel.isRewardedAdLoading) {
            rewardedVideoAd.loadAd(customRewardedAdId, AdRequest.Builder().build())
            viewModel.isRewardedAdLoading = true
        }
    }

    fun showRewardedVideoAd() {

    }

    fun showRewardedVideoForItems(rewardItems: List<RewardItem>) {
        if (viewModel.isNoAdsBought()) return
        this.rewardItems = rewardItems
        if (rewardedVideoAd != null && rewardedVideoAd.isLoaded) {
            rewardedVideoAd.show()
        } else {
            if (!viewModel.isRewardedAdLoading) {
                reloadAd()
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
        reloadAd()
    }

    private fun reloadAd() {
        if (customRewardedAdId != null) {
            loadRewardedVideoForAdId(customRewardedAdId)
        } else {
            loadRewardedVideo()
        }
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