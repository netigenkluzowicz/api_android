package pl.netigen.coreapi.payments

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.LiveData
import pl.netigen.coreapi.main.ICoreMainActivity
import pl.netigen.coreapi.payments.model.NetigenSkuDetails
import pl.netigen.coreapi.payments.model.PaymentEvent
import pl.netigen.extensions.SingleLiveEvent

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
     * List of all IN_APP payments sku available in Application
     * for default it is only one default no-ads sku: [packageName] +".noads"
     */
    val inAppSkuList: List<String>

    /**
     * List of all SUBSCRIPTION payments sku available in Application
     * for default it is empty
     */
    val subscriptionsSkuList: List<String>

    /**
     * [LiveData] with list of [NetigenSkuDetails], use this to get information of current available IN_APP and SUBSCRIPTION payments
     *
     * warning this currently not working/tested in HMS!!
     */
    val skuDetailsLD: LiveData<List<NetigenSkuDetails>>

    /**
     * [LiveData] with list of IN_APP and SUBSCRIPTION payments owned by user (this payments [String] sku)
     *
     * This is updated with each start of application, and with user interaction with payments
     */
    val ownedPurchasesSkuLD: LiveData<List<String>>

    /**
     * [SingleLiveEvent] indicating last payment event it can be success [PaymentEvent.PaymentSuccess()],
     * [PaymentEvent.PaymentRestored()] or [PaymentEvent.Error()]
     *
     * This should be used for show information about billing flow to the users
     *
     * warning this currently not working/tested in HMS!!
     *
     */
    val lastPaymentEvent: SingleLiveEvent<PaymentEvent>

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
     * It is called on [onStart()][Activity.onStart] of [ICoreMainActivity] implementation
     *
     */
    fun onActivityStart()

    fun isSaleTime(netigenSkuDetails: NetigenSkuDetails?, netigenInfoSkuDetails: NetigenSkuDetails?): Boolean

    fun purchaseSubscriptionOffer(activity: Activity, productDetails: Any, subscriptionOfferToken: String)

    val paymentsStateFlow: Any
}
