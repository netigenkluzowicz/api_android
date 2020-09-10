package pl.netigen.coreapi.payments

import android.app.Activity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * [INoAds] implementation when we have no no-ads payment
 */
object NoAdsNotAvailable : INoAds {
    override val packageName: String = ""
    override val noAdsInAppSkuList: List<String> = emptyList()
    override val noAdsActive: Flow<Boolean> = flow { emit(false) }

    override fun makeNoAdsPayment(activity: Activity, noAdsSku: String) = throw IllegalStateException("NoAds payment it not available")
}