---
title: IRewardedAd - api_android
---

[api_android](../../index.html) / [pl.netigen.coreapi.ads](../index.html) / [IRewardedAd](./index.html)

# IRewardedAd

`interface IRewardedAd : `[`IAd`](../-i-ad/index.html)

Manages rewarded ads:

* loading first and next ads when first is displayed
* reloading on load errors
* showing to the users
* provides callback about result of the reward watch user action

Rewarded ad  allow you to reward users with in-app items for interacting with video ads, playable ads, and surveys.

### Properties

| [isLoaded](is-loaded.html) | Informs is ad ready to show`abstract val isLoaded: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

### Functions

| [showRewardedAd](show-rewarded-ad.html) | Shows rewarded ad and provides information if user should be given with reward`abstract fun showRewardedAd(onRewardResult: (`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| [AdMobRewarded](../../pl.netigen.gms.ads/-ad-mob-rewarded/index.html) | [IRewardedAd](./index.html) implementation with [RewardedAd](#) from Google Mobile Ads SDK`class AdMobRewarded : `[`IRewardedAd`](./index.html)`, `[`LifecycleObserver`](https://developer.android.com/reference/kotlin/androidx/lifecycle/LifecycleObserver.html) |

