---
title: pl.netigen.coreapi.main - api_android
---

[api_android](../index.html) / [pl.netigen.coreapi.main](./index.html)

## Package pl.netigen.coreapi.main

### Types

| [CoreMainVM](-core-main-v-m/index.html) | Base class for [ICoreMainVM](-i-core-main-v-m/index.html) implementation, extends [AndroidViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/AndroidViewModel.html) because it must be available from any Fragment or Activity`abstract class CoreMainVM : `[`AndroidViewModel`](https://developer.android.com/reference/kotlin/androidx/lifecycle/AndroidViewModel.html)`, `[`ICoreMainVM`](-i-core-main-v-m/index.html) |
| [IAppConfig](-i-app-config/index.html) | Keeps configuration for entire Api/Application:`interface IAppConfig : `[`IAdsConfig`](../pl.netigen.coreapi.ads/-i-ads-config/index.html)`, `[`IGDPRConsentConfig`](../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent-config/index.html)`, `[`ISplashConfig`](../pl.netigen.coreapi.splash/-i-splash-config/index.html) |
| [ICoreMainActivity](-i-core-main-activity/index.html) | Base and by design should be only Activity in application:`interface ICoreMainActivity` |
| [ICoreMainVM](-i-core-main-v-m/index.html) | This implementations, provides access to api modules:`interface ICoreMainVM : `[`IPayments`](../pl.netigen.coreapi.payments/-i-payments/index.html)`, `[`IAds`](../pl.netigen.coreapi.ads/-i-ads/index.html)`, `[`INetworkStatus`](../pl.netigen.coreapi.network/-i-network-status/index.html)`, `[`IGDPRConsent`](../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.html)`, `[`IAppConfig`](-i-app-config/index.html) |
| [ICoreViewModelsFactory](-i-core-view-models-factory/index.html) | Interface used for provide [ViewModelProvider.Factory](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModelProvider/Factory.html) to instantiate [ICoreMainVM](-i-core-main-v-m/index.html) and [ISplashVM](../pl.netigen.coreapi.splash/-i-splash-v-m/index.html) implementations`interface ICoreViewModelsFactory : `[`Factory`](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModelProvider/Factory.html) |
| [Store](-store/index.html) | Possible target Stores for Application release`enum class Store` |

