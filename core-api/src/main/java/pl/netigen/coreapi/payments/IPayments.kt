package pl.netigen.coreapi.payments

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.LiveData
import pl.netigen.coreapi.payments.model.NetigenSkuDetails

/**
 * Interface for payments, extends [INoAds]
 *
 * Use this for:
 * - observe no-ads payments changes,
 * - observe owned payments changes (refreshed on application start),
 * - observe available payments with details ([NetigenSkuDetails]) fetched form store
 * - making calls for launch payment flow
 *
 */
interface IPayments : INoAds {
    /**
     * List of all payments sku available in Application
     * for default it is only one default no-ads sku: [packageName] +".noads"
     */
    val inAppSkuList: List<String>

    /**
     * [LiveData] with list of [NetigenSkuDetails], use this to get information of current available in app payments
     * 01
     *
     * warning this currently not working/tested in HMS!!
     */
    val inAppSkuDetailsLD: LiveData<List<NetigenSkuDetails>>

    /**
     * [LiveData] with list of [NetigenSkuDetails], use this to get information of current available subscriptions
     *
     * warning this currently not working/tested in HMS!!
     */
    val subsSkuDetailsLD: LiveData<List<NetigenSkuDetails>>

    /**
     * [LiveData] with list of payments owned by user (this payments [String] sku)
     *
     * This is updated with each start of application, and with user interaction with payments
     */
    val ownedPurchasesSkuLD: LiveData<List<String>>

    /**
     * Makes call to current payment implementation to launch billing flow for given [String] sku.
     *
     * It will show the purchase screen to the user
     *
     * When user successfully complete purchase, [ownedPurchasesSkuLD] live data will be updated with this purchase sku
     *
     * @param activity activity used to as context for launch
     * @param sku in-app purchase identifier (sku)
     */
    fun makePurchase(activity: Activity, sku: String)

    /**
     * Used only in HMS payments implementation, to handle billing events
     * Not need to be called manually
     *
     * @see Activity.onActivityResult
     */
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent)
}