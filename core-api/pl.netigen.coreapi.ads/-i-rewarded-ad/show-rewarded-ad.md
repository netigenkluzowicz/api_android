---
title: IRewardedAd.showRewardedAd - core-api
---

[core-api](../../index.html) / [pl.netigen.coreapi.ads](../index.html) / [IRewardedAd](index.html) / [showRewardedAd](./show-rewarded-ad.html)

# showRewardedAd

`abstract fun showRewardedAd(onRewardResult: (`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Shows rewarded ad and provides information if user should be given with reward

### Parameters

`onRewardResult` - Callback invoked when interaction with ad is finished, if onRewardResult is true user should be rewarded