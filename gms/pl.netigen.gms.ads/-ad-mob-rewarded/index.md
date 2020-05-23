---
title: AdMobRewarded - gms
---

[gms](../../index.html) / [pl.netigen.gms.ads](../index.html) / [AdMobRewarded](./index.html)

# AdMobRewarded

`class AdMobRewarded : IRewardedAd, `[`LifecycleObserver`](https://developer.android.com/reference/kotlin/androidx/lifecycle/LifecycleObserver.html)

[IRewardedAd](#) implementation with [RewardedAd](#) from Google Mobile Ads SDK

### Types

| [AdCallback](-ad-callback/index.html) | `inner class AdCallback : RewardedAdCallback` |
| [AdLoadCallback](-ad-load-callback/index.html) | `inner class AdLoadCallback : RewardedAdLoadCallback` |

### Constructors

| [&lt;init&gt;](-init-.html) | Initializes ad, starts observing activity [Lifecycle](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.html)`AdMobRewarded(activity: `[`ComponentActivity`](https://developer.android.com/reference/kotlin/androidx/activity/ComponentActivity.html)`, adMobRequest: `[`IAdMobRequest`](../-i-ad-mob-request/index.html)`, adId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "", enabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = adId.isNotEmpty())` |

### Properties

| [adId](ad-id.html) | Current ad [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) identifier`val adId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [enabled](enabled.html) | Current ad [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) identifier`var enabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isLoaded](is-loaded.html) | `val isLoaded: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

### Functions

| [showRewardedAd](show-rewarded-ad.html) | `fun showRewardedAd(onRewardResult: (`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

