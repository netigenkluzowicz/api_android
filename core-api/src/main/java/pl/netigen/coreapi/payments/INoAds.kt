package pl.netigen.coreapi.payments

import android.app.Activity
import kotlinx.coroutines.flow.Flow

/**
 * Interface for no ads Payments, which turns on/off ads in whole application
 *
 */
interface INoAds {
    /**
     * Application package name for default no ads sku
     */
    val packageName: String

    /**
     * List of no ads sku available in application
     *
     * For default it is one sku: [packageName] +".noads"
     */
    val noAdsInAppSkuList: List<String>

    /**
     * Emits [Flow]<[Boolean]> indicating that no ads in-app purchase is or has changed to active or inactive
     *
     * Use this for behave with no ads related stuff, (e.g. hide/show no ads buy buttons)
     */
    val noAdsActive: Flow<Boolean>

    /**
     * Makes call to current payment implementation to launch billing flow for an no ads in-app purchase.
     * It will show the purchase screen to the user
     *
     * @param activity activity used to as context for launch
     * @param noAdsSku optional parameter - no ads in-app purchase identifier (sku).
     * Use when it is multiple no-ads sku-s, because this takes current provided no ads sku from [noAdsInAppSkuList].
     *
     * For default it is [packageName] +".noads"
     */
    fun makeNoAdsPayment(activity: Activity, noAdsSku: String = "$packageName.noads")
}
