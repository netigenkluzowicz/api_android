package pl.netigen.coreapi.payments

import android.app.Activity
import androidx.lifecycle.LiveData
import pl.netigen.coreapi.payments.model.NetigenSkuDetails

interface IPayments : INoAds {
    val inAppSkuDetails: LiveData<List<NetigenSkuDetails>>
    val subsSkuDetails: LiveData<List<NetigenSkuDetails>>

    fun makePurchase(activity: Activity, netigenSkuDetails: NetigenSkuDetails)
    fun consumeItem()
    fun endConnection()
}