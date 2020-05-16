package pl.netigen.coreapi.payments.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import pl.netigen.coreapi.payments.INoAds

/**
 * Represents an in-app product's or subscription's  details.
 *
 * @property sku This product Id
 * @property type SKU type (consumable, non-consumable, subscription)
 * @property isNoAds Indicates if this product turns off ads in app
 *
 * see: [INoAds] and [INoAds.noAdsInAppSkuList]
 * @property price Formatted price of the item, including its currency sign.
 * @property title Title of the product
 * @property description Description of the product
 * @property originalJson String in JSON format that contains Sku details
 */
@Entity
data class NetigenSkuDetails(
    @PrimaryKey val sku: String,
    val type: String? = null,
    val isNoAds: Boolean,
    val price: String? = null,
    val title: String? = null,
    val description: String? = null,
    val originalJson: String? = null
)