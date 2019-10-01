package pl.netigen.core.config

import pl.netigen.core.utils.SingletonHolder

class Config private constructor(val inDebugMode: Boolean, val isSamsung: Boolean) {

    private val testRewardedAdId = "ca-app-pub-3940256099942544/5224354917"
    private val testBannerAdId = "ca-app-pub-3940256099942544/6300978111"
    private val testInterstitialAdId = "ca-app-pub-3940256099942544/1033173712"

    var isNoAdsBought: Boolean = false
    var isNoAdsPaymentAvailable: Boolean = true
    var isDesignedForFamily: Boolean = false
    var rewardedAdId: String? = null
    var adMobAppId: String? = null
    var publishersId = arrayOf("pub-4699516034931013")
    var isMultiScreen = false
    var bannerAdId: String = testBannerAdId
    var interstitalAdId: String = testInterstitialAdId

    lateinit var testDevices: List<String>

    init {
        if(inDebugMode){
            bannerAdId = testBannerAdId
            interstitalAdId = testInterstitialAdId
        }
        testDevices = ArrayList<String>()
    }

    companion object : SingletonHolder<Config, Boolean, Boolean>(::Config){

    }

}


