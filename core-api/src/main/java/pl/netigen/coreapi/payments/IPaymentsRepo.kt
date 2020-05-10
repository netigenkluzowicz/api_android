package pl.netigen.coreapi.payments

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import pl.netigen.coreapi.payments.model.NetigenSkuDetails

/**
 * Interface for current payments implementation repository
 * @see IPayments
 */
interface IPaymentsRepo {
    /**
     * [LiveData] with list of payments owned by user (this payments [String] sku), this values a equal to [NetigenSkuDetails.sku]
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

    /**
     * [LiveData] with list of [NetigenSkuDetails], use this to get information of current available in app payments
     * warning this currently not working in HMS!!
     *
     * @see IPayments.inAppSkuDetailsLD
     */
    val inAppSkuDetailsLD: LiveData<List<NetigenSkuDetails>>

    /**
     * [LiveData] with list of [NetigenSkuDetails], use this to get information of current available subscriptions
     * warning this currently not working in HMS!!
     *
     * @see IPayments.subsSkuDetailsLD
     */
    val subsSkuDetailsLD: LiveData<List<NetigenSkuDetails>>
}