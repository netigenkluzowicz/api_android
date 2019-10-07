package pl.netigen.core.config

import kotlin.properties.Delegates

class Config constructor(val inDebugMode: Boolean, val isSamsung: Boolean,
                         var isNoAdsBought: Boolean = false, var isNoAdsPaymentAvailable: Boolean = true,
                         var isDesignedForFamily: Boolean = false, var rewardedAdId: String? = null,
                         var adMobAppId: String? = null, var publishersId: Array<String> = arrayOf("pub-4699516034931013"),
                         var isMultiScreen: Boolean = false, var bannerAdId: String = testBannerAdId,
                         var interstitialAdId: String = testInterstitialAdId, var testDevices: ArrayList<String> = ArrayList()) {

    init {
        isSamsungStatic = isSamsung
        if (inDebugMode) {
            bannerAdId = testBannerAdId
            interstitialAdId = testInterstitialAdId
            if (rewardedAdId != null) {
                rewardedAdId = testRewardedAdId
            }
        }
    }

    companion object {
        var isSamsungStatic by Delegates.notNull<Boolean>()
        const val testRewardedAdId = "ca-app-pub-3940256099942544/5224354917"
        val testBannerAdId = "ca-app-pub-3940256099942544/6300978111"
        val testInterstitialAdId = "ca-app-pub-3940256099942544/1033173712"
    }

}
