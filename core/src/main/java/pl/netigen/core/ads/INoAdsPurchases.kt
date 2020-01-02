package pl.netigen.core.ads

interface INoAdsPurchases {
    fun isNoAdsActive(): Boolean
    fun addNoAdsPurchaseListener(noAdsPurchaseListener: NoAdsPurchaseListener)
    fun removeNoAdsPurchaseListener(noAdsPurchaseListener: NoAdsPurchaseListener)
}