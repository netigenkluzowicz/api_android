---
title: CoreMainVmImpl - core
---

[core](../../index.md) / [pl.netigen.core.main](../index.md) / [CoreMainVmImpl](./index.md)

# CoreMainVmImpl

`class CoreMainVmImpl : CoreMainVM, IPayments, IAds, INetworkStatus, IGDPRConsent, IAppConfig`

Current [ICoreMainVM](#) implementation, provided implementations should be singletons

### Constructors

| [&lt;init&gt;](-init-.md)) | Current [ICoreMainVM](#) implementation, provided implementations should be singletons`CoreMainVmImpl(application: `[`Application`](https://developer.android.com/reference/android/app/Application.md))`, ads: IAds, payments: IPayments, networkStatus: INetworkStatus, gdprConsent: IGDPRConsent, appConfig: IAppConfig)` |

### Properties

| [ads](ads.md)) | [IAds](#) implementation for activity`val ads: IAds` |
| [currentIsNoAdsActive](current-is-no-ads-active.md)) | `var currentIsNoAdsActive: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |
| [networkStatus](network-status.md)) | [INetworkStatus](#) implementation for application`val networkStatus: INetworkStatus` |
| [payments](payments.md)) | [IPayments](#) implementation for application`val payments: IPayments` |
| [showGdprResetAds](show-gdpr-reset-ads.md)) | `val showGdprResetAds: MutableSingleLiveEvent<`[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md)`>` |

### Functions

| [onCleared](on-cleared.md)) | `fun onCleared(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [resetAdsPreferences](reset-ads-preferences.md)) | `fun resetAdsPreferences(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [start](start.md)) | `fun start(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |

