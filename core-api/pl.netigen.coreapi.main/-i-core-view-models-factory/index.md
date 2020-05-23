---
title: ICoreViewModelsFactory - core-api
---

[core-api](../../index.html) / [pl.netigen.coreapi.main](../index.html) / [ICoreViewModelsFactory](./index.html)

# ICoreViewModelsFactory

`interface ICoreViewModelsFactory : `[`Factory`](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModelProvider/Factory.html)

Interface used for provide [ViewModelProvider.Factory](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModelProvider/Factory.html) to instantiate [ICoreMainVM](../-i-core-main-v-m/index.html) and [ISplashVM](../../pl.netigen.coreapi.splash/-i-splash-v-m/index.html) implementations

### Properties

| [ads](ads.html) | Provides [IAds](../../pl.netigen.coreapi.ads/-i-ads/index.html) to Api view models`abstract val ads: `[`IAds`](../../pl.netigen.coreapi.ads/-i-ads/index.html) |
| [appConfig](app-config.html) | Provides [IAppConfig](../-i-app-config/index.html) to Api view models`abstract val appConfig: `[`IAppConfig`](../-i-app-config/index.html) |
| [coreMainActivity](core-main-activity.html) | Main api activity provides context for Api modules`abstract val coreMainActivity: `[`ICoreMainActivity`](../-i-core-main-activity/index.html) |
| [gdprConsent](gdpr-consent.html) | Provides [IGDPRConsent](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.html) to Api view models`abstract val gdprConsent: `[`IGDPRConsent`](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.html) |
| [networkStatus](network-status.html) | Provides [INetworkStatus](../../pl.netigen.coreapi.network/-i-network-status/index.html) to Api view models`abstract val networkStatus: `[`INetworkStatus`](../../pl.netigen.coreapi.network/-i-network-status/index.html) |
| [payments](payments.html) | Provides [IPayments](../../pl.netigen.coreapi.payments/-i-payments/index.html) to Api view models`abstract val payments: `[`IPayments`](../../pl.netigen.coreapi.payments/-i-payments/index.html) |

