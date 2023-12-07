package pl.netigen.coreapi.payments

import android.app.Activity
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import pl.netigen.coreapi.main.ICoreMainActivity
import pl.netigen.coreapi.payments.model.NetigenSkuDetails
import pl.netigen.coreapi.payments.model.PaymentEvent
import pl.netigen.extensions.SingleLiveEvent

/**
 * Interface for current payments repository implementation
 *
 * Current design uses [androidx.room.Room] database + [Flow]/[LiveData] for observing purchases data
 * @see IPayments
 */
interface IPaymentsRepo {
    /**
     * It is called on [onStart()][Activity.onStart] of [ICoreMainActivity] implementation
     *
     */
    fun onActivityStart()

    /**
     * [SingleLiveEvent] indicating last payment event it can be success [PaymentEvent.PaymentSuccess()],
     * [PaymentEvent.PaymentRestored()] or [PaymentEvent.Error()]
     *
     * This should be used for show information about billing flow to the users
     *
     */
    val lastPaymentEvent: SingleLiveEvent<PaymentEvent>

    /**
     * [LiveData] with list of payments owned by user (this payments [String] sku)
     *
     * @see IPayments.ownedPurchasesSkuLD
     */
    val ownedPurchasesSkuLD: LiveData<List<String>>

    /**
     * Emits [Flow]<[Boolean]> indicating that no ads in-app purchase is or has changed to active or inactive
     * Use this for behave with no ads related stuff, (e.g. hide/show no ads buy buttons)
     *
     * @see IPayments.noAdsActive
     */
    val noAdsActive: Flow<Boolean>


    val paymentsStateFlow: Any

    /**
     * [LiveData] with list of [NetigenSkuDetails], use this to get information of current available IN_APP and SUBSCRIPTION payments
     *
     * warning this currently not working/tested in HMS!!
     *
     * @see IPayments.skuDetailsLD
     */
    val skuDetailsLD: LiveData<List<NetigenSkuDetails>>
}
