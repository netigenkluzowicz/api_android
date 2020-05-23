---
title: INoAds - core-api
---

[core-api](../../index.html) / [pl.netigen.coreapi.payments](../index.html) / [INoAds](./index.html)

# INoAds

`interface INoAds`

Interface for no ads Payments, which turns on/off ads in whole application

### Properties

| [noAdsActive](no-ads-active.html) | Emits [Flow](#)&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt; indicating that no ads in-app purchase is or has changed to active or inactive`abstract val noAdsActive: Flow<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [noAdsInAppSkuList](no-ads-in-app-sku-list.html) | List of no ads sku available in application`abstract val noAdsInAppSkuList: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [packageName](package-name.html) | Application package name for default no ads sku`abstract val packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Functions

| [makeNoAdsPayment](make-no-ads-payment.html) | Makes call to current payment implementation to launch billing flow for an no ads in-app purchase. It will show the purchase screen to the user`abstract fun makeNoAdsPayment(activity: `[`Activity`](https://developer.android.com/reference/android/app/Activity.html)`, noAdsSku: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "${packageName}.noads"): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| [IPayments](../-i-payments/index.html) | Interface for payments, extends [INoAds](./index.html)`interface IPayments : `[`INoAds`](./index.html) |
| [ISplashVM](../../pl.netigen.coreapi.splash/-i-splash-v-m/index.html) | Its used for Splash [AndroidViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/AndroidViewModel.html), provides application initialization,:`interface ISplashVM : `[`INoAds`](./index.html)`, `[`IAppConfig`](../../pl.netigen.coreapi.main/-i-app-config/index.html) |
| [NoAdsNotAvailable](../-no-ads-not-available/index.html) | [INoAds](./index.html) implementation when we have no no-ads payment`object NoAdsNotAvailable : `[`INoAds`](./index.html) |

