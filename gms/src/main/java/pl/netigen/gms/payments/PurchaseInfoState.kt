package pl.netigen.gms.payments

import com.android.billingclient.api.Purchase

sealed class PurchaseInfoState(val purchases: List<Purchase> = emptyList()) {
    fun isNotRunning(): Boolean = this is NotStarted || this is Finished

    object NotStarted : PurchaseInfoState()
    object Started : PurchaseInfoState()
    class OneLoaded(purchases: List<Purchase>) : PurchaseInfoState(purchases)
    class BothLoaded(purchases: List<Purchase>) : PurchaseInfoState(purchases)
    object Finished : PurchaseInfoState()
}
