---
title: IInterstitialAd.showIfCanBeShowed - core-api
---

[core-api](../../index.html) / [pl.netigen.coreapi.ads](../index.html) / [IInterstitialAd](index.html) / [showIfCanBeShowed](./show-if-can-be-showed.html)

# showIfCanBeShowed

`abstract fun showIfCanBeShowed(forceShow: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, onClosedOrNotShowed: (`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Shows ad if it is loaded and minimum time between this ads passed (limit can be ignored by [forceShow](show-if-can-be-showed.html#pl.netigen.coreapi.ads.IInterstitialAd$showIfCanBeShowed(kotlin.Boolean, kotlin.Function1((kotlin.Boolean, kotlin.Unit)))/forceShow) = true)

### Parameters

`forceShow` - If true time limit is ignored

`onClosedOrNotShowed` - Callback called when ad can't be showed or ad is closed