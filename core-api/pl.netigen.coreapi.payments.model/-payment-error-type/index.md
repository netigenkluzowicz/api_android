---
title: PaymentErrorType - core-api
---

[api_android](../index.md)(../index.md)/[core-api](../../index.md) / [pl.netigen.coreapi.payments.model](../index.md) / [PaymentErrorType](./index.md)

# PaymentErrorType

`enum class PaymentErrorType`

Enumerates possible billing flow errors

see: [PaymentError](../-payment-error/index.md)

### Enum Values

| [SERVICE_TIMEOUT](-s-e-r-v-i-c-e_-t-i-m-e-o-u-t.html) |  |
| [FEATURE_NOT_SUPPORTED](-f-e-a-t-u-r-e_-n-o-t_-s-u-p-p-o-r-t-e-d.html) |  |
| [SERVICE_DISCONNECTED](-s-e-r-v-i-c-e_-d-i-s-c-o-n-n-e-c-t-e-d.html) |  |
| [USER_CANCELED](-u-s-e-r_-c-a-n-c-e-l-e-d.html) |  |
| [SERVICE_UNAVAILABLE](-s-e-r-v-i-c-e_-u-n-a-v-a-i-l-a-b-l-e.html) |  |
| [BILLING_UNAVAILABLE](-b-i-l-l-i-n-g_-u-n-a-v-a-i-l-a-b-l-e.html) |  |
| [ITEM_UNAVAILABLE](-i-t-e-m_-u-n-a-v-a-i-l-a-b-l-e.html) |  |
| [DEVELOPER_ERROR](-d-e-v-e-l-o-p-e-r_-e-r-r-o-r.html) |  |
| [ERROR](-e-r-r-o-r.html) |  |
| [ITEM_ALREADY_OWNED](-i-t-e-m_-a-l-r-e-a-d-y_-o-w-n-e-d.html) |  |
| [ITEM_NOT_OWNED](-i-t-e-m_-n-o-t_-o-w-n-e-d.html) |  |

### Extension Functions

| [validateIn](../../pl.netigen.extensions/validate-in.html) | `fun <T : `[`Comparable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-comparable/index.md)`<T>> T.validateIn(values: `[`Iterable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/index.md)`<T>, name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [validateInRange](../../pl.netigen.extensions/validate-in-range.html) | `fun <T : `[`Comparable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-comparable/index.md)`<T>> T.validateInRange(range: `[`ClosedRange`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-closed-range/index.md)`<T>, name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [validateNotSmaller](../../pl.netigen.extensions/validate-not-smaller.html) | `fun <T : `[`Comparable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-comparable/index.md)`<T>> T.validateNotSmaller(limit: T, name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |

