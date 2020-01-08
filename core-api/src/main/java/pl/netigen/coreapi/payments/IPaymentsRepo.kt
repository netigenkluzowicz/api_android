package pl.netigen.coreapi.payments

import android.app.Application
import androidx.lifecycle.LiveData
import pl.netigen.coreapi.payments.model.NetigenSkuDetails

abstract class IPaymentsRepo(private val application: Application) {

    abstract val noAdsActive: LiveData<Boolean>

    abstract val inAppSkuDetails: LiveData<NetigenSkuDetails>
    abstract val subsSkuDetails: LiveData<NetigenSkuDetails>

}