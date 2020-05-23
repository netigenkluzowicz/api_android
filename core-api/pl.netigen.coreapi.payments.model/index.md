---
title: pl.netigen.coreapi.payments.model - core-api
---

[api_android](../index.md)/[core-api](../index.md) / [pl.netigen.coreapi.payments.model](./index.md)

## Package pl.netigen.coreapi.payments.model

### Types

| [NetigenSkuDetails](-netigen-sku-details/index.md) | Represents an in-app product's or subscription's  details.`data class NetigenSkuDetails` |
| [PaymentError](-payment-error/index.md) | Represents payment or billing service error with message and error type`data class PaymentError : `[`PaymentEvent`](-payment-event.html) |
| [PaymentErrorType](-payment-error-type/index.md) | Enumerates possible billing flow errors`enum class PaymentErrorType` |
| [PaymentEvent](-payment-event.html) | Represents Payment state it can be [PaymentSuccess](-payment-success/index.md), [PaymentRestored](-payment-restored/index.md) or [Error](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-error/index.md)`sealed class PaymentEvent` |
| [PaymentRestored](-payment-restored/index.md) | Represents payment restored from billing service with given sku(product Id)`data class PaymentRestored : `[`PaymentEvent`](-payment-event.html) |
| [PaymentSuccess](-payment-success/index.md) | Represents successful payment done by user with given sku(product Id)`data class PaymentSuccess : `[`PaymentEvent`](-payment-event.html) |

