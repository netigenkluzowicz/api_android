package pl.netigen.coreapi.main

import android.webkit.WebView
import android.widget.RelativeLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import pl.netigen.coreapi.ads.IBannerAd
import pl.netigen.coreapi.gdpr.ICoreSplashFragment
import pl.netigen.coreapi.payments.INoAds
import pl.netigen.coreapi.rateus.IRateUs
import pl.netigen.coreapi.survey.ISurvey

/**
 * Base and by design should be only Activity in application:
 * - observes when splash was showing/closing, and calls [onSplashOpened]/[onSplashClosed]
 * - observes when ads should be hided/showed and calls [showAds]/[hideAds] and [onNoAdsChanged]
 * - shows GDPR pop up on [ICoreMainVM.resetAdsPreferences] called
 */
interface ICoreMainActivity {

    /**
     * Indicates if we can safe perform Fragment transaction
     * as [commit()][FragmentTransaction.commit] or [popBackStack()][FragmentManager.popBackStack] and others
     * otherwise it will result with
     * [IllegalStateException][java.lang.IllegalStateException] crash
     *
     * see: [FragmentManager.isStateSaved]
     *
     * see: [stackoverflow](https://stackoverflow.com/a/44064149/3442734)
     */
    val canCommitFragments: Boolean

    /**
     * Returns if no ads in-app purchase is active or inactive, its current value of [INoAds.noAdsActive],
     * and should be used only when observing this value is hard to implement
     *
     */
    val noAdsActive: Boolean

    /**
     * Indicates if [CoreSplashFragment][ICoreSplashFragment] is visible to the user
     */
    val splashActive: Boolean

    /**
     * Factory for providing [ICoreMainVM] and [ISplashVM][pl.netigen.coreapi.splash.ISplashVM]
     */
    val viewModelFactory: ICoreViewModelsFactory

    val coreMainVM: ICoreMainVM
    val rateUs: IRateUs
    val survey: ISurvey

    /**
     * It's called when [CoreSplashFragment][ICoreSplashFragment] is showed
     */
    fun onSplashOpened()

    /**
     * It's called when [CoreSplashFragment][ICoreSplashFragment] is closed
     */
    fun onSplashClosed()

    /**
     * Shows GDPR pop up on [ICoreMainVM.resetAdsPreferences] called
     *
     */
    fun showGdprPopUp()

    /**
     * Called when [IBannerAd] and other ads related views should be hided
     *
     */
    fun hideAds()

    /**
     * Called when [IBannerAd] and other ads related views should be showed
     *
     */
    fun showAds()

    /**
     * Called when no-ads payment is active or not this as in [INoAds.noAdsActive]
     *
     * @param noAdsActive true when no-ads payment is active
     */
    fun onNoAdsChanged(noAdsActive: Boolean)

    /**
     * It's called when you should open Fragment with [WebView] for displaying Survey
     * @see [ISurvey.showSurvey]
     */
    fun openSurveyFragment()

    /**
     * Should be called in [onSplashOpened]
     * @return If Survey should be showed now
     */
    fun checkSurvey(): Boolean

    /**
     * Should be called in [onSplashOpened]
     * @return If RateUs should be showed now
     */
    fun checkRateUs(): Boolean

    /**
     * It should be called to count number of times user launches app
     *
     */
    fun increaseOpeningCounter()

    /**
     * Counts calls of [increaseOpeningCounter]
     */
    val openingCounter: Int


    companion object {
        const val UPDATE_REQUEST_CODE: Int = 77
        const val SHARED_PREFERENCES_NAME = " pl.netigen.rateus.RateUs"
        const val KEY_NUMBER_OF_OPENINGS = "KEY_NUMBER_OF_OPENINGS"
        const val KEY_LAST_LAUNCH_TIME_COUNTER = "KEY_LAST_LAUNCH_TIME"
        const val KEY_IS_RATE_US_OPEN = "KEY_IS_RATE_US_OPEN"
        const val NUMBER_OF_CHECKS_BEFORE_SHOWING_RATE_US = 4
        const val SPLASH_COUNTER_REFRESH_TIME_LIMIT_MS = 20 * 60 * 1000L //20 minutes
        const val SPLASH_COUNTER_REFRESH_TIME_LIMIT_MS_DEBUG = 20 * 1000L //20 sec
    }

    fun bannerView(): RelativeLayout
}
