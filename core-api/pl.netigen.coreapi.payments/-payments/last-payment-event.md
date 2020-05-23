---
title: Payments.lastPaymentEvent - core-api
---

[api_android](../index.md)(../index.md)/[core-api](../../index.md) / [pl.netigen.coreapi.payments](../index.md) / [Payments](index.md) / [lastPaymentEvent](./last-payment-event.html)

# lastPaymentEvent

`open val lastPaymentEvent: `[`SingleLiveEvent`](../../pl.netigen.extensions/-single-live-event/index.md)`<`[`PaymentEvent`](../../pl.netigen.coreapi.payments.model/-payment-event.html)`>`

[SingleLiveEvent](../../pl.netigen.extensions/-single-live-event/index.md) indicating last payment event it can be success [PaymentEvent.PaymentSuccess](#), [PaymentEvent.PaymentRestored](#) or [PaymentEvent.Error](#)

This should be used for show information about billing flow to the users

warning this currently not working/tested in HMS!!

**Getter**

[SingleLiveEvent](../../pl.netigen.extensions/-single-live-event/index.md) indicating last payment event it can be success [PaymentEvent.PaymentSuccess](#), [PaymentEvent.PaymentRestored](#) or [PaymentEvent.Error](#)

This should be used for show information about billing flow to the users

warning this currently not working/tested in HMS!!

