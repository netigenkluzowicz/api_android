[core-api](../../index.md) / [pl.netigen.coreapi.payments](../index.md) / [NoAdsNotAvailable](./index.md)

# NoAdsNotAvailable

`object NoAdsNotAvailable : `[`INoAds`](../-i-no-ads/index.md)

[INoAds](../-i-no-ads/index.md) implementation when we have no no-ads payment

### Properties

| [noAdsActive](no-ads-active.md) | Emits [Flow](#)&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt; indicating that no ads in-app purchase is or has changed to active or inactive Use this for behave with no ads related stuff, (e.g. hide/show no ads buy buttons)`val noAdsActive: Flow<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [noAdsInAppSkuList](no-ads-in-app-sku-list.md) | List of no ads sku available in application`val noAdsInAppSkuList: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [packageName](package-name.md) | Application package name for default no ads sku`val packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Functions

| [makeNoAdsPayment](make-no-ads-payment.md) | Makes call to current payment implementation to launch billing flow for an no ads in-app purchase. It will show the purchase screen to the user`fun makeNoAdsPayment(activity: `[`Activity`](https://developer.android.com/reference/android/app/Activity.html)`, noAdsSku: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Nothing`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-nothing/index.html) |

