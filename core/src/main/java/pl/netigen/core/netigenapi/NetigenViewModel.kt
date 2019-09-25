package pl.netigen.core.netigenapi

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import pl.netigen.core.config.Config
import pl.netigen.core.config.ConfigBuilder

abstract class NetigenViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var config: Config
    abstract fun prepareConfigBuilder(): ConfigBuilder
    var noAdsLiveData = MutableLiveData<Boolean>()
    var delayBetweenInterstitialAds = 60L * 1000L
    val noAdsSku = application.packageName + ".noads"
    var isRewardedAdLoading: Boolean = false

    var isNoAdsBought: Boolean = false
        get() = config.isNoAdsBought
        set(isNoAdsBought) {
            noAdsLiveData.value = isNoAdsBought
            field = isNoAdsBought
        }

    fun setupConfig() {
        this.config = prepareConfigBuilder().createConfig()
    }

    val interstitialAdId: String = config.interstitalAdId
    fun getRewardedAdId() = config.rewardedAdId
    fun getBannerId() = config.bannerAdId
    fun isInDebugMode() = config.inDebugMode
    fun getTestDevices() = config.testDevices
    fun isSamsung() = config.isSamsung
    val isMultiFullscreenApp: Boolean = config.isMultiScreen

}