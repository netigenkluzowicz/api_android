package pl.netigen.coreapi.ads

import androidx.appcompat.app.AppCompatActivity

/**
 * Manages Interstitial Ad:
 * - loading
 * - showing to the user
 * - provides loading, showing and closing events
 * - is connected with activity lifecycle
 *
 * Interstitial ads are full-screen ads that cover the interface of their host app.
 * They're typically displayed at natural transition points in the flow of an app,
 * such as between activities or during the pause between levels in a game.
 * In this api default behaviour we displaying it also after showing splash to the users on application launch.
 * When an app shows an interstitial ad,
 * the user has the choice to either tap on the ad and continue to its destination or close it and return to the app.
 *
 */
interface IInterstitialAd : IAd, OnSplashAd {
    /**
     * Indicates if ad is loaded and ready to show
     */
    val isLoaded: Boolean

    /**
     * Indicates is activity alive or in background
     */
    var isInBackground: Boolean

    /**
     * Loads ad if it is not currently loading or already loaded
     *
     */
    fun loadIfShouldBeLoaded()

    fun onResume(activity: AppCompatActivity)

}
