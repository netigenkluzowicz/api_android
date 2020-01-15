package pl.netigen.coreapi.payments

import android.app.Activity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object NoAdsNotAvailable : INoAds {
    override val noAdsActive: Flow<Boolean> = flow { emit(false) }

    override fun makeNoAdsPayment(activity: Activity, noAdsString: String) = throw IllegalStateException("NoAds payment it not available")
}