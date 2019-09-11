package pl.netigen.netigenapi

import android.content.Intent


internal interface ISplashActivity {

    /**
     * @return Intent(Activity) to launch after initialization
     */
    var intentToLaunch: Intent

    var configBuilder: ConfigBuilder

    val publisherIds: Array<String>
        get() = PUBLISHER_IDS

    var splashContentView: Int

    var splashFragmentRodoContainerId: Int

    fun onNoAdsPaymentProcessingFinished(noAdsBought: Boolean)

    fun shouldShowInterstitialAd(): Boolean {
        return showFullscreen()
    }

    @Deprecated("", replaceWith = ReplaceWith("shouldShowInterstitialAd()"))
    open fun showFullscreen(): Boolean {
        return true
    }

    companion object {
        val PUBLISHER_IDS = arrayOf("pub-4699516034931013")
    }
}
