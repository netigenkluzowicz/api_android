---
title: PaymentEvent - core-api
---

[core-api](../index.md) / [pl.netigen.coreapi.payments.model](index.md) / [PaymentEvent](./-payment-event.md))

# PaymentEvent

`sealed class PaymentEvent`

Represents Payment state it can be [PaymentSuccess](-payment-success/index.md), [PaymentRestored](-payment-restored/index.md) or [Error](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-error/index.md)

### Inheritors

| [PaymentError](-payment-error/index.md) | Represents payment or billing service error with message and error type`data class PaymentError : `[`PaymentEvent`](./-payment-event.md)) |
| [PaymentRestored](-payment-restored/index.md) | Represents payment restored from billing service with given sku(product Id)`data class PaymentRestored : `[`PaymentEvent`](./-payment-event.md)) |
| [PaymentSuccess](-payment-success/index.md) | Represents successful payment done by user with given sku(product Id)`data class PaymentSuccess : `[`PaymentEvent`](./-payment-event.md)) |

