---
title: IAppConfig - core-api
---

[api_android](../index.md)/[core-api](../../index.md)/[pl.netigen.coreapi.main](../index.md)/[IAppConfig](./index.md)

# IAppConfig

`interface IAppConfig : `[`IAdsConfig`](../../pl.netigen.coreapi.ads/-i-ads-config/index.md)`, `[`IGDPRConsentConfig`](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent-config/index.md)`, `[`ISplashConfig`](../../pl.netigen.coreapi.splash/-i-splash-config/index.md)

Keeps configuration for entire Api/Application:

* Ads configuration [IAdsConfig](../../pl.netigen.coreapi.ads/-i-ads-config/index.md)
* UE GDPR consent configuration [IGDPRConsentConfig](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent-config/index.md)
* splash base configuration [ISplashConfig](../../pl.netigen.coreapi.splash/-i-splash-config/index.md)
* if no-ads payment is available in application
* if debug mode is enabled for ads and payments
* target [Store](../-store/index.md) for application release
* if use default ["Rate us"](../../pl.netigen.coreapi.rateus/-i-rate-us/index.md)

### Properties

| [inDebugMode](in-debug-mode.html) | Sets debug mode on/off, when:`abstract val inDebugMode: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |
| [isNoAdsAvailable](is-no-ads-available.html) | Set if there is or isn't no-ads payment in application`abstract val isNoAdsAvailable: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |
| [store](store.html) | Current release target Store, one of:`abstract val store: `[`Store`](../-store/index.md) |
| [useDefaultRateUs](use-default-rate-us.html) | Indicates If use default ["Rate us"](../../pl.netigen.coreapi.rateus/-i-rate-us/index.md)`abstract val useDefaultRateUs: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |

### Inheritors

| [ICoreMainVM](../-i-core-main-v-m/index.md) | This implementations, provides access to api modules:`interface ICoreMainVM : `[`IPayments`](../../pl.netigen.coreapi.payments/-i-payments/index.md)`, `[`IAds`](../../pl.netigen.coreapi.ads/-i-ads/index.md)`, `[`INetworkStatus`](../../pl.netigen.coreapi.network/-i-network-status/index.md)`, `[`IGDPRConsent`](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.md)`, `[`IAppConfig`](./index.md) |
| [ISplashVM](../../pl.netigen.coreapi.splash/-i-splash-v-m/index.md) | Its used for Splash [AndroidViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/AndroidViewModel.html), provides application initialization,:`interface ISplashVM : `[`INoAds`](../../pl.netigen.coreapi.payments/-i-no-ads/index.md)`, `[`IAppConfig`](./index.md) |

