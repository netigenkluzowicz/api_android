---
title: AdMobInterstitial.showIfCanBeShowed - api_android
---

[api_android](../../index.html) / [pl.netigen.gms.ads](../index.html) / [AdMobInterstitial](index.html) / [showIfCanBeShowed](./show-if-can-be-showed.html)

# showIfCanBeShowed

`fun showIfCanBeShowed(forceShow: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, onClosedOrNotShowed: (`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Shows ad if it is loaded and minimum time between this ads passed (limit can be ignored by [forceShow](../../pl.netigen.coreapi.ads/-i-interstitial-ad/show-if-can-be-showed.html#pl.netigen.coreapi.ads.IInterstitialAd$showIfCanBeShowed(kotlin.Boolean, kotlin.Function1((kotlin.Boolean, kotlin.Unit)))/forceShow) = true)

### Parameters

`forceShow` - If true time limit is ignored

`onClosedOrNotShowed` - Callback called when ad can't be showed or ad is closed