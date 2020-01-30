package pl.netigen.hms.payments

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.huawei.hms.iap.entity.PurchaseResultInfo
import pl.netigen.extensions.fromJson
import pl.netigen.extensions.toJson

@Entity(tableName = "purchase_table")
data class CachedPurchase(val data: PurchaseResultInfo) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is CachedPurchase -> data == other.data
            is PurchaseResultInfo -> data == other
            else -> false
        }
    }

    override fun hashCode(): Int {
        return data.hashCode()
    }
}

class PurchaseTypeConverter {
    @TypeConverter
    fun toString(purchase: PurchaseResultInfo): String = purchase.toJson()

    @TypeConverter
    fun toPurchase(data: String): PurchaseResultInfo = data.fromJson()
}