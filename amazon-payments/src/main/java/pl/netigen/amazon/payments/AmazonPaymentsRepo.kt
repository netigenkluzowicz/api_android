package pl.netigen.amazon.payments

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.amazon.device.iap.PurchasingListener
import com.amazon.device.iap.PurchasingService
import com.amazon.device.iap.model.FulfillmentResult
import com.amazon.device.iap.model.ProductDataResponse
import com.amazon.device.iap.model.ProductType
import com.amazon.device.iap.model.PurchaseResponse
import com.amazon.device.iap.model.PurchaseResponse.RequestStatus.ALREADY_PURCHASED
import com.amazon.device.iap.model.PurchaseResponse.RequestStatus.SUCCESSFUL
import com.amazon.device.iap.model.PurchaseUpdatesResponse
import com.amazon.device.iap.model.Receipt
import com.amazon.device.iap.model.UserDataResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import pl.netigen.coreapi.payments.IPaymentsRepo
import pl.netigen.coreapi.payments.model.NetigenSkuDetails
import pl.netigen.coreapi.payments.model.PaymentEvent
import pl.netigen.extensions.MutableSingleLiveEvent
import pl.netigen.extensions.SingleLiveEvent
import timber.log.Timber
import timber.log.Timber.Forest.d

class AmazonPaymentsRepo(
    private val activity: Activity,
    private val inAppSkuList: List<String>,
    private val noAdsInAppSkuList: List<String>,
) : IPaymentsRepo, PurchasingListener {
    private val localCacheBillingClient by lazy { LocalBillingDb.getInstance(activity) }
    override val skuDetailsLD = MutableLiveData<List<NetigenSkuDetails>>()
    private var amazonUserData = AmazonUserData()

    override fun onActivityStart() {
        PurchasingService.registerListener(activity, this)
        PurchasingService.getPurchaseUpdates(false)
    }

    override val lastPaymentEvent: SingleLiveEvent<PaymentEvent> = MutableSingleLiveEvent()
    override val ownedPurchasesSkuLD: LiveData<List<String>>
        get() = localCacheBillingClient.purchaseDao().getPurchasesFlow().asLiveData().map { list -> list.map { it.sku } }


    override val noAdsActive = localCacheBillingClient.purchaseDao().getPurchasesFlow()
        .map { list -> list.any { it.sku in noAdsInAppSkuList } }
    override val paymentsStateFlow: Any
        get() = Unit


    override fun onUserDataResponse(response: UserDataResponse?) {
        d("userDataResponse = [$response]")

        val status: UserDataResponse.RequestStatus = response?.requestStatus ?: return
        amazonUserData = when (status) {
            UserDataResponse.RequestStatus.SUCCESSFUL -> {
                AmazonUserData(response.userData.userId, response.userData.marketplace)
            }

            UserDataResponse.RequestStatus.FAILED, UserDataResponse.RequestStatus.NOT_SUPPORTED -> {
                Timber.e("userDataResponse $status")
                AmazonUserData()
            }
        }
    }

    override fun onProductDataResponse(response: ProductDataResponse?) {
        d("productDataResponse = [$response]")

    }

    override fun onPurchaseResponse(response: PurchaseResponse?) {
        d("purchaseResponse = [$response]")
        val status: PurchaseResponse.RequestStatus = response?.requestStatus ?: return
        when (status) {
            SUCCESSFUL, ALREADY_PURCHASED -> {
                amazonUserData = AmazonUserData(response.userData.userId, response.userData.marketplace)
                handleReceipt(response.receipt)
            }

            else -> Timber.e("userDataResponse $status")
        }
    }

    override fun onPurchaseUpdatesResponse(response: PurchaseUpdatesResponse?) {
        d("purchaseUpdatesResponse = [$response]")
        val status: PurchaseUpdatesResponse.RequestStatus = response?.requestStatus ?: return
        when (status) {
            PurchaseUpdatesResponse.RequestStatus.SUCCESSFUL -> {
                for (receipt in response.receipts.filterNotNull()) {
                    amazonUserData = AmazonUserData(response.userData.userId, response.userData.marketplace)
                    handleReceipt(receipt)
                }
                if (response.hasMore()) {
                    PurchasingService.getPurchaseUpdates(false)
                }
            }

            PurchaseUpdatesResponse.RequestStatus.FAILED, PurchaseUpdatesResponse.RequestStatus.NOT_SUPPORTED -> {
                Timber.e("onPurchaseUpdatesResponse $status")
            }
        }

    }

    private fun handleReceipt(receipt: Receipt) {
        if (receipt.isCanceled) {
            localCacheBillingClient.purchaseDao().delete(receipt.sku)
        } else when (receipt.productType) {
            ProductType.ENTITLED -> handleIAP(receipt, amazonUserData)
            ProductType.SUBSCRIPTION -> handleSub(receipt, amazonUserData)
            else -> Unit
        }
    }


    private fun handleIAP(receipt: Receipt, amazonUserData: AmazonUserData) {
        CoroutineScope(Job() + Dispatchers.IO).launch {
            localCacheBillingClient.purchaseDao().insert(CachedPurchase.from(receipt, amazonUserData))
        }
        PurchasingService.notifyFulfillment(receipt.receiptId, FulfillmentResult.FULFILLED)
    }

    private fun handleSub(receipt: Receipt, amazonUserData: AmazonUserData) {

        CoroutineScope(Job() + Dispatchers.IO).launch {
            localCacheBillingClient.purchaseDao().insert(CachedPurchase.from(receipt, amazonUserData))
        }
        PurchasingService.notifyFulfillment(receipt.receiptId, FulfillmentResult.FULFILLED)

    }

    fun makePurchase(sku: String) {
        PurchasingService.purchase(sku)
    }
}
