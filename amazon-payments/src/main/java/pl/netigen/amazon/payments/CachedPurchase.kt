package pl.netigen.amazon.payments

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.amazon.device.iap.model.Receipt

@Entity(tableName = "purchase_table")
data class CachedPurchase(
    @PrimaryKey
    val receiptId: String,
    val userId: String,
    val sku: String,
) {
    companion object {
        fun from(receipt: Receipt, amazonUserData: AmazonUserData): CachedPurchase =
            CachedPurchase(receipt.receiptId, amazonUserData.userId, receipt.sku)
    }
}
