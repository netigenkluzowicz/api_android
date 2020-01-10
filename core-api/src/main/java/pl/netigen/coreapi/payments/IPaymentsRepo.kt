package pl.netigen.coreapi.payments

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import pl.netigen.coreapi.payments.model.NetigenSkuDetails

abstract class IPaymentsRepo(private val application: Application) {

    abstract val noAdsActive: Flow<Boolean>

    abstract val inAppSkuDetails: LiveData<List<NetigenSkuDetails>>
    abstract val subsSkuDetails: LiveData<List<NetigenSkuDetails>>
}