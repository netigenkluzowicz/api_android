package pl.netigen.gms.payments

import com.android.billingclient.api.ProductDetails

sealed class ProductDetailsInfoState(val productDetails: List<ProductDetails> = emptyList()) {
    object NotStarted : ProductDetailsInfoState()
    object Started : ProductDetailsInfoState()
    class OneLoaded(purchases: List<ProductDetails>) : ProductDetailsInfoState(purchases)
    class BothLoaded(purchases: List<ProductDetails>) : ProductDetailsInfoState(purchases)
}
