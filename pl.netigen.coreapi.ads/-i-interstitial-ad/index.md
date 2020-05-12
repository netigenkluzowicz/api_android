[core-api](../../index.md) / [pl.netigen.coreapi.ads](../index.md) / [IInterstitialAd](./index.md)

# IInterstitialAd

`interface IInterstitialAd : `[`IAd`](../-i-ad/index.md)

### Properties

| [isLoaded](is-loaded.md) | `abstract val isLoaded: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

### Functions

| [load](load.md) | `abstract fun load(): Flow<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [loadIfShouldBeLoaded](load-if-should-be-loaded.md) | `abstract fun loadIfShouldBeLoaded(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [showIfCanBeShowed](show-if-can-be-showed.md) | `abstract fun showIfCanBeShowed(forceShow: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, onClosedOrNotShowed: (`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

