---
title: ICoreViewModelsFactory - core-api
---

[core-api](../../index.md) / [pl.netigen.coreapi.main](../index.md) / [ICoreViewModelsFactory](./index.md)

# ICoreViewModelsFactory

`interface ICoreViewModelsFactory : `[`Factory`](https://netigenkluzowicz.github.io/api_android/gms/androidx/lifecycle/ViewModelProvider/Factory.md))

Interface used for provide [ViewModelProvider.Factory](https://netigenkluzowicz.github.io/api_android/gms/androidx/lifecycle/ViewModelProvider/Factory.md)) to instantiate [ICoreMainVM](../-i-core-main-v-m/index.md) and [ISplashVM](../../pl.netigen.coreapi.splash/-i-splash-v-m/index.md) implementations

### Properties

| [ads](ads.md)) | Provides [IAds](../../pl.netigen.coreapi.ads/-i-ads/index.md) to Api view models`abstract val ads: `[`IAds`](../../pl.netigen.coreapi.ads/-i-ads/index.md) |
| [appConfig](app-config.md)) | Provides [IAppConfig](../-i-app-config/index.md) to Api view models`abstract val appConfig: `[`IAppConfig`](../-i-app-config/index.md) |
| [coreMainActivity](core-main-activity.md)) | Main api activity provides context for Api modules`abstract val coreMainActivity: `[`ICoreMainActivity`](../-i-core-main-activity/index.md) |
| [gdprConsent](gdpr-consent.md)) | Provides [IGDPRConsent](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.md) to Api view models`abstract val gdprConsent: `[`IGDPRConsent`](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.md) |
| [networkStatus](network-status.md)) | Provides [INetworkStatus](../../pl.netigen.coreapi.network/-i-network-status/index.md) to Api view models`abstract val networkStatus: `[`INetworkStatus`](../../pl.netigen.coreapi.network/-i-network-status/index.md) |
| [payments](payments.md)) | Provides [IPayments](../../pl.netigen.coreapi.payments/-i-payments/index.md) to Api view models`abstract val payments: `[`IPayments`](../../pl.netigen.coreapi.payments/-i-payments/index.md) |

