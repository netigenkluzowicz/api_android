---
title: IAppConfig - api_android
---

[api_android](../../index.html) / [pl.netigen.coreapi.main](../index.html) / [IAppConfig](./index.html)

# IAppConfig

`interface IAppConfig : `[`IAdsConfig`](../../pl.netigen.coreapi.ads/-i-ads-config/index.html)`, `[`IGDPRConsentConfig`](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent-config/index.html)`, `[`ISplashConfig`](../../pl.netigen.coreapi.splash/-i-splash-config/index.html)

Keeps configuration for entire Api/Application:

* Ads configuration [IAdsConfig](../../pl.netigen.coreapi.ads/-i-ads-config/index.html)
* UE GDPR consent configuration [IGDPRConsentConfig](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent-config/index.html)
* splash base configuration [ISplashConfig](../../pl.netigen.coreapi.splash/-i-splash-config/index.html)
* if no-ads payment is available in application
* if debug mode is enabled for ads and payments
* target [Store](../-store/index.html) for application release
* if use default ["Rate us"](../../pl.netigen.coreapi.rateus/-i-rate-us/index.html)

### Properties

| [inDebugMode](in-debug-mode.html) | Sets debug mode on/off, when:`abstract val inDebugMode: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isNoAdsAvailable](is-no-ads-available.html) | Set if there is or isn't no-ads payment in application`abstract val isNoAdsAvailable: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [store](store.html) | Current release target Store, one of:`abstract val store: `[`Store`](../-store/index.html) |
| [useDefaultRateUs](use-default-rate-us.html) | Indicates If use default ["Rate us"](../../pl.netigen.coreapi.rateus/-i-rate-us/index.html)`abstract val useDefaultRateUs: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

### Inheritors

| [AppConfig](../../pl.netigen.core.config/-app-config/index.html) | [IAppConfig](./index.html) implementation which provides also [marketLinkPrefix](../../pl.netigen.core.config/-app-config/market-link-prefix.html) for linking Application to current [Store](../-store/index.html)`open class AppConfig : `[`IAppConfig`](./index.html) |
| [CoreMainVmImpl](../../pl.netigen.core.main/-core-main-vm-impl/index.html) | Current [ICoreMainVM](../-i-core-main-v-m/index.html) implementation, provided implementations should be singletons`class CoreMainVmImpl : `[`CoreMainVM`](../-core-main-v-m/index.html)`, `[`IPayments`](../../pl.netigen.coreapi.payments/-i-payments/index.html)`, `[`IAds`](../../pl.netigen.coreapi.ads/-i-ads/index.html)`, `[`INetworkStatus`](../../pl.netigen.coreapi.network/-i-network-status/index.html)`, `[`IGDPRConsent`](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.html)`, `[`IAppConfig`](./index.html) |
| [CoreSplashVMImpl](../../pl.netigen.core.splash/-core-splash-v-m-impl/index.html) | [SplashVM](../../pl.netigen.coreapi.splash/-splash-v-m/index.html) implementation`class CoreSplashVMImpl : `[`SplashVM`](../../pl.netigen.coreapi.splash/-splash-v-m/index.html)`, `[`INoAds`](../../pl.netigen.coreapi.payments/-i-no-ads/index.html)`, `[`IAppConfig`](./index.html) |
| [ICoreMainVM](../-i-core-main-v-m/index.html) | This implementations, provides access to api modules:`interface ICoreMainVM : `[`IPayments`](../../pl.netigen.coreapi.payments/-i-payments/index.html)`, `[`IAds`](../../pl.netigen.coreapi.ads/-i-ads/index.html)`, `[`INetworkStatus`](../../pl.netigen.coreapi.network/-i-network-status/index.html)`, `[`IGDPRConsent`](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.html)`, `[`IAppConfig`](./index.html) |
| [ISplashVM](../../pl.netigen.coreapi.splash/-i-splash-v-m/index.html) | Its used for Splash [AndroidViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/AndroidViewModel.html), provides application initialization,:`interface ISplashVM : `[`INoAds`](../../pl.netigen.coreapi.payments/-i-no-ads/index.html)`, `[`IAppConfig`](./index.html) |

