---
title: pl.netigen.coreapi.payments.model - api_android
---

[api_android](../index.html) / [pl.netigen.coreapi.payments.model](./index.html)

## Package pl.netigen.coreapi.payments.model

### Types

| [NetigenSkuDetails](-netigen-sku-details/index.html) | Represents an in-app product's or subscription's  details.`data class NetigenSkuDetails` |
| [PaymentError](-payment-error/index.html) | Represents payment or billing service error with message and error type`data class PaymentError : `[`PaymentEvent`](-payment-event.html) |
| [PaymentErrorType](-payment-error-type/index.html) | Enumerates possible billing flow errors`enum class PaymentErrorType` |
| [PaymentEvent](-payment-event.html) | Represents Payment state it can be [PaymentSuccess](-payment-success/index.html), [PaymentRestored](-payment-restored/index.html) or [Error](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-error/index.html)`sealed class PaymentEvent` |
| [PaymentRestored](-payment-restored/index.html) | Represents payment restored from billing service with given sku(product Id)`data class PaymentRestored : `[`PaymentEvent`](-payment-event.html) |
| [PaymentSuccess](-payment-success/index.html) | Represents successful payment done by user with given sku(product Id)`data class PaymentSuccess : `[`PaymentEvent`](-payment-event.html) |

