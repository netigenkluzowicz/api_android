package pl.netigen.core.netigenapi

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import pl.netigen.core.config.Config
import pl.netigen.core.config.ConfigBuilder

abstract class NetigenViewModel(application: Application) : AndroidViewModel(application) {

    var isRewardedAdLoading: Boolean = false
    val noAdsSku = application.packageName + ".noads"
    lateinit var config: Config

    abstract fun prepareConfigBuilder(): ConfigBuilder

    fun setupConfig() {
        this.config = prepareConfigBuilder().createConfig()
    }

    fun getRewardedAdId() = config.rewardedAdId
    fun isNoAdsBought() = config.isNoAdsBought
    fun getBannerId() = config.bannerAdId
    fun isInDebugMode() = config.inDebugMode
    fun getTestDevices() = config.testDevices
    fun isSamsung() = config.isSamsung
}