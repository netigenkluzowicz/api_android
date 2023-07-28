package pl.netigen.amazon.payments

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.huawei.hms.iap.entity.InAppPurchaseData
import pl.netigen.extensions.fromJson
import pl.netigen.extensions.toJson

@Entity(tableName = "purchase_table")
data class CachedPurchase(val data: InAppPurchaseData) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is CachedPurchase -> data == other.data
            else -> false
        }
    }

    override fun hashCode(): Int {
        return data.hashCode()
    }
}

class InAppPurchaseDataTypeConverter {
    @TypeConverter
    fun toString(inAppPurchaseData: InAppPurchaseData): String = inAppPurchaseData.toJson()

    @TypeConverter
    fun toPurchase(data: String): InAppPurchaseData = data.fromJson()
}
