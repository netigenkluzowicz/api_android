package pl.netigen.coreapi.payments

import android.content.Intent
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import pl.netigen.coreapi.payments.model.NetigenSkuDetails

interface IPaymentsRepo {
    val noAdsActive: Flow<Boolean>
    val inAppSkuDetails: LiveData<List<NetigenSkuDetails>>
    val subsSkuDetails: LiveData<List<NetigenSkuDetails>>
    fun endConnection()
}