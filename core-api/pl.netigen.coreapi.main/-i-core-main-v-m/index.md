---
title: ICoreMainVM - core-api
---

[home page](../index.md)/[core-api](../../index.md) / [pl.netigen.coreapi.main](../index.md) / [ICoreMainVM](./index.md)

# ICoreMainVM

`interface ICoreMainVM : `[`IPayments`](../../pl.netigen.coreapi.payments/-i-payments/index.md)`, `[`IAds`](../../pl.netigen.coreapi.ads/-i-ads/index.md)`, `[`INetworkStatus`](../../pl.netigen.coreapi.network/-i-network-status/index.md)`, `[`IGDPRConsent`](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.md)`, `[`IAppConfig`](../-i-app-config/index.md)

This implementations, provides access to api modules:

* payments [IPayments](../../pl.netigen.coreapi.payments/-i-payments/index.md)
* ads [IAds](../../pl.netigen.coreapi.ads/-i-ads/index.md)
* network status [INetworkStatus](../../pl.netigen.coreapi.network/-i-network-status/index.md)
* GDPR consent [IGDPRConsent](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.md)
* api configuration [IAppConfig](../-i-app-config/index.md)

### Properties

| [currentIsNoAdsActive](current-is-no-ads-active.html) | Indicating that no ads in-app purchase is active or inactive`abstract val currentIsNoAdsActive: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |
| [showGdprResetAds](show-gdpr-reset-ads.html) | [ICoreMainActivity](../-i-core-main-activity/index.md) will observe it and show [GDPRDialogFragment](#) when [resetAdsPreferences](reset-ads-preferences.html) is called`abstract val showGdprResetAds: `[`SingleLiveEvent`](../../pl.netigen.extensions/-single-live-event/index.md)`<`[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md)`>` |

### Functions

| [resetAdsPreferences](reset-ads-preferences.html) | Shows [GDPRDialogFragment](#) in [ICoreMainActivity](../-i-core-main-activity/index.md) implementation to reset user ads Consent`abstract fun resetAdsPreferences(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [start](start.html) | It is called on [onStart()](https://developer.android.com/reference/android/app/Activity.html#onStart()) of [ICoreMainActivity](../-i-core-main-activity/index.md) implementation`abstract fun start(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |

### Inheritors

| [CoreMainVM](../-core-main-v-m/index.md) | Base class for [ICoreMainVM](./index.md) implementation, extends [AndroidViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/AndroidViewModel.html) because it must be available from any Fragment or Activity`abstract class CoreMainVM : `[`AndroidViewModel`](https://developer.android.com/reference/kotlin/androidx/lifecycle/AndroidViewModel.html)`, `[`ICoreMainVM`](./index.md) |

