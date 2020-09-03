package pl.netigen.hms.payments

import android.app.Activity
import android.content.Intent
import android.content.IntentSender.SendIntentException
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.huawei.hmf.tasks.OnSuccessListener
import com.huawei.hmf.tasks.Task
import com.huawei.hms.iap.Iap
import com.huawei.hms.iap.IapApiException
import com.huawei.hms.iap.IapClient
import com.huawei.hms.iap.entity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.json.JSONException
import pl.netigen.coreapi.payments.IPaymentsRepo
import pl.netigen.coreapi.payments.model.NetigenSkuDetails
import pl.netigen.coreapi.payments.model.PaymentEvent
import pl.netigen.extensions.MutableSingleLiveEvent
import pl.netigen.extensions.SingleLiveEvent
import timber.log.Timber
import timber.log.Timber.d


class HMSPaymentsRepo(
    private val activity: Activity,
    private val inAppSkuList: List<String>,
    private val noAdsInAppSkuList: List<String>,
    private val consumablesInAppSkuList: List<String> = emptyList(),
    private val consumeTestPurchase: Boolean = false
) : IPaymentsRepo {
    private val localCacheBillingClient by lazy { LocalBillingDb.getInstance(activity) }
    override val inAppSkuDetailsLD = MutableLiveData<List<NetigenSkuDetails>>()  // TODO: 14.05.2020
    override val subsSkuDetailsLD = MutableLiveData<List<NetigenSkuDetails>>() // TODO: 14.05.2020

    override fun onActivityStart() = Unit

    override val lastPaymentEvent: SingleLiveEvent<PaymentEvent> = MutableSingleLiveEvent() // TODO: 14.05.2020
    override val noAdsActive = localCacheBillingClient.purchaseDao().getPurchasesFlow()
        .map { list -> list.any { it.data.productId in noAdsInAppSkuList } }

    init {
        d("()")
        obtainOwnedPurchases()
    }


    override val ownedPurchasesSkuLD: LiveData<List<String>>
        get() = localCacheBillingClient.purchaseDao().getPurchasesFlow().asLiveData().map { list -> list.map { it.data.productId } }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == REQ_CODE_BUY) {
            d("requestCode = [$requestCode], resultCode = [$resultCode], data = [$data]")
            val purchaseResultInfo = Iap.getIapClient(activity).parsePurchaseResultInfoFromIntent(data)
            d("${purchaseResultInfo.returnCode}")
            try {
                when (purchaseResultInfo.returnCode) {
                    OrderStatusCode.ORDER_PRODUCT_OWNED, OrderStatusCode.ORDER_STATE_SUCCESS -> {
                        paymentSuccess(InAppPurchaseData(purchaseResultInfo.inAppPurchaseData))
                    }
                }
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    private fun paymentSuccess(inAppPurchaseData: InAppPurchaseData) {
        d("inAppPurchaseData = [$inAppPurchaseData]")
        CoroutineScope(Job() + Dispatchers.IO).launch {
            try {
                localCacheBillingClient.purchaseDao().insert(CachedPurchase(inAppPurchaseData))
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    private fun createPurchaseIntentReq(productId: String): PurchaseIntentReq? {
        val req = PurchaseIntentReq()
        req.productId = productId
        req.priceType = PRICE_TYPE
        req.developerPayload = "test"
        return req
    }


    fun makePurchase(activity: Activity, skuId: String) {
        d("activity = [$activity], skuId = [$skuId]")
        val mClient = Iap.getIapClient(activity)
        val task = mClient.createPurchaseIntent(createPurchaseIntentReq(skuId))
        task.addOnSuccessListener(OnSuccessListener { result ->
            d("createPurchaseIntent, onSuccess")
            if (result == null) {
                d("result is null")
                return@OnSuccessListener
            }
            val status = result.status
            if (status == null) {
                d("status is null")
                return@OnSuccessListener
            }
            // you should pull up the page to complete the payment process.
            if (status.hasResolution()) {
                try {
                    status.startResolutionForResult(activity, REQ_CODE_BUY)
                } catch (exp: SendIntentException) {
                    Timber.e(exp)
                }
            }
        }).addOnFailureListener { e ->
            Toast.makeText(activity, e.message, Toast.LENGTH_SHORT).show()
            if (e is IapApiException) {
                val returnCode = e.statusCode
                d("createPurchaseIntent, returnCode: $returnCode")
                // handle error scenarios
            }
        }
    }

    private fun obtainOwnedPurchases() {
        d("()")
        val ownedPurchasesReq = OwnedPurchasesReq()
        ownedPurchasesReq.priceType = PRICE_TYPE
        val task: Task<OwnedPurchasesResult> = Iap.getIapClient(activity).obtainOwnedPurchases(ownedPurchasesReq)
        task.addOnSuccessListener { result ->
            // Obtain the execution result.
            d("result = [$result]")
            if (result != null && result.inAppPurchaseDataList != null) {
                for (i in result.inAppPurchaseDataList.indices) {
                    val inAppPurchaseDataString = result.inAppPurchaseDataList[i]
                    d("inAppPurchaseDataString = [$inAppPurchaseDataString]")
                    try {
                        val inAppPurchaseData = InAppPurchaseData(inAppPurchaseDataString)
                        if (consumeTestPurchase) consume(inAppPurchaseData) else paymentSuccess(inAppPurchaseData)
                    } catch (e: JSONException) {
                        Timber.e(e)
                    }
                }
            }
        }.addOnFailureListener { e ->
            Timber.e(e)
            if (e is IapApiException) {
                val returnCode = e.statusCode
                d("returnCode $returnCode")
                if (returnCode == OrderStatusCode.ORDER_HWID_NOT_LOGIN) {
                    Toast.makeText(activity, "Please sign in to the app with a HUAWEI ID.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun consume(inAppPurchaseData: InAppPurchaseData) {
        d("inAppPurchaseData = [$inAppPurchaseData]")
        val mClient = Iap.getIapClient(activity)
        val req = ConsumeOwnedPurchaseReq()
        req.purchaseToken = inAppPurchaseData.purchaseToken
        val task = mClient.consumeOwnedPurchase(req)
        task.addOnSuccessListener {
            d("consumeOwnedPurchase success")
            CoroutineScope(Job() + Dispatchers.IO).launch {
                localCacheBillingClient.purchaseDao().deleteAll()
            }

        }.addOnFailureListener { e ->
            Timber.e(e)
            Toast.makeText(activity, e.message, Toast.LENGTH_SHORT).show()
            if (e is IapApiException) {
                val returnCode = e.statusCode
                d("consumeOwnedPurchase fail,returnCode: $returnCode")
            }
        }
    }


    companion object {
        const val REQ_CODE_BUY = 4002
        const val PRICE_TYPE = IapClient.PriceType.IN_APP_NONCONSUMABLE
    }
}