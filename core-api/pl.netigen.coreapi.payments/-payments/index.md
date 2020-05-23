---
title: Payments - core-api
---

[core-api](../../index.md) / [pl.netigen.coreapi.payments](../index.md) / [Payments](./index.md)

# Payments

`abstract class Payments : `[`IPayments`](../-i-payments/index.md)`, `[`IPaymentsRepo`](../-i-payments-repo/index.md)

Base class for [IPayments](../-i-payments/index.md) implementations

Provides also access to payments repository and default sku values

### Parameters

`activity` - [Activity](https://developer.android.com/reference/android/app/Activity.html) using in current Payments implementation and for get [packageName](package-name.html)

### Constructors

| [&lt;init&gt;](-init-.html) | Base class for [IPayments](../-i-payments/index.md) implementations`Payments(activity: `[`Activity`](https://developer.android.com/reference/android/app/Activity.html)`)` |

### Properties

| [inAppSkuDetailsLD](in-app-sku-details-l-d.html) | [LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html) with list of [NetigenSkuDetails](../../pl.netigen.coreapi.payments.model/-netigen-sku-details/index.md), use this to get information of current available in app payments`open val inAppSkuDetailsLD: `[`LiveData`](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.md)`<`[`NetigenSkuDetails`](../../pl.netigen.coreapi.payments.model/-netigen-sku-details/index.md)`>>` |
| [lastPaymentEvent](last-payment-event.html) | [SingleLiveEvent](../../pl.netigen.extensions/-single-live-event/index.md) indicating last payment event it can be success [PaymentEvent.PaymentSuccess](#), [PaymentEvent.PaymentRestored](#) or [PaymentEvent.Error](#)`open val lastPaymentEvent: `[`SingleLiveEvent`](../../pl.netigen.extensions/-single-live-event/index.md)`<`[`PaymentEvent`](../../pl.netigen.coreapi.payments.model/-payment-event.html)`>` |
| [noAdsActive](no-ads-active.html) | Emits [Flow](#)&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)&gt; indicating that no ads in-app purchase is or has changed to active or inactive`open val noAdsActive: Flow<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)`>` |
| [ownedPurchasesSkuLD](owned-purchases-sku-l-d.html) | [LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html) with list of payments owned by user (this payments [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) sku)`open val ownedPurchasesSkuLD: `[`LiveData`](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`>>` |
| [packageName](package-name.html) | Application package name for default no ads sku`open val packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) |
| [paymentsRepo](payments-repo.html) | Current [IPaymentsRepo](../-i-payments-repo/index.md) implementation, current design uses [androidx.room.Room](https://developer.android.com/reference/kotlin/androidx/room/Room.html) database + [Flow](#)/[LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html) for observing purchases data`abstract val paymentsRepo: `[`IPaymentsRepo`](../-i-payments-repo/index.md) |
| [subsSkuDetailsLD](subs-sku-details-l-d.html) | [LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html) with list of [NetigenSkuDetails](../../pl.netigen.coreapi.payments.model/-netigen-sku-details/index.md), use this to get information of current available subscriptions`open val subsSkuDetailsLD: `[`LiveData`](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.md)`<`[`NetigenSkuDetails`](../../pl.netigen.coreapi.payments.model/-netigen-sku-details/index.md)`>>` |

### Functions

| [onActivityResult](on-activity-result.html) | Used only in HMS payments implementation, to handle billing events Not need to be called manually`open fun onActivityResult(requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md)`, resultCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md)`, data: `[`Intent`](https://developer.android.com/reference/android/content/Intent.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |

