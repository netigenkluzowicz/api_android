package pl.netigen.coreapi.payments

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.LiveData
import pl.netigen.coreapi.payments.model.NetigenSkuDetails

interface IPayments : INoAds {
    val inAppSkuDetails: LiveData<List<NetigenSkuDetails>>
    val subsSkuDetails: LiveData<List<NetigenSkuDetails>>
    val ownedPurchases: LiveData<List<String>>

    fun makePurchase(activity: Activity, skuId: String)
    fun endConnection()
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent)
}