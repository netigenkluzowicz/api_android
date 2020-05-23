---
title: ICoreMainVM - api_android
---

[api_android](../../index.html) / [pl.netigen.coreapi.main](../index.html) / [ICoreMainVM](./index.html)

# ICoreMainVM

`interface ICoreMainVM : `[`IPayments`](../../pl.netigen.coreapi.payments/-i-payments/index.html)`, `[`IAds`](../../pl.netigen.coreapi.ads/-i-ads/index.html)`, `[`INetworkStatus`](../../pl.netigen.coreapi.network/-i-network-status/index.html)`, `[`IGDPRConsent`](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.html)`, `[`IAppConfig`](../-i-app-config/index.html)

This implementations, provides access to api modules:

* payments [IPayments](../../pl.netigen.coreapi.payments/-i-payments/index.html)
* ads [IAds](../../pl.netigen.coreapi.ads/-i-ads/index.html)
* network status [INetworkStatus](../../pl.netigen.coreapi.network/-i-network-status/index.html)
* GDPR consent [IGDPRConsent](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.html)
* api configuration [IAppConfig](../-i-app-config/index.html)

### Properties

| [currentIsNoAdsActive](current-is-no-ads-active.html) | Indicating that no ads in-app purchase is active or inactive`abstract val currentIsNoAdsActive: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [showGdprResetAds](show-gdpr-reset-ads.html) | [ICoreMainActivity](../-i-core-main-activity/index.html) will observe it and show [GDPRDialogFragment](../../pl.netigen.core.gdpr/-g-d-p-r-dialog-fragment/index.html) when [resetAdsPreferences](reset-ads-preferences.html) is called`abstract val showGdprResetAds: `[`SingleLiveEvent`](../../pl.netigen.extensions/-single-live-event/index.html)`<`[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`>` |

### Functions

| [resetAdsPreferences](reset-ads-preferences.html) | Shows [GDPRDialogFragment](../../pl.netigen.core.gdpr/-g-d-p-r-dialog-fragment/index.html) in [ICoreMainActivity](../-i-core-main-activity/index.html) implementation to reset user ads Consent`abstract fun resetAdsPreferences(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [start](start.html) | It is called on [onStart()](#) of [ICoreMainActivity](../-i-core-main-activity/index.html) implementation`abstract fun start(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| [CoreMainVM](../-core-main-v-m/index.html) | Base class for [ICoreMainVM](./index.html) implementation, extends [AndroidViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/AndroidViewModel.html) because it must be available from any Fragment or Activity`abstract class CoreMainVM : `[`AndroidViewModel`](https://developer.android.com/reference/kotlin/androidx/lifecycle/AndroidViewModel.html)`, `[`ICoreMainVM`](./index.html) |

