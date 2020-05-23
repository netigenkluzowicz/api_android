---
title: AdMobInterstitial - gms
---

[gms](../../index.md) / [pl.netigen.gms.ads](../index.md) / [AdMobInterstitial](./index.md)

# AdMobInterstitial

`class AdMobInterstitial : IInterstitialAd, `[`LifecycleObserver`](https://netigenkluzowicz.github.io/api_android/core/androidx/lifecycle/LifecycleObserver.md))

[IInterstitialAd](#) implementation with [InterstitialAd](#) from Google Mobile Ads SDK

See: [Interstitial Ads](https://developers.google.com/admob/android/interstitial)

### Constructors

| [&lt;init&gt;](-init-.md)) | Initializes ad, starts observing activity [Lifecycle](https://netigenkluzowicz.github.io/api_android/core/androidx/lifecycle/Lifecycle.md))`AdMobInterstitial(activity: `[`ComponentActivity`](https://netigenkluzowicz.github.io/api_android/core/androidx/activity/ComponentActivity.md))`, adMobRequest: `[`IAdMobRequest`](../-i-ad-mob-request/index.md)`, adId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`, minDelayBetweenInterstitial: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.md)` = DEFAULT_DELAY_BETWEEN_INTERSTITIAL_ADS_MS, enabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)` = true)` |

### Properties

| [adId](ad-id.md)) | Current ad [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) identifier`val adId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) |
| [enabled](enabled.md)) | Current ad [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) identifier`var enabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |
| [isLoaded](is-loaded.md)) | `val isLoaded: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |

### Functions

| [load](load.md)) | `fun load(): Flow<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)`>` |
| [loadIfShouldBeLoaded](load-if-should-be-loaded.md)) | `fun loadIfShouldBeLoaded(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [showIfCanBeShowed](show-if-can-be-showed.md)) | `fun showIfCanBeShowed(forceShow: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)`, onClosedOrNotShowed: (`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |

