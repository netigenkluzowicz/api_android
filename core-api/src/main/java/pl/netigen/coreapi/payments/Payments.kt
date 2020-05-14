package pl.netigen.coreapi.payments

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import pl.netigen.coreapi.payments.model.PaymentEvent
import pl.netigen.extensions.SingleLiveEvent

/**
 * Base class for [IPayments] implementations
 *
 * Provides also access to payments repository and default sku values
 *
 * @property activity [Activity] using in current Payments implementation and for get [packageName]
 */
abstract class Payments(
    open val activity: Activity,
    override val packageName: String = activity.packageName,
    override val noAdsInAppSkuList: List<String> = listOf("${packageName}.noads"),
    override val inAppSkuList: List<String> = listOf("${packageName}.noads")
) : IPayments {

    /**
     * Current [IPaymentsRepo] implementation,
     * current design uses [androidx.room.Room] database + [Flow]/[LiveData] for observing purchases data
     */
    abstract val paymentsRepo: IPaymentsRepo

    override val ownedPurchasesSkuLD: LiveData<List<String>> by lazy { paymentsRepo.ownedPurchasesSkuLD }
    override val lastPaymentEvent: SingleLiveEvent<PaymentEvent> by lazy { paymentsRepo.lastPaymentEvent }
    override val noAdsActive by lazy { paymentsRepo.noAdsActive }
    override val inAppSkuDetailsLD by lazy { paymentsRepo.inAppSkuDetailsLD }
    override val subsSkuDetailsLD by lazy { paymentsRepo.subsSkuDetailsLD }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) = Unit
}
