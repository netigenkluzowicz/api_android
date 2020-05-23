---
title: CoreMainVmImpl - api_android
---

[api_android](../../index.html) / [pl.netigen.core.main](../index.html) / [CoreMainVmImpl](./index.html)

# CoreMainVmImpl

`class CoreMainVmImpl : `[`CoreMainVM`](../../pl.netigen.coreapi.main/-core-main-v-m/index.html)`, `[`IPayments`](../../pl.netigen.coreapi.payments/-i-payments/index.html)`, `[`IAds`](../../pl.netigen.coreapi.ads/-i-ads/index.html)`, `[`INetworkStatus`](../../pl.netigen.coreapi.network/-i-network-status/index.html)`, `[`IGDPRConsent`](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.html)`, `[`IAppConfig`](../../pl.netigen.coreapi.main/-i-app-config/index.html)

Current [ICoreMainVM](../../pl.netigen.coreapi.main/-i-core-main-v-m/index.html) implementation, provided implementations should be singletons

### Constructors

| [&lt;init&gt;](-init-.html) | Current [ICoreMainVM](../../pl.netigen.coreapi.main/-i-core-main-v-m/index.html) implementation, provided implementations should be singletons`CoreMainVmImpl(application: Application, ads: `[`IAds`](../../pl.netigen.coreapi.ads/-i-ads/index.html)`, payments: `[`IPayments`](../../pl.netigen.coreapi.payments/-i-payments/index.html)`, networkStatus: `[`INetworkStatus`](../../pl.netigen.coreapi.network/-i-network-status/index.html)`, gdprConsent: `[`IGDPRConsent`](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.html)`, appConfig: `[`IAppConfig`](../../pl.netigen.coreapi.main/-i-app-config/index.html)`)` |

### Properties

| [ads](ads.html) | [IAds](../../pl.netigen.coreapi.ads/-i-ads/index.html) implementation for activity`val ads: `[`IAds`](../../pl.netigen.coreapi.ads/-i-ads/index.html) |
| [currentIsNoAdsActive](current-is-no-ads-active.html) | Indicating that no ads in-app purchase is active or inactive`var currentIsNoAdsActive: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [networkStatus](network-status.html) | [INetworkStatus](../../pl.netigen.coreapi.network/-i-network-status/index.html) implementation for application`val networkStatus: `[`INetworkStatus`](../../pl.netigen.coreapi.network/-i-network-status/index.html) |
| [payments](payments.html) | [IPayments](../../pl.netigen.coreapi.payments/-i-payments/index.html) implementation for application`val payments: `[`IPayments`](../../pl.netigen.coreapi.payments/-i-payments/index.html) |
| [showGdprResetAds](show-gdpr-reset-ads.html) | [ICoreMainActivity](../../pl.netigen.coreapi.main/-i-core-main-activity/index.html) will observe it and show [GDPRDialogFragment](../../pl.netigen.core.gdpr/-g-d-p-r-dialog-fragment/index.html) when [resetAdsPreferences](../../pl.netigen.coreapi.main/-i-core-main-v-m/reset-ads-preferences.html) is called`val showGdprResetAds: `[`MutableSingleLiveEvent`](../../pl.netigen.extensions/-mutable-single-live-event/index.html)`<`[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`>` |

### Functions

| [onCleared](on-cleared.html) | `fun onCleared(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [resetAdsPreferences](reset-ads-preferences.html) | Shows [GDPRDialogFragment](../../pl.netigen.core.gdpr/-g-d-p-r-dialog-fragment/index.html) in [ICoreMainActivity](../../pl.netigen.coreapi.main/-i-core-main-activity/index.html) implementation to reset user ads Consent`fun resetAdsPreferences(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [start](start.html) | It is called on [onStart()](#) of [ICoreMainActivity](../../pl.netigen.coreapi.main/-i-core-main-activity/index.html) implementation`fun start(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

