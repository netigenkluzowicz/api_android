package pl.netigen.coreapi.payments.model

import androidx.room.Entity
import androidx.room.PrimaryKey

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