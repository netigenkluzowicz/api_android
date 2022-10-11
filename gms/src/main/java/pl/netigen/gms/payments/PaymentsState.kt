package pl.netigen.gms.payments

data class PaymentsState(
    val productDetailsInfoState: ProductDetailsInfoState = ProductDetailsInfoState.NotStarted,
    val purchaseInfoState: PurchaseInfoState = PurchaseInfoState.NotStarted,
)
