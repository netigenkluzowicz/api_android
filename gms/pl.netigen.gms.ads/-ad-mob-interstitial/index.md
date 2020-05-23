---
title: AdMobInterstitial - gms
---

[gms](../../index.md) / [pl.netigen.gms.ads](../index.md) / [AdMobInterstitial](./index.md)

# AdMobInterstitial

`class AdMobInterstitial : IInterstitialAd, `[`LifecycleObserver`](https://developer.android.com/reference/kotlin/androidx/lifecycle/LifecycleObserver.html)

[IInterstitialAd](#) implementation with [InterstitialAd](#) from Google Mobile Ads SDK

See: [Interstitial Ads](https://developers.google.com/admob/android/interstitial)

### Constructors

| [&lt;init&gt;](-init-.html) | Initializes ad, starts observing activity [Lifecycle](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.html)`AdMobInterstitial(activity: `[`ComponentActivity`](https://developer.android.com/reference/kotlin/androidx/activity/ComponentActivity.html)`, adMobRequest: `[`IAdMobRequest`](../-i-ad-mob-request/index.md)`, adId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`, minDelayBetweenInterstitial: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.md)` = DEFAULT_DELAY_BETWEEN_INTERSTITIAL_ADS_MS, enabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)` = true)` |

### Properties

| [adId](ad-id.html) | Current ad [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) identifier`val adId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) |
| [enabled](enabled.html) | Current ad [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) identifier`var enabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |
| [isLoaded](is-loaded.html) | `val isLoaded: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |

### Functions

| [load](load.html) | `fun load(): Flow<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)`>` |
| [loadIfShouldBeLoaded](load-if-should-be-loaded.html) | `fun loadIfShouldBeLoaded(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [showIfCanBeShowed](show-if-can-be-showed.html) | `fun showIfCanBeShowed(forceShow: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)`, onClosedOrNotShowed: (`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |

