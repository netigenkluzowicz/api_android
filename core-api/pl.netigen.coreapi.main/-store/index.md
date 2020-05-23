---
title: Store - core-api
---

[core-api](../../index.html) / [pl.netigen.coreapi.main](../index.html) / [Store](./index.html)

# Store

`enum class Store`

Possible target Stores for Application release

* [Google Play](https://play.google.com/store)
* [Samsung Galaxy Store](https://www.samsung.com/apps/galaxy-store/)
* [Huawei AppGallery](https://huaweimobileservices.com/appgallery/)

### Enum Values

| [GOOGLE_PLAY](-g-o-o-g-l-e_-p-l-a-y.html) | For [Google Play](https://play.google.com/store) application target store. |
| [SAMSUNG](-s-a-m-s-u-n-g.html) | For [Samsung Galaxy Store](https://www.samsung.com/apps/galaxy-store/) application target store. |
| [HUAWEI](-h-u-a-w-e-i.html) | For [Huawei AppGallery](https://huaweimobileservices.com/appgallery/) application target store. |

### Extension Functions

| [validateIn](../../pl.netigen.extensions/validate-in.html) | `fun <T : `[`Comparable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-comparable/index.html)`<T>> T.validateIn(values: `[`Iterable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/index.html)`<T>, name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [validateInRange](../../pl.netigen.extensions/validate-in-range.html) | `fun <T : `[`Comparable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-comparable/index.html)`<T>> T.validateInRange(range: `[`ClosedRange`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-closed-range/index.html)`<T>, name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [validateNotSmaller](../../pl.netigen.extensions/validate-not-smaller.html) | `fun <T : `[`Comparable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-comparable/index.html)`<T>> T.validateNotSmaller(limit: T, name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

