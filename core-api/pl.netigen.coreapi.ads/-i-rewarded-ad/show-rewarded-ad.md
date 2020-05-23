---
title: IRewardedAd.showRewardedAd - core-api
---

[core-api](../../index.md) / [pl.netigen.coreapi.ads](../index.md) / [IRewardedAd](index.md) / [showRewardedAd](./show-rewarded-ad.html)

# showRewardedAd

`abstract fun showRewardedAd(onRewardResult: (`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md)

Shows rewarded ad and provides information if user should be given with reward

### Parameters

`onRewardResult` - Callback invoked when interaction with ad is finished, if onRewardResult is true user should be rewarded