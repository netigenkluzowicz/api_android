package pl.netigen.core.netigenapi

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import pl.netigen.core.config.Config

abstract class NetigenMainVM(application: Application) : AndroidViewModel(application) {
    abstract fun prepareConfigBuilder(): Config
    val config: Config by lazy {
        prepareConfigBuilder()
    }

    var isInEea = false
    var noAdsLiveData = MutableLiveData<Boolean>()
    var delayBetweenInterstitialAds = 60L * 1000L
    open val noAdsSku: String
        get() {
            return if (config.inDebugMode) {
                TODO() // PaymentManager.TEST_PURCHASED
            } else {
                getApplication<Application>().packageName + ".noads"
            }
        }

    var isRewardedAdLoading: Boolean = false
    var isNoAdsPaymentAvailable: Boolean = config.isNoAdsPaymentAvailable
    var isDesignedForFamily: Boolean = config.isDesignedForFamily
    var publishersIds = config.publishersId
    var isNoAdsBought: Boolean = false
        get() {
            return config.isNoAdsBought
        }
        set(isNoAdsBought) {
            noAdsLiveData.value = isNoAdsBought
            config.isNoAdsBought = isNoAdsBought
            field = isNoAdsBought
        }

    internal var canCommitFragments: Boolean = false
        private set

    fun onStartActivity() {
        canCommitFragments = true
    }

    fun onStopActivity() {
        canCommitFragments = false
    }

    fun getInterstitialAdId() = config.interstitialAdId
    fun getRewardedAdId() = config.rewardedAdId
    fun getBannerId() = config.bannerAdId
    fun isInDebugMode() = config.inDebugMode
    fun getTestDevices() = config.testDevices
    fun isSamsung() = Config.isSamsung
}