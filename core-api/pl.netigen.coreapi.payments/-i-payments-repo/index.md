---
title: IPaymentsRepo - core-api
---

[api_android](../index.md)(../index.md)/[core-api](../../index.md) / [pl.netigen.coreapi.payments](../index.md) / [IPaymentsRepo](./index.md)

# IPaymentsRepo

`interface IPaymentsRepo`

Interface for current payments repository implementation

Current design uses [androidx.room.Room](https://developer.android.com/reference/kotlin/androidx/room/Room.html) database + [Flow](#)/[LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html) for observing purchases data

**See Also**

[IPayments](../-i-payments/index.md)

### Properties

| [inAppSkuDetailsLD](in-app-sku-details-l-d.html) | [LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html) with list of [NetigenSkuDetails](../../pl.netigen.coreapi.payments.model/-netigen-sku-details/index.md), use this to get information of current available in app payments`abstract val inAppSkuDetailsLD: `[`LiveData`](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.md)`<`[`NetigenSkuDetails`](../../pl.netigen.coreapi.payments.model/-netigen-sku-details/index.md)`>>` |
| [lastPaymentEvent](last-payment-event.html) | [SingleLiveEvent](../../pl.netigen.extensions/-single-live-event/index.md) indicating last payment event it can be success [PaymentEvent.PaymentSuccess](#), [PaymentEvent.PaymentRestored](#) or [PaymentEvent.Error](#)`abstract val lastPaymentEvent: `[`SingleLiveEvent`](../../pl.netigen.extensions/-single-live-event/index.md)`<`[`PaymentEvent`](../../pl.netigen.coreapi.payments.model/-payment-event.html)`>` |
| [noAdsActive](no-ads-active.html) | Emits [Flow](#)&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)&gt; indicating that no ads in-app purchase is or has changed to active or inactive Use this for behave with no ads related stuff, (e.g. hide/show no ads buy buttons)`abstract val noAdsActive: Flow<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)`>` |
| [ownedPurchasesSkuLD](owned-purchases-sku-l-d.html) | [LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html) with list of payments owned by user (this payments [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) sku)`abstract val ownedPurchasesSkuLD: `[`LiveData`](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`>>` |
| [subsSkuDetailsLD](subs-sku-details-l-d.html) | [LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html) with list of [NetigenSkuDetails](../../pl.netigen.coreapi.payments.model/-netigen-sku-details/index.md), use this to get information of current available subscriptions`abstract val subsSkuDetailsLD: `[`LiveData`](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.md)`<`[`NetigenSkuDetails`](../../pl.netigen.coreapi.payments.model/-netigen-sku-details/index.md)`>>` |

### Inheritors

| [Payments](../-payments/index.md) | Base class for [IPayments](../-i-payments/index.md) implementations`abstract class Payments : `[`IPayments`](../-i-payments/index.md)`, `[`IPaymentsRepo`](./index.md) |

