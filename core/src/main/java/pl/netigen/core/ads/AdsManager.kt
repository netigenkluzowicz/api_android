package pl.netigen.core.ads

import android.app.Activity

import com.google.android.gms.ads.MobileAds

import pl.netigen.core.netigenapi.NetigenViewModel

class AdsManager(var viewModel: NetigenViewModel, val activity: Activity) {

    lateinit var rewardedAd: RewardedAd
    lateinit var bannerAdManager: BannerAdManager
    lateinit var interstitialAd: InterstitialAdManager


    init {
        MobileAds.initialize(activity)
        this.rewardedAd = RewardedAd(viewModel, activity)
    }


}