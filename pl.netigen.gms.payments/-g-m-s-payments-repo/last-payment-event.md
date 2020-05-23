---
title: GMSPaymentsRepo.lastPaymentEvent - api_android
---

[api_android](../../index.html) / [pl.netigen.gms.payments](../index.html) / [GMSPaymentsRepo](index.html) / [lastPaymentEvent](./last-payment-event.html)

# lastPaymentEvent

`val lastPaymentEvent: `[`SingleLiveEvent`](../../pl.netigen.extensions/-single-live-event/index.html)`<`[`PaymentEvent`](../../pl.netigen.coreapi.payments.model/-payment-event.html)`>`

[SingleLiveEvent](../../pl.netigen.extensions/-single-live-event/index.html) indicating last payment event it can be success [PaymentEvent.PaymentSuccess](#),
[PaymentEvent.PaymentRestored](#) or [PaymentEvent.Error](#)

This should be used for show information about billing flow to the users

