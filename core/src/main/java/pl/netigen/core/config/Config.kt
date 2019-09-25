package pl.netigen.core.config

import pl.netigen.core.utils.SingletonHolder

class Config private constructor(val inDebugMode: Boolean, val isSamsung: Boolean) {

    private val testRewardedAdId = "ca-app-pub-3940256099942544/5224354917"
    private val testBannerAdId = "ca-app-pub-3940256099942544/6300978111"
    private val testInterstitialAdId = "ca-app-pub-3940256099942544/1033173712"

    var isNoAdsBought: Boolean = false

    var rewardedAdId: String? = null
    var bannerAdId: String = testBannerAdId
    var interstitalAdId: String = testInterstitialAdId

    var adMobAppId: String? = null

    var isMultiScreen = false
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


