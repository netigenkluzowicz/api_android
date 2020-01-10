package pl.netigen.coreapi.payments

import androidx.lifecycle.LiveData
import pl.netigen.coreapi.payments.model.NetigenSkuDetails

interface IPayments {
    val inAppSkuDetails: LiveData<List<NetigenSkuDetails>>
    val subsSkuDetails: LiveData<List<NetigenSkuDetails>>

    fun makePurchase()
    fun consumeItem()
}