package pl.netigen.gms.payments

import androidx.room.*
import com.android.billingclient.api.Purchase

@Entity(tableName = "purchase_table")
@TypeConverters(PurchaseTypeConverter::class)
data class CachedPurchase(val data: Purchase, val isNoAds: Boolean = false) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @Ignore
    val purchaseToken = data.purchaseToken

}

class PurchaseTypeConverter {
    @TypeConverter
    fun toString(purchase: Purchase): String = purchase.originalJson + '|' + purchase.signature

    @TypeConverter
    fun toPurchase(data: String): Purchase = data.split('|').let {
        Purchase(it[0], it[1])
    }
}