---
title: IPayments.lastPaymentEvent - core-api
---

[core-api](../../index.md) / [pl.netigen.coreapi.payments](../index.md) / [IPayments](index.md) / [lastPaymentEvent](./last-payment-event.md))

# lastPaymentEvent

`abstract val lastPaymentEvent: `[`SingleLiveEvent`](../../pl.netigen.extensions/-single-live-event/index.md)`<`[`PaymentEvent`](../../pl.netigen.coreapi.payments.model/-payment-event.md))`>`

[SingleLiveEvent](../../pl.netigen.extensions/-single-live-event/index.md) indicating last payment event it can be success [PaymentEvent.PaymentSuccess](#), [PaymentEvent.PaymentRestored](#) or [PaymentEvent.Error](#)

This should be used for show information about billing flow to the users

warning this currently not working/tested in HMS!!

