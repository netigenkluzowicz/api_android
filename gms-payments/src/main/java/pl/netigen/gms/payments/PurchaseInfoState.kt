package pl.netigen.gms.payments

import com.android.billingclient.api.Purchase

sealed class PurchaseInfoState(val purchases: List<Purchase> = emptyList()) {
    fun isNotRunning(): Boolean = this is NotStarted || this is Processed

    object NotStarted : PurchaseInfoState()
    object Started : PurchaseInfoState()
    class OneLoaded(purchases: List<Purchase>) : PurchaseInfoState(purchases)
    class BothLoaded(purchases: List<Purchase>) : PurchaseInfoState(purchases)
    class Processed(purchases: List<Purchase>) : PurchaseInfoState(purchases)
}
