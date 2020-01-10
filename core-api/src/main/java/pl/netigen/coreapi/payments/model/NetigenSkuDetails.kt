package pl.netigen.coreapi.payments.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NetigenSkuDetails(
    @PrimaryKey val sku: String,
    val type: String?,
    val isNoAds: Boolean,
    val price: String?,
    val title: String?,
    val description: String?,
    val originalJson: String?
)