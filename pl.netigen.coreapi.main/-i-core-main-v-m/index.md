[core-api](../../index.md) / [pl.netigen.coreapi.main](../index.md) / [ICoreMainVM](./index.md)

# ICoreMainVM

`interface ICoreMainVM : `[`IPayments`](../../pl.netigen.coreapi.payments/-i-payments/index.md)`, `[`IAds`](../../pl.netigen.coreapi.ads/-i-ads/index.md)`, `[`INetworkStatus`](../../pl.netigen.coreapi.network/-i-network-status/index.md)`, `[`IGDPRConsent`](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.md)`, `[`IAppConfig`](../-i-app-config/index.md)

### Properties

| [currentIsNoAdsActive](current-is-no-ads-active.md) | `abstract val currentIsNoAdsActive: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [showGdprResetAds](show-gdpr-reset-ads.md) | `abstract val showGdprResetAds: `[`SingleLiveEvent`](../../pl.netigen.extensions/-single-live-event/index.md)`<`[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`>` |

### Functions

| [resetAdsPreferences](reset-ads-preferences.md) | `abstract fun resetAdsPreferences(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [start](start.md) | `abstract fun start(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| [CoreMainVM](../-core-main-v-m/index.md) | `abstract class CoreMainVM : `[`AndroidViewModel`](https://developer.android.com/reference/kotlin/androidx/lifecycle/AndroidViewModel.html)`, `[`ICoreMainVM`](./index.md) |

