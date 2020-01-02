package pl.netigen.core.purchases

interface INoAdsPurchases {
    fun isNoAdsActive(): Boolean
    fun addNoAdsPurchaseListener(noAdsPurchaseListener: NoAdsPurchaseListener)
    fun removeNoAdsPurchaseListener(noAdsPurchaseListener: NoAdsPurchaseListener)
}