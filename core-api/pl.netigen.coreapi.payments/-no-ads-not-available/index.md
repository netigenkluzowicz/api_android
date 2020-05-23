---
title: NoAdsNotAvailable - core-api
---

[api_android](../index.md)(../index.md)/[core-api](../../index.md) / [pl.netigen.coreapi.payments](../index.md) / [NoAdsNotAvailable](./index.md)

# NoAdsNotAvailable

`object NoAdsNotAvailable : `[`INoAds`](../-i-no-ads/index.md)

[INoAds](../-i-no-ads/index.md) implementation when we have no no-ads payment

### Properties

| [noAdsActive](no-ads-active.html) | Emits [Flow](#)&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)&gt; indicating that no ads in-app purchase is or has changed to active or inactive`val noAdsActive: Flow<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)`>` |
| [noAdsInAppSkuList](no-ads-in-app-sku-list.html) | List of no ads sku available in application`val noAdsInAppSkuList: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`>` |
| [packageName](package-name.html) | Application package name for default no ads sku`val packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) |

### Functions

| [makeNoAdsPayment](make-no-ads-payment.html) | Makes call to current payment implementation to launch billing flow for an no ads in-app purchase. It will show the purchase screen to the user`fun makeNoAdsPayment(activity: `[`Activity`](https://developer.android.com/reference/android/app/Activity.html)`, noAdsSku: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`): `[`Nothing`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-nothing/index.md) |

