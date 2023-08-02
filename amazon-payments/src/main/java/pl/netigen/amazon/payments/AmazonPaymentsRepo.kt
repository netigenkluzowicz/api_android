package pl.netigen.amazon.payments

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.amazon.device.iap.PurchasingListener
import com.amazon.device.iap.PurchasingService
import com.amazon.device.iap.model.ProductDataResponse
import com.amazon.device.iap.model.PurchaseResponse
import com.amazon.device.iap.model.PurchaseUpdatesResponse
import com.amazon.device.iap.model.UserDataResponse
import kotlinx.coroutines.flow.map
import pl.netigen.coreapi.payments.IPaymentsRepo
import pl.netigen.coreapi.payments.model.NetigenSkuDetails
import pl.netigen.coreapi.payments.model.PaymentEvent
import pl.netigen.extensions.MutableSingleLiveEvent
import pl.netigen.extensions.SingleLiveEvent
import timber.log.Timber.Forest.d

class AmazonPaymentsRepo(
    private val activity: Activity,
    private val inAppSkuList: List<String>,
    private val noAdsInAppSkuList: List<String>,
) : IPaymentsRepo, PurchasingListener {
    private val localCacheBillingClient by lazy { LocalBillingDb.getInstance(activity) }
    override val skuDetailsLD = MutableLiveData<List<NetigenSkuDetails>>() // TODO: 14.05.2020
    private var userIapData = UserIapData()

    override fun onActivityStart() {
        PurchasingService.registerListener(activity, this)
        PurchasingService.getPurchaseUpdates(false)
    }

    override val lastPaymentEvent: SingleLiveEvent<PaymentEvent> = MutableSingleLiveEvent() // TODO: 14.05.2020
    override val ownedPurchasesSkuLD: LiveData<List<String>>
        get() = localCacheBillingClient.purchaseDao().getPurchasesFlow().asLiveData().map { list -> list.map { it.data.productId } }


    override val noAdsActive = localCacheBillingClient.purchaseDao().getPurchasesFlow()
        .map { list -> list.any { it.data.productId in noAdsInAppSkuList } }


    override fun onUserDataResponse(userDataResponse: UserDataResponse?) {
        d("userDataResponse = [$userDataResponse]")

        val status: UserDataResponse.RequestStatus = userDataResponse?.requestStatus ?: return
        userIapData = when (status) {
            UserDataResponse.RequestStatus.SUCCESSFUL -> {
                UserIapData(userDataResponse.userData.userId, userDataResponse.userData.marketplace)
            }

            UserDataResponse.RequestStatus.FAILED, UserDataResponse.RequestStatus.NOT_SUPPORTED -> {
                UserIapData()
            }
        }
    }

    override fun onProductDataResponse(productDataResponse: ProductDataResponse?) {
        d("productDataResponse = [$productDataResponse]")
    }

    override fun onPurchaseResponse(purchaseResponse: PurchaseResponse?) {
        d("purchaseResponse = [$purchaseResponse]")
    }

    override fun onPurchaseUpdatesResponse(purchaseUpdatesResponse: PurchaseUpdatesResponse?) {
        d("purchaseUpdatesResponse = [$purchaseUpdatesResponse]")
    }

    fun makePurchase(activity: Activity, sku: String) {
        PurchasingService.purchase(sku)
    }
}
