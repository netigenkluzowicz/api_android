---
title: AdMobRewarded - gms
---

[gms](../../index.md) / [pl.netigen.gms.ads](../index.md) / [AdMobRewarded](./index.md)

# AdMobRewarded

`class AdMobRewarded : IRewardedAd, `[`LifecycleObserver`](https://netigenkluzowicz.github.io/api_android/core/androidx/lifecycle/LifecycleObserver.md))

[IRewardedAd](#) implementation with [RewardedAd](#) from Google Mobile Ads SDK

### Types

| [AdCallback](-ad-callback/index.md) | `inner class AdCallback : RewardedAdCallback` |
| [AdLoadCallback](-ad-load-callback/index.md) | `inner class AdLoadCallback : RewardedAdLoadCallback` |

### Constructors

| [&lt;init&gt;](-init-.md)) | Initializes ad, starts observing activity [Lifecycle](https://netigenkluzowicz.github.io/api_android/core/androidx/lifecycle/Lifecycle.md))`AdMobRewarded(activity: `[`ComponentActivity`](https://netigenkluzowicz.github.io/api_android/core/androidx/activity/ComponentActivity.md))`, adMobRequest: `[`IAdMobRequest`](../-i-ad-mob-request/index.md)`, adId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)` = "", enabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)` = adId.isNotEmpty())` |

### Properties

| [adId](ad-id.md)) | Current ad [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) identifier`val adId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) |
| [enabled](enabled.md)) | Current ad [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) identifier`var enabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |
| [isLoaded](is-loaded.md)) | `val isLoaded: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |

### Functions

| [showRewardedAd](show-rewarded-ad.md)) | `fun showRewardedAd(onRewardResult: (`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |

