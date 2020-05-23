---
title: GMSPayments - api_android
---

[api_android](../../index.html) / [pl.netigen.gms.payments](../index.html) / [GMSPayments](./index.html)

# GMSPayments

`class GMSPayments : `[`Payments`](../../pl.netigen.coreapi.payments/-payments/index.html)

### Constructors

| [&lt;init&gt;](-init-.html) | `GMSPayments(activity: Activity, inAppSkuList: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`> = listOf("${activity.packageName}.noads"), noAdsInAppSkuList: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`> = listOf("${activity.packageName}.noads"), inDebugMode: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false)` |

### Properties

| [inAppSkuList](in-app-sku-list.html) | List of all payments sku available in Application for default it is only one default no-ads sku: [packageName](../../pl.netigen.coreapi.payments/-i-no-ads/package-name.html) +".noads"`val inAppSkuList: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [noAdsInAppSkuList](no-ads-in-app-sku-list.html) | List of no ads sku available in application`val noAdsInAppSkuList: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [paymentsRepo](payments-repo.html) | Current [IPaymentsRepo](../../pl.netigen.coreapi.payments/-i-payments-repo/index.html) implementation, current design uses [androidx.room.Room](https://developer.android.com/reference/kotlin/androidx/room/Room.html) database + [Flow](#)/[LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html) for observing purchases data`val paymentsRepo: `[`GMSPaymentsRepo`](../-g-m-s-payments-repo/index.html) |

### Functions

| [makeNoAdsPayment](make-no-ads-payment.html) | Makes call to current payment implementation to launch billing flow for an no ads in-app purchase. It will show the purchase screen to the user`fun makeNoAdsPayment(activity: Activity, noAdsSku: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [makePurchase](make-purchase.html) | Makes call to current payment implementation to launch billing flow for given [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) sku.`fun makePurchase(activity: Activity, sku: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

