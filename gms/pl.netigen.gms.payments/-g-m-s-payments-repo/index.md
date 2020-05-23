---
title: GMSPaymentsRepo - gms
---

[gms](../../index.html) / [pl.netigen.gms.payments](../index.html) / [GMSPaymentsRepo](./index.html)

# GMSPaymentsRepo

`class GMSPaymentsRepo : IPaymentsRepo, PurchasesUpdatedListener, BillingClientStateListener`

### Constructors

| [&lt;init&gt;](-init-.html) | `GMSPaymentsRepo(activity: `[`Activity`](https://developer.android.com/reference/android/app/Activity.html)`, inAppSkuList: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>, noAdsInAppSkuList: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>, isDebugMode: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, consumablesInAppSkuList: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`> = emptyList())` |

### Properties

| [inAppSkuDetailsLD](in-app-sku-details-l-d.html) | `val inAppSkuDetailsLD: `[`LiveData`](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<NetigenSkuDetails>>` |
| [lastPaymentEvent](last-payment-event.html) | `val lastPaymentEvent: SingleLiveEvent<PaymentEvent>` |
| [noAdsActive](no-ads-active.html) | `val noAdsActive: Flow<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [ownedPurchasesSkuLD](owned-purchases-sku-l-d.html) | `val ownedPurchasesSkuLD: `[`LiveData`](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>` |
| [subsSkuDetailsLD](subs-sku-details-l-d.html) | `val subsSkuDetailsLD: `[`LiveData`](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<NetigenSkuDetails>>` |

### Functions

| [endConnection](end-connection.html) | `fun endConnection(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [makePurchase](make-purchase.html) | `fun makePurchase(activity: `[`Activity`](https://developer.android.com/reference/android/app/Activity.html)`, skuId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onBillingServiceDisconnected](on-billing-service-disconnected.html) | `fun onBillingServiceDisconnected(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onBillingSetupFinished](on-billing-setup-finished.html) | `fun onBillingSetupFinished(billingResult: BillingResult): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onPurchasesUpdated](on-purchases-updated.html) | `fun onPurchasesUpdated(billingResult: BillingResult, purchases: `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<Purchase>?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

