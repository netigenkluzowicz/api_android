---
title: pl.netigen.coreapi.main - core-api
---

[api_android](../index.md)(../index.md)/[core-api](../index.md) / [pl.netigen.coreapi.main](./index.md)

## Package pl.netigen.coreapi.main

### Types

| [CoreMainVM](-core-main-v-m/index.md) | Base class for [ICoreMainVM](-i-core-main-v-m/index.md) implementation, extends [AndroidViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/AndroidViewModel.html) because it must be available from any Fragment or Activity`abstract class CoreMainVM : `[`AndroidViewModel`](https://developer.android.com/reference/kotlin/androidx/lifecycle/AndroidViewModel.html)`, `[`ICoreMainVM`](-i-core-main-v-m/index.md) |
| [IAppConfig](-i-app-config/index.md) | Keeps configuration for entire Api/Application:`interface IAppConfig : `[`IAdsConfig`](../pl.netigen.coreapi.ads/-i-ads-config/index.md)`, `[`IGDPRConsentConfig`](../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent-config/index.md)`, `[`ISplashConfig`](../pl.netigen.coreapi.splash/-i-splash-config/index.md) |
| [ICoreMainActivity](-i-core-main-activity/index.md) | Base and by design should be only Activity in application:`interface ICoreMainActivity` |
| [ICoreMainVM](-i-core-main-v-m/index.md) | This implementations, provides access to api modules:`interface ICoreMainVM : `[`IPayments`](../pl.netigen.coreapi.payments/-i-payments/index.md)`, `[`IAds`](../pl.netigen.coreapi.ads/-i-ads/index.md)`, `[`INetworkStatus`](../pl.netigen.coreapi.network/-i-network-status/index.md)`, `[`IGDPRConsent`](../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.md)`, `[`IAppConfig`](-i-app-config/index.md) |
| [ICoreViewModelsFactory](-i-core-view-models-factory/index.md) | Interface used for provide [ViewModelProvider.Factory](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModelProvider/Factory.html) to instantiate [ICoreMainVM](-i-core-main-v-m/index.md) and [ISplashVM](../pl.netigen.coreapi.splash/-i-splash-v-m/index.md) implementations`interface ICoreViewModelsFactory : `[`Factory`](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModelProvider/Factory.html) |
| [Store](-store/index.md) | Possible target Stores for Application release`enum class Store` |

