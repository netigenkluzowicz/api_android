---
title: GMSPayments - gms
---

[gms](../../index.md) / [pl.netigen.gms.payments](../index.md) / [GMSPayments](./index.md)

# GMSPayments

`class GMSPayments : Payments`

### Constructors

| [&lt;init&gt;](-init-.md)) | `GMSPayments(activity: `[`Activity`](https://developer.android.com/reference/android/app/Activity.md))`, inAppSkuList: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`> = listOf("${activity.packageName}.noads"), noAdsInAppSkuList: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`> = listOf("${activity.packageName}.noads"), inDebugMode: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)` = false)` |

### Properties

| [inAppSkuList](in-app-sku-list.md)) | `val inAppSkuList: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`>` |
| [noAdsInAppSkuList](no-ads-in-app-sku-list.md)) | `val noAdsInAppSkuList: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`>` |
| [paymentsRepo](payments-repo.md)) | `val paymentsRepo: `[`GMSPaymentsRepo`](../-g-m-s-payments-repo/index.md) |

### Functions

| [makeNoAdsPayment](make-no-ads-payment.md)) | `fun makeNoAdsPayment(activity: `[`Activity`](https://developer.android.com/reference/android/app/Activity.md))`, noAdsSku: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [makePurchase](make-purchase.md)) | `fun makePurchase(activity: `[`Activity`](https://developer.android.com/reference/android/app/Activity.md))`, sku: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |

