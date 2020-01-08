package pl.netigen.coreapi.payments

import androidx.lifecycle.LiveData
import pl.netigen.coreapi.payments.model.NetigenSkuDetails

interface IPayments {
    val inAppSkuDetails: LiveData<NetigenSkuDetails>
    val subsSkuDetails: LiveData<NetigenSkuDetails>

    fun makePurchase()
    fun consumeItem()
}