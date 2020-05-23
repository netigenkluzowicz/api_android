---
title: IRewardedAd - core-api
---

[api_android](../index.md)/[core-api](../../index.md)/[pl.netigen.coreapi.ads](../index.md)/[IRewardedAd](./index.md)

# IRewardedAd

`interface IRewardedAd : `[`IAd`](../-i-ad/index.md)

Manages rewarded ads:

* loading first and next ads when first is displayed
* reloading on load errors
* showing to the users
* provides callback about result of the reward watch user action

Rewarded ad  allow you to reward users with in-app items for interacting with video ads, playable ads, and surveys.

### Properties

| [isLoaded](is-loaded.html) | Informs is ad ready to show`abstract val isLoaded: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |

### Functions

| [showRewardedAd](show-rewarded-ad.html) | Shows rewarded ad and provides information if user should be given with reward`abstract fun showRewardedAd(onRewardResult: (`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |

