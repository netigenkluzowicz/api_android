package pl.netigen.gms.payments

import androidx.lifecycle.LiveData
import androidx.room.*
import com.android.billingclient.api.ProductDetails
import pl.netigen.coreapi.payments.model.NetigenSkuDetails

@Dao
interface NetigenSkuDetailsDao {
    @Query("SELECT * FROM NetigenSkuDetails")
    fun skuDetailsLiveData(): LiveData<List<NetigenSkuDetails>>

    @Transaction
    fun insertOrUpdate(sku: String, isNoAds: Boolean = false) {
        val result = getById(sku)
        if (result != null) {
            update(sku, isNoAds)
        } else {
            insert(NetigenSkuDetails(sku, null, isNoAds, null, null, null, null))
        }
    }

    @Query("SELECT * FROM NetigenSkuDetails WHERE productId = :sku")
    fun getById(sku: String): NetigenSkuDetails?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(NetigenSkuDetails: NetigenSkuDetails)

    @Query("UPDATE NetigenSkuDetails SET isNoAds = :isNoAds WHERE productId = :sku")
    fun update(sku: String, isNoAds: Boolean)

    fun insertOrUpdate(productDetails: ProductDetails, isNoAds: Boolean) = productDetails.apply {
        val detail = toNetigenSkuDetails(this, isNoAds)
        insert(detail)
    }

    fun toNetigenSkuDetails(productDetails: ProductDetails, noAds: Boolean): NetigenSkuDetails {
        val originalJson = productDetails.toString().substring("SkuDetails: ".length)
        val price = productDetails.oneTimePurchaseOfferDetails?.formattedPrice
            ?: productDetails.subscriptionOfferDetails?.firstOrNull()?.pricingPhases?.pricingPhaseList?.firstOrNull()?.formattedPrice
        val priceAmountMicros = productDetails.oneTimePurchaseOfferDetails?.priceAmountMicros
            ?: productDetails.subscriptionOfferDetails?.firstOrNull()?.pricingPhases?.pricingPhaseList?.firstOrNull()?.priceAmountMicros
        val priceCurrencyCode = productDetails.oneTimePurchaseOfferDetails?.priceCurrencyCode
            ?: productDetails.subscriptionOfferDetails?.firstOrNull()?.pricingPhases?.pricingPhaseList?.firstOrNull()?.priceCurrencyCode

        return NetigenSkuDetails(
            productDetails.productId,
            productDetails.productType, noAds, price, priceAmountMicros, priceCurrencyCode,
            productDetails.title,
            productDetails.description, originalJson,
        )
    }
}
