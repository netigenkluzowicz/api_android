---
title: GMSPaymentsRepo - gms
---

[gms](../../index.md) / [pl.netigen.gms.payments](../index.md) / [GMSPaymentsRepo](./index.md)

# GMSPaymentsRepo

`class GMSPaymentsRepo : IPaymentsRepo, PurchasesUpdatedListener, BillingClientStateListener`

### Constructors

| [&lt;init&gt;](-init-.md)) | `GMSPaymentsRepo(activity: `[`Activity`](https://developer.android.com/reference/android/app/Activity.md))`, inAppSkuList: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`>, noAdsInAppSkuList: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`>, isDebugMode: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)` = false, consumablesInAppSkuList: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`> = emptyList())` |

### Properties

| [inAppSkuDetailsLD](in-app-sku-details-l-d.md)) | `val inAppSkuDetailsLD: `[`LiveData`](https://netigenkluzowicz.github.io/api_android/core/androidx/lifecycle/LiveData.md))`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.md)`<NetigenSkuDetails>>` |
| [lastPaymentEvent](last-payment-event.md)) | `val lastPaymentEvent: SingleLiveEvent<PaymentEvent>` |
| [noAdsActive](no-ads-active.md)) | `val noAdsActive: Flow<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)`>` |
| [ownedPurchasesSkuLD](owned-purchases-sku-l-d.md)) | `val ownedPurchasesSkuLD: `[`LiveData`](https://netigenkluzowicz.github.io/api_android/core/androidx/lifecycle/LiveData.md))`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`>>` |
| [subsSkuDetailsLD](subs-sku-details-l-d.md)) | `val subsSkuDetailsLD: `[`LiveData`](https://netigenkluzowicz.github.io/api_android/core/androidx/lifecycle/LiveData.md))`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.md)`<NetigenSkuDetails>>` |

### Functions

| [endConnection](end-connection.md)) | `fun endConnection(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [makePurchase](make-purchase.md)) | `fun makePurchase(activity: `[`Activity`](https://developer.android.com/reference/android/app/Activity.md))`, skuId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [onBillingServiceDisconnected](on-billing-service-disconnected.md)) | `fun onBillingServiceDisconnected(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [onBillingSetupFinished](on-billing-setup-finished.md)) | `fun onBillingSetupFinished(billingResult: BillingResult): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [onPurchasesUpdated](on-purchases-updated.md)) | `fun onPurchasesUpdated(billingResult: BillingResult, purchases: `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.md)`<Purchase>?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |

