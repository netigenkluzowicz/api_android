package pl.netigen.core.purchases

class NoAdsNoAvailable : INoAdsPurchases {
    override fun isNoAdsActive() = false
    override fun addNoAdsPurchaseListener(noAdsPurchaseListener: NoAdsPurchaseListener) = Unit
    override fun removeNoAdsPurchaseListener(noAdsPurchaseListener: NoAdsPurchaseListener) = Unit
}