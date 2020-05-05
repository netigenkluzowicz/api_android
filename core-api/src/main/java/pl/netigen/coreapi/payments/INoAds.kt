package pl.netigen.coreapi.payments

import android.app.Activity
import kotlinx.coroutines.flow.Flow

/**
 * Simple interface for no ads Payments, which turns off ads in whole application
 *
 */
interface INoAds {
    /**
     * Emits [Flow]<[Boolean]> indicating that no ads in-app purchase is or has changed to active or inactive
     * Use this for behave with no ads related stuff, (e.g. hide/show no ads buy buttons)
     */
    val noAdsActive: Flow<Boolean>

    /**
     * Makes call to current payment implementation to launch billing flow for an no ads in-app purchase.
     * It will show the purchase screen to the user
     *
     * @param activity - activity used to as context for launch
     * @param noAdsString - optional parameter - no ads in-app purchase identifier, use only when it is more than one no ad purchase available
     */
    fun makeNoAdsPayment(activity: Activity, noAdsString: String = "${activity.packageName}.noads")
}