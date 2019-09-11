package pl.netigen.netigenapi

import android.content.Intent


internal interface ISplashActivity {

    /**
     * @return Intent(Activity) to launch after initialization
     */
    fun getIntentToLaunch(): Intent

    fun getConfigBuilder(): ConfigBuilder

    fun getPublisherIds() = arrayOf("pub-4699516034931013")

    fun getContentView(): Int

    fun getSplashFragmentRodoContainerId(): Int

    fun onNoAdsPaymentProcessingFinished(noAdsBought: Boolean)

    fun shouldShowInterstitialAd(): Boolean {
        return showFullscreen()
    }

    @Deprecated("", replaceWith = ReplaceWith("shouldShowInterstitialAd()"))
    fun showFullscreen(): Boolean {
        return true
    }
}
