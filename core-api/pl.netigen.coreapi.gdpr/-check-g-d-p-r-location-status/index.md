---
title: CheckGDPRLocationStatus - core-api
---

[api_android](../index.md)(../index.md)/[core-api](../../index.md) / [pl.netigen.coreapi.gdpr](../index.md) / [CheckGDPRLocationStatus](./index.md)

# CheckGDPRLocationStatus

`enum class CheckGDPRLocationStatus`

Possible user GDPR location statuses:

* [UE](-u-e.html) Consent should be displayed
* [NON_UE](-n-o-n_-u-e.html) Consent can be skipped
* [ERROR](-e-r-r-o-r.html) Consent should be displayed because location is unknown

### Enum Values

| [UE](-u-e.html) |  |
| [NON_UE](-n-o-n_-u-e.html) |  |
| [ERROR](-e-r-r-o-r.html) |  |

### Extension Functions

| [validateIn](../../pl.netigen.extensions/validate-in.html) | `fun <T : `[`Comparable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-comparable/index.md)`<T>> T.validateIn(values: `[`Iterable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/index.md)`<T>, name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [validateInRange](../../pl.netigen.extensions/validate-in-range.html) | `fun <T : `[`Comparable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-comparable/index.md)`<T>> T.validateInRange(range: `[`ClosedRange`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-closed-range/index.md)`<T>, name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [validateNotSmaller](../../pl.netigen.extensions/validate-not-smaller.html) | `fun <T : `[`Comparable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-comparable/index.md)`<T>> T.validateNotSmaller(limit: T, name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |

