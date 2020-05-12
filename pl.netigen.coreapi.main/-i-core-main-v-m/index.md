---
title: ICoreMainVM - core-api
---

[core-api](../../index.html) / [pl.netigen.coreapi.main](../index.html) / [ICoreMainVM](./index.html)

# ICoreMainVM

`interface ICoreMainVM : `[`IPayments`](../../pl.netigen.coreapi.payments/-i-payments/index.html)`, `[`IAds`](../../pl.netigen.coreapi.ads/-i-ads/index.html)`, `[`INetworkStatus`](../../pl.netigen.coreapi.network/-i-network-status/index.html)`, `[`IGDPRConsent`](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.html)`, `[`IAppConfig`](../-i-app-config/index.html)

### Properties

| [currentIsNoAdsActive](current-is-no-ads-active.html) | `abstract val currentIsNoAdsActive: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [showGdprResetAds](show-gdpr-reset-ads.html) | `abstract val showGdprResetAds: `[`SingleLiveEvent`](../../pl.netigen.extensions/-single-live-event/index.html)`<`[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`>` |

### Functions

| [resetAdsPreferences](reset-ads-preferences.html) | `abstract fun resetAdsPreferences(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [start](start.html) | `abstract fun start(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| [CoreMainVM](../-core-main-v-m/index.html) | `abstract class CoreMainVM : `[`AndroidViewModel`](https://developer.android.com/reference/kotlin/androidx/lifecycle/AndroidViewModel.html)`, `[`ICoreMainVM`](./index.html) |

