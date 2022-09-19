package pl.netigen.coreapi.payments.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import pl.netigen.coreapi.payments.INoAds
import java.text.NumberFormat
import java.util.*

/**
 * Represents an in-app product's or subscription's  details.
 *
 * @property sku This product Id
 * @property type SKU type (consumable, non-consumable, subscription)
 * @property isNoAds Indicates if this product turns off ads in app
 *
 * see: [INoAds] and [INoAds.noAdsInAppSkuList]
 * @property price Formatted price of the item, including its currency sign.
 * @property priceAmountMicros Price in micro-units, where 1,000,000 micro-units equal one unit of the currency (if price is "€7.99", price_amount_micros is "7990000")
 * @property priceCurrencyCode ISO 4217 currency code for price and original price ( if price is specified in British pounds sterling, price_currency_code is "GBP")
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
    val priceAmountMicros: Long? = null,
    val priceCurrencyCode: String? = null,
    val title: String? = null,
    val description: String? = null,
    val originalJson: String? = null,
) {
    fun getDoublePrice(): Double? =
        try {
            StringBuilder(priceAmountMicros!!.toString()).insert(priceAmountMicros.toString().length - 6, ".").toString().toDouble()
        } catch (e: Exception) {
            null
        }

    fun replacePrice(priceToReplace: Double?): NetigenSkuDetails =
        copy(
            price = createPriceWithSymbol(
                price = price,
                priceToReplace = NumberFormat.getNumberInstance(Locale.getDefault()).format(priceToReplace),
            ),
        )

    private fun createPriceWithSymbol(price: String?, priceToReplace: String?): String? {
        var priceWithSymbol: String? = null
        if (price != null) {
            with(price) {
                if (first().isDigit()) {
                    priceWithSymbol = ""
                    priceWithSymbol += "$priceToReplace "
                    forEachIndexed { index, c ->
                        if (c.isDigit().not() && this[if (index < 0) 0 else index - 1].isDigit().not()) {
                            priceWithSymbol += c
                        }
                    }
                } else if (last().isDigit()) {
                    priceWithSymbol = ""
                    forEachIndexed { index, c ->
                        if (c.isDigit().not() && this[if (index > length) length else index + 1].isDigit().not()) {
                            priceWithSymbol += c
                        }
                    }
                    priceWithSymbol += " $priceToReplace"
                }
            }
        }
        return priceWithSymbol
    }

    companion object {
        const val EMPTY_PRICE = "--,--"
    }
}
