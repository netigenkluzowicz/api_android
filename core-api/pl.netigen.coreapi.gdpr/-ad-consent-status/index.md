---
title: AdConsentStatus - core-api
---

[core-api](../../index.md) / [pl.netigen.coreapi.gdpr](../index.md) / [AdConsentStatus](./index.md)

# AdConsentStatus

`enum class AdConsentStatus`

Possible values of Gdpr ads consent status

* [PERSONALIZED_NON_UE](-p-e-r-s-o-n-a-l-i-z-e-d_-n-o-n_-u-e.md)) - user location allows use of personalized ads
* [PERSONALIZED_SHOWED](-p-e-r-s-o-n-a-l-i-z-e-d_-s-h-o-w-e-d.md)) - consent was collected from user with personalized ads allowed
* [NON_PERSONALIZED_SHOWED](-n-o-n_-p-e-r-s-o-n-a-l-i-z-e-d_-s-h-o-w-e-d.md)) - consent was collected from user with personalized ads not allowed
* [UNINITIALIZED](-u-n-i-n-i-t-i-a-l-i-z-e-d.md)) - consent was not collected and user location is not checked- ads should not be displayed

### Enum Values

| [PERSONALIZED_NON_UE](-p-e-r-s-o-n-a-l-i-z-e-d_-n-o-n_-u-e.md)) |  |
| [PERSONALIZED_SHOWED](-p-e-r-s-o-n-a-l-i-z-e-d_-s-h-o-w-e-d.md)) |  |
| [NON_PERSONALIZED_SHOWED](-n-o-n_-p-e-r-s-o-n-a-l-i-z-e-d_-s-h-o-w-e-d.md)) |  |
| [UNINITIALIZED](-u-n-i-n-i-t-i-a-l-i-z-e-d.md)) |  |

### Extension Functions

| [validateIn](../../pl.netigen.extensions/validate-in.md)) | `fun <T : `[`Comparable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-comparable/index.md)`<T>> T.validateIn(values: `[`Iterable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/index.md)`<T>, name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [validateInRange](../../pl.netigen.extensions/validate-in-range.md)) | `fun <T : `[`Comparable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-comparable/index.md)`<T>> T.validateInRange(range: `[`ClosedRange`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-closed-range/index.md)`<T>, name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [validateNotSmaller](../../pl.netigen.extensions/validate-not-smaller.md)) | `fun <T : `[`Comparable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-comparable/index.md)`<T>> T.validateNotSmaller(limit: T, name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |

