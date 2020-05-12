[core-api](../../index.md) / [pl.netigen.coreapi.payments](../index.md) / [INoAds](./index.md)

# INoAds

`interface INoAds`

Interface for no ads Payments, which turns on/off ads in whole application

### Properties

| [noAdsActive](no-ads-active.md) | Emits [Flow](#)&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt; indicating that no ads in-app purchase is or has changed to active or inactive Use this for behave with no ads related stuff, (e.g. hide/show no ads buy buttons)`abstract val noAdsActive: Flow<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [noAdsInAppSkuList](no-ads-in-app-sku-list.md) | List of no ads sku available in application`abstract val noAdsInAppSkuList: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [packageName](package-name.md) | Application package name for default no ads sku`abstract val packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Functions

| [makeNoAdsPayment](make-no-ads-payment.md) | Makes call to current payment implementation to launch billing flow for an no ads in-app purchase. It will show the purchase screen to the user`abstract fun makeNoAdsPayment(activity: `[`Activity`](https://developer.android.com/reference/android/app/Activity.html)`, noAdsSku: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "${packageName}.noads"): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| [IPayments](../-i-payments/index.md) | Interface for payments, extends [INoAds](./index.md)`interface IPayments : `[`INoAds`](./index.md) |
| [ISplashVM](../../pl.netigen.coreapi.splash/-i-splash-v-m/index.md) | `interface ISplashVM : `[`INoAds`](./index.md)`, `[`IAppConfig`](../../pl.netigen.coreapi.main/-i-app-config/index.md) |
| [NoAdsNotAvailable](../-no-ads-not-available/index.md) | [INoAds](./index.md) implementation when we have no no-ads payment`object NoAdsNotAvailable : `[`INoAds`](./index.md) |

