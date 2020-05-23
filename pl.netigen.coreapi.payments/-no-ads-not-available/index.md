---
title: NoAdsNotAvailable - api_android
---

[api_android](../../index.html) / [pl.netigen.coreapi.payments](../index.html) / [NoAdsNotAvailable](./index.html)

# NoAdsNotAvailable

`object NoAdsNotAvailable : `[`INoAds`](../-i-no-ads/index.html)

[INoAds](../-i-no-ads/index.html) implementation when we have no no-ads payment

### Properties

| [noAdsActive](no-ads-active.html) | Emits [Flow](#)&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt; indicating that no ads in-app purchase is or has changed to active or inactive`val noAdsActive: Flow<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [noAdsInAppSkuList](no-ads-in-app-sku-list.html) | List of no ads sku available in application`val noAdsInAppSkuList: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [packageName](package-name.html) | Application package name for default no ads sku`val packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Functions

| [makeNoAdsPayment](make-no-ads-payment.html) | Makes call to current payment implementation to launch billing flow for an no ads in-app purchase. It will show the purchase screen to the user`fun makeNoAdsPayment(activity: Activity, noAdsSku: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Nothing`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-nothing/index.html) |

