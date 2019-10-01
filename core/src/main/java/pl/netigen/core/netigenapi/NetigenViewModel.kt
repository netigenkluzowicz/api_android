package pl.netigen.core.netigenapi

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import pl.netigen.core.config.Config
import pl.netigen.core.config.ConfigBuilder

abstract class NetigenViewModel(application: Application) : AndroidViewModel(application) {

    var isSplashInBackground: Boolean = false

    abstract fun prepareConfigBuilder(): ConfigBuilder
    var config: Config = prepareConfigBuilder().createConfig()

    var noAdsLiveData = MutableLiveData<Boolean>()
    var delayBetweenInterstitialAds = 60L * 1000L
    val noAdsSku = application.packageName + ".noads"
    var isRewardedAdLoading: Boolean = false
    var isNoAdsPaymentAvailable: Boolean = config.isNoAdsPaymentAvailable
    var isDesignedForFamily: Boolean = config.isDesignedForFamily
    var publishersIds = config.publishersId
    var isNoAdsBought: Boolean = false
        get() = config.isNoAdsBought
        set(isNoAdsBought) {
            noAdsLiveData.value = isNoAdsBought
            field = isNoAdsBought
        }

    val interstitialAdId: String = config.interstitalAdId
    fun getRewardedAdId() = config.rewardedAdId
    fun getBannerId() = config.bannerAdId
    fun isInDebugMode() = config.inDebugMode
    fun getTestDevices() = config.testDevices
    fun isSamsung() = config.isSamsung
    val isMultiFullscreenApp: Boolean = config.isMultiScreen

}