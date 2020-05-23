---
title: Store - core-api
---

[api_android](../index.md)/[core-api](../../index.md)/[pl.netigen.coreapi.main](../index.md)/[Store](./index.md)

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

| [validateIn](../../pl.netigen.extensions/validate-in.html) | `fun <T : `[`Comparable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-comparable/index.md)`<T>> T.validateIn(values: `[`Iterable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/index.md)`<T>, name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [validateInRange](../../pl.netigen.extensions/validate-in-range.html) | `fun <T : `[`Comparable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-comparable/index.md)`<T>> T.validateInRange(range: `[`ClosedRange`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-closed-range/index.md)`<T>, name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [validateNotSmaller](../../pl.netigen.extensions/validate-not-smaller.html) | `fun <T : `[`Comparable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-comparable/index.md)`<T>> T.validateNotSmaller(limit: T, name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |

