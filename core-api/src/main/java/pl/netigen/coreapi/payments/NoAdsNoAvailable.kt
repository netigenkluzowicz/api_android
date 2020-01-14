package pl.netigen.coreapi.payments

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NoAdsNoAvailable : INoAds {
    override val noAdsActive: Flow<Boolean> = flow { emit(false) }

    override fun makeNoAdsPayment() = throw IllegalStateException("NoAds payment it not available")
}