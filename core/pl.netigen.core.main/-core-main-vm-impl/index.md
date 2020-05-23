---
title: CoreMainVmImpl - core
---

[api_android](../index.md)/[core](../../index.md)/[pl.netigen.core.main](../index.md)/[CoreMainVmImpl](./index.md)

# CoreMainVmImpl

`class CoreMainVmImpl : CoreMainVM, IPayments, IAds, INetworkStatus, IGDPRConsent, IAppConfig`

Current [ICoreMainVM](#) implementation, provided implementations should be singletons

### Constructors

| [&lt;init&gt;](-init-.html) | Current [ICoreMainVM](#) implementation, provided implementations should be singletons`CoreMainVmImpl(application: `[`Application`](https://developer.android.com/reference/android/app/Application.html)`, ads: IAds, payments: IPayments, networkStatus: INetworkStatus, gdprConsent: IGDPRConsent, appConfig: IAppConfig)` |

### Properties

| [ads](ads.html) | [IAds](#) implementation for activity`val ads: IAds` |
| [currentIsNoAdsActive](current-is-no-ads-active.html) | `var currentIsNoAdsActive: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |
| [networkStatus](network-status.html) | [INetworkStatus](#) implementation for application`val networkStatus: INetworkStatus` |
| [payments](payments.html) | [IPayments](#) implementation for application`val payments: IPayments` |
| [showGdprResetAds](show-gdpr-reset-ads.html) | `val showGdprResetAds: MutableSingleLiveEvent<`[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md)`>` |

### Functions

| [onCleared](on-cleared.html) | `fun onCleared(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [resetAdsPreferences](reset-ads-preferences.html) | `fun resetAdsPreferences(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [start](start.html) | `fun start(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |

