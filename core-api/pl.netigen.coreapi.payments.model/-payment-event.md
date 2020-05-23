---
title: PaymentEvent - core-api
---

[core-api](../index.html) / [pl.netigen.coreapi.payments.model](index.html) / [PaymentEvent](./-payment-event.html)

# PaymentEvent

`sealed class PaymentEvent`

Represents Payment state it can be [PaymentSuccess](-payment-success/index.html), [PaymentRestored](-payment-restored/index.html) or [Error](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-error/index.html)

### Inheritors

| [PaymentError](-payment-error/index.html) | Represents payment or billing service error with message and error type`data class PaymentError : `[`PaymentEvent`](./-payment-event.html) |
| [PaymentRestored](-payment-restored/index.html) | Represents payment restored from billing service with given sku(product Id)`data class PaymentRestored : `[`PaymentEvent`](./-payment-event.html) |
| [PaymentSuccess](-payment-success/index.html) | Represents successful payment done by user with given sku(product Id)`data class PaymentSuccess : `[`PaymentEvent`](./-payment-event.html) |

