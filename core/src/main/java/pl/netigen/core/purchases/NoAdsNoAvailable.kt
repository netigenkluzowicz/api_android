package pl.netigen.core.purchases

import pl.netigen.coreapi.purchases.INoAdsPurchases
import pl.netigen.coreapi.purchases.NoAdsPurchaseListener

class NoAdsNoAvailable : INoAdsPurchases {
    override fun isNoAdsActive() = false
    override fun addNoAdsPurchaseListener(noAdsPurchaseListener: NoAdsPurchaseListener) = Unit
    override fun removeNoAdsPurchaseListener(noAdsPurchaseListener: NoAdsPurchaseListener) = Unit
}