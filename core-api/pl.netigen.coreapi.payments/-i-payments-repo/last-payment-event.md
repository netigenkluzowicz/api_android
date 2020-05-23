---
title: IPaymentsRepo.lastPaymentEvent - core-api
---

[core-api](../../index.html) / [pl.netigen.coreapi.payments](../index.html) / [IPaymentsRepo](index.html) / [lastPaymentEvent](./last-payment-event.html)

# lastPaymentEvent

`abstract val lastPaymentEvent: `[`SingleLiveEvent`](../../pl.netigen.extensions/-single-live-event/index.html)`<`[`PaymentEvent`](../../pl.netigen.coreapi.payments.model/-payment-event.html)`>`

[SingleLiveEvent](../../pl.netigen.extensions/-single-live-event/index.html) indicating last payment event it can be success [PaymentEvent.PaymentSuccess](#), [PaymentEvent.PaymentRestored](#) or [PaymentEvent.Error](#)

This should be used for show information about billing flow to the users

