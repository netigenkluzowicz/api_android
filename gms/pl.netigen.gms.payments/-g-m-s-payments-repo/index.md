---
title: GMSPaymentsRepo - gms
---

[gms](../../index.md) / [pl.netigen.gms.payments](../index.md) / [GMSPaymentsRepo](./index.md)

# GMSPaymentsRepo

`class GMSPaymentsRepo : IPaymentsRepo, PurchasesUpdatedListener, BillingClientStateListener`

### Constructors

| [&lt;init&gt;](-init-.html) | `GMSPaymentsRepo(activity: `[`Activity`](https://developer.android.com/reference/android/app/Activity.html)`, inAppSkuList: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`>, noAdsInAppSkuList: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`>, isDebugMode: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)` = false, consumablesInAppSkuList: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`> = emptyList())` |

### Properties

| [inAppSkuDetailsLD](in-app-sku-details-l-d.html) | `val inAppSkuDetailsLD: `[`LiveData`](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.md)`<NetigenSkuDetails>>` |
| [lastPaymentEvent](last-payment-event.html) | `val lastPaymentEvent: SingleLiveEvent<PaymentEvent>` |
| [noAdsActive](no-ads-active.html) | `val noAdsActive: Flow<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)`>` |
| [ownedPurchasesSkuLD](owned-purchases-sku-l-d.html) | `val ownedPurchasesSkuLD: `[`LiveData`](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`>>` |
| [subsSkuDetailsLD](subs-sku-details-l-d.html) | `val subsSkuDetailsLD: `[`LiveData`](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.md)`<NetigenSkuDetails>>` |

### Functions

| [endConnection](end-connection.html) | `fun endConnection(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [makePurchase](make-purchase.html) | `fun makePurchase(activity: `[`Activity`](https://developer.android.com/reference/android/app/Activity.html)`, skuId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [onBillingServiceDisconnected](on-billing-service-disconnected.html) | `fun onBillingServiceDisconnected(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [onBillingSetupFinished](on-billing-setup-finished.html) | `fun onBillingSetupFinished(billingResult: BillingResult): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [onPurchasesUpdated](on-purchases-updated.html) | `fun onPurchasesUpdated(billingResult: BillingResult, purchases: `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.md)`<Purchase>?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |

