package pl.netigen.coreapi.payments

import androidx.lifecycle.MutableLiveData
import pl.netigen.coreapi.payments.model.NetigenSkuDetails

abstract class Payments : INoAds, IPayments {
    override val noAdsActive by lazy { MutableLiveData<Boolean>() }
    override val inAppSkuDetails by lazy { MutableLiveData<NetigenSkuDetails>() }
    override val subsSkuDetails by lazy { MutableLiveData<NetigenSkuDetails>() }
}
