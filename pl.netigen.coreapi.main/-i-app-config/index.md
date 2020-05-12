---
title: IAppConfig - core-api
---

[core-api](../../index.html) / [pl.netigen.coreapi.main](../index.html) / [IAppConfig](./index.html)

# IAppConfig

`interface IAppConfig : `[`IAdsConfig`](../../pl.netigen.coreapi.ads/-i-ads-config/index.html)`, `[`IGDPRConsentConfig`](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent-config/index.html)`, `[`ISplashConfig`](../../pl.netigen.coreapi.splash/-i-splash-config/index.html)

### Properties

| [inDebugMode](in-debug-mode.html) | `abstract val inDebugMode: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isNoAdsAvailable](is-no-ads-available.html) | Set if there is or isn't no-ads payment in application`abstract val isNoAdsAvailable: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [store](store.html) | `abstract val store: `[`Store`](../-store/index.html) |

### Inheritors

| [ICoreMainVM](../-i-core-main-v-m/index.html) | `interface ICoreMainVM : `[`IPayments`](../../pl.netigen.coreapi.payments/-i-payments/index.html)`, `[`IAds`](../../pl.netigen.coreapi.ads/-i-ads/index.html)`, `[`INetworkStatus`](../../pl.netigen.coreapi.network/-i-network-status/index.html)`, `[`IGDPRConsent`](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.html)`, `[`IAppConfig`](./index.html) |
| [ISplashVM](../../pl.netigen.coreapi.splash/-i-splash-v-m/index.html) | `interface ISplashVM : `[`INoAds`](../../pl.netigen.coreapi.payments/-i-no-ads/index.html)`, `[`IAppConfig`](./index.html) |

