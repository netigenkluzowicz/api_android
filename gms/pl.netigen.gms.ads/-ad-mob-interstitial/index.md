---
title: AdMobInterstitial - gms
---

[gms](../../index.html) / [pl.netigen.gms.ads](../index.html) / [AdMobInterstitial](./index.html)

# AdMobInterstitial

`class AdMobInterstitial : IInterstitialAd, `[`LifecycleObserver`](https://developer.android.com/reference/kotlin/androidx/lifecycle/LifecycleObserver.html)

[IInterstitialAd](#) implementation with [InterstitialAd](#) from Google Mobile Ads SDK

See: [Interstitial Ads](https://developers.google.com/admob/android/interstitial)

### Constructors

| [&lt;init&gt;](-init-.html) | Initializes ad, starts observing activity [Lifecycle](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.html)`AdMobInterstitial(activity: `[`ComponentActivity`](https://developer.android.com/reference/kotlin/androidx/activity/ComponentActivity.html)`, adMobRequest: `[`IAdMobRequest`](../-i-ad-mob-request/index.html)`, adId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, minDelayBetweenInterstitial: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = DEFAULT_DELAY_BETWEEN_INTERSTITIAL_ADS_MS, enabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true)` |

### Properties

| [adId](ad-id.html) | Current ad [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) identifier`val adId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [enabled](enabled.html) | Current ad [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) identifier`var enabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isLoaded](is-loaded.html) | `val isLoaded: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

### Functions

| [load](load.html) | `fun load(): Flow<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [loadIfShouldBeLoaded](load-if-should-be-loaded.html) | `fun loadIfShouldBeLoaded(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [showIfCanBeShowed](show-if-can-be-showed.html) | `fun showIfCanBeShowed(forceShow: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, onClosedOrNotShowed: (`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

