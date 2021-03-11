package pl.netigen.coreapi.payments

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import pl.netigen.coreapi.payments.model.NetigenSkuDetails
import pl.netigen.coreapi.payments.model.PaymentEvent
import pl.netigen.extensions.SingleLiveEvent

/**
 * Base class for [IPayments] implementations
 *
 * Provides also access to payments repository and default sku values
 *
 * @param activity [Activity] using in current Payments implementation and for get [packageName]
 */
abstract class Payments(activity: Activity) : IPayments, IPaymentsRepo {

    /**
     * Current [IPaymentsRepo] implementation,
     * current design uses [androidx.room.Room] database + [Flow]/[LiveData] for observing purchases data
     */
    abstract val paymentsRepo: IPaymentsRepo

    override val packageName: String by lazy { activity.packageName }
    override val ownedPurchasesSkuLD: LiveData<List<String>> by lazy { paymentsRepo.ownedPurchasesSkuLD }
    override val lastPaymentEvent: SingleLiveEvent<PaymentEvent> by lazy { paymentsRepo.lastPaymentEvent }
    override val noAdsActive by lazy { paymentsRepo.noAdsActive }
    override val skuDetailsLD by lazy { paymentsRepo.skuDetailsLD }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) = Unit
    override fun onActivityStart() = paymentsRepo.onActivityStart()

    fun isSaleTime(netigenSkuDetails: NetigenSkuDetails?, netigenInfoSkuDetails: NetigenSkuDetails?): Boolean {
        val infoPrice = netigenInfoSkuDetails?.priceAmountMicros
        val salePrice = netigenSkuDetails?.priceAmountMicros
        return if (infoPrice != null && salePrice != null) infoPrice > salePrice else false
    }
}
