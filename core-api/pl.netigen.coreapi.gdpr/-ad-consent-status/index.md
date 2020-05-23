---
title: AdConsentStatus - core-api
---

[core-api](../../index.html) / [pl.netigen.coreapi.gdpr](../index.html) / [AdConsentStatus](./index.html)

# AdConsentStatus

`enum class AdConsentStatus`

Possible values of Gdpr ads consent status

* [PERSONALIZED_NON_UE](-p-e-r-s-o-n-a-l-i-z-e-d_-n-o-n_-u-e.html) - user location allows use of personalized ads
* [PERSONALIZED_SHOWED](-p-e-r-s-o-n-a-l-i-z-e-d_-s-h-o-w-e-d.html) - consent was collected from user with personalized ads allowed
* [NON_PERSONALIZED_SHOWED](-n-o-n_-p-e-r-s-o-n-a-l-i-z-e-d_-s-h-o-w-e-d.html) - consent was collected from user with personalized ads not allowed
* [UNINITIALIZED](-u-n-i-n-i-t-i-a-l-i-z-e-d.html) - consent was not collected and user location is not checked- ads should not be displayed

### Enum Values

| [PERSONALIZED_NON_UE](-p-e-r-s-o-n-a-l-i-z-e-d_-n-o-n_-u-e.html) |  |
| [PERSONALIZED_SHOWED](-p-e-r-s-o-n-a-l-i-z-e-d_-s-h-o-w-e-d.html) |  |
| [NON_PERSONALIZED_SHOWED](-n-o-n_-p-e-r-s-o-n-a-l-i-z-e-d_-s-h-o-w-e-d.html) |  |
| [UNINITIALIZED](-u-n-i-n-i-t-i-a-l-i-z-e-d.html) |  |

### Extension Functions

| [validateIn](../../pl.netigen.extensions/validate-in.html) | `fun <T : `[`Comparable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-comparable/index.html)`<T>> T.validateIn(values: `[`Iterable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/index.html)`<T>, name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [validateInRange](../../pl.netigen.extensions/validate-in-range.html) | `fun <T : `[`Comparable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-comparable/index.html)`<T>> T.validateInRange(range: `[`ClosedRange`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-closed-range/index.html)`<T>, name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [validateNotSmaller](../../pl.netigen.extensions/validate-not-smaller.html) | `fun <T : `[`Comparable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-comparable/index.html)`<T>> T.validateNotSmaller(limit: T, name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

