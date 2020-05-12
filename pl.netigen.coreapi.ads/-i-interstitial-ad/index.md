---
title: IInterstitialAd - core-api
---

[core-api](../../index.html) / [pl.netigen.coreapi.ads](../index.html) / [IInterstitialAd](./index.html)

# IInterstitialAd

`interface IInterstitialAd : `[`IAd`](../-i-ad/index.html)

### Properties

| [isLoaded](is-loaded.html) | `abstract val isLoaded: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

### Functions

| [load](load.html) | `abstract fun load(): Flow<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [loadIfShouldBeLoaded](load-if-should-be-loaded.html) | `abstract fun loadIfShouldBeLoaded(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [showIfCanBeShowed](show-if-can-be-showed.html) | `abstract fun showIfCanBeShowed(forceShow: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, onClosedOrNotShowed: (`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

