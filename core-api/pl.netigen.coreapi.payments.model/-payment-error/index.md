---
title: PaymentError - core-api
---

[home page](../index.md)/[core-api](../../index.md) / [pl.netigen.coreapi.payments.model](../index.md) / [PaymentError](./index.md)

# PaymentError

`data class PaymentError : `[`PaymentEvent`](../-payment-event.html)

Represents payment or billing service error with message and error type

### Constructors

| [&lt;init&gt;](-init-.html) | Represents payment or billing service error with message and error type`PaymentError(errorMessage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`, errorType: `[`PaymentErrorType`](../-payment-error-type/index.md)`)` |

### Properties

| [errorMessage](error-message.html) | This error debug message`val errorMessage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) |
| [errorType](error-type.html) | This error type`val errorType: `[`PaymentErrorType`](../-payment-error-type/index.md) |

