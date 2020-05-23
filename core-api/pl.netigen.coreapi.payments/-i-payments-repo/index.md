---
title: IPaymentsRepo - core-api
---

[core-api](../../index.html) / [pl.netigen.coreapi.payments](../index.html) / [IPaymentsRepo](./index.html)

# IPaymentsRepo

`interface IPaymentsRepo`

Interface for current payments repository implementation

Current design uses [androidx.room.Room](https://developer.android.com/reference/kotlin/androidx/room/Room.html) database + [Flow](#)/[LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html) for observing purchases data

**See Also**

[IPayments](../-i-payments/index.html)

### Properties

| [inAppSkuDetailsLD](in-app-sku-details-l-d.html) | [LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html) with list of [NetigenSkuDetails](../../pl.netigen.coreapi.payments.model/-netigen-sku-details/index.html), use this to get information of current available in app payments`abstract val inAppSkuDetailsLD: `[`LiveData`](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`NetigenSkuDetails`](../../pl.netigen.coreapi.payments.model/-netigen-sku-details/index.html)`>>` |
| [lastPaymentEvent](last-payment-event.html) | [SingleLiveEvent](../../pl.netigen.extensions/-single-live-event/index.html) indicating last payment event it can be success [PaymentEvent.PaymentSuccess](#), [PaymentEvent.PaymentRestored](#) or [PaymentEvent.Error](#)`abstract val lastPaymentEvent: `[`SingleLiveEvent`](../../pl.netigen.extensions/-single-live-event/index.html)`<`[`PaymentEvent`](../../pl.netigen.coreapi.payments.model/-payment-event.html)`>` |
| [noAdsActive](no-ads-active.html) | Emits [Flow](#)&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt; indicating that no ads in-app purchase is or has changed to active or inactive Use this for behave with no ads related stuff, (e.g. hide/show no ads buy buttons)`abstract val noAdsActive: Flow<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [ownedPurchasesSkuLD](owned-purchases-sku-l-d.html) | [LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html) with list of payments owned by user (this payments [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) sku)`abstract val ownedPurchasesSkuLD: `[`LiveData`](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>` |
| [subsSkuDetailsLD](subs-sku-details-l-d.html) | [LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html) with list of [NetigenSkuDetails](../../pl.netigen.coreapi.payments.model/-netigen-sku-details/index.html), use this to get information of current available subscriptions`abstract val subsSkuDetailsLD: `[`LiveData`](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`NetigenSkuDetails`](../../pl.netigen.coreapi.payments.model/-netigen-sku-details/index.html)`>>` |

### Inheritors

| [Payments](../-payments/index.html) | Base class for [IPayments](../-i-payments/index.html) implementations`abstract class Payments : `[`IPayments`](../-i-payments/index.html)`, `[`IPaymentsRepo`](./index.html) |

