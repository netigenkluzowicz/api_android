---
title: Payments - api_android
---

[api_android](../../index.html) / [pl.netigen.coreapi.payments](../index.html) / [Payments](./index.html)

# Payments

`abstract class Payments : `[`IPayments`](../-i-payments/index.html)`, `[`IPaymentsRepo`](../-i-payments-repo/index.html)

Base class for [IPayments](../-i-payments/index.html) implementations

Provides also access to payments repository and default sku values

### Parameters

`activity` - [Activity](#) using in current Payments implementation and for get [packageName](package-name.html)

### Constructors

| [&lt;init&gt;](-init-.html) | Base class for [IPayments](../-i-payments/index.html) implementations`Payments(activity: Activity)` |

### Properties

| [inAppSkuDetailsLD](in-app-sku-details-l-d.html) | [LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html) with list of [NetigenSkuDetails](../../pl.netigen.coreapi.payments.model/-netigen-sku-details/index.html), use this to get information of current available in app payments`open val inAppSkuDetailsLD: `[`LiveData`](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`NetigenSkuDetails`](../../pl.netigen.coreapi.payments.model/-netigen-sku-details/index.html)`>>` |
| [lastPaymentEvent](last-payment-event.html) | [SingleLiveEvent](../../pl.netigen.extensions/-single-live-event/index.html) indicating last payment event it can be success [PaymentEvent.PaymentSuccess](#), [PaymentEvent.PaymentRestored](#) or [PaymentEvent.Error](#)`open val lastPaymentEvent: `[`SingleLiveEvent`](../../pl.netigen.extensions/-single-live-event/index.html)`<`[`PaymentEvent`](../../pl.netigen.coreapi.payments.model/-payment-event.html)`>` |
| [noAdsActive](no-ads-active.html) | Emits [Flow](#)&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt; indicating that no ads in-app purchase is or has changed to active or inactive`open val noAdsActive: Flow<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [ownedPurchasesSkuLD](owned-purchases-sku-l-d.html) | [LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html) with list of payments owned by user (this payments [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) sku)`open val ownedPurchasesSkuLD: `[`LiveData`](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>` |
| [packageName](package-name.html) | Application package name for default no ads sku`open val packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [paymentsRepo](payments-repo.html) | Current [IPaymentsRepo](../-i-payments-repo/index.html) implementation, current design uses [androidx.room.Room](https://developer.android.com/reference/kotlin/androidx/room/Room.html) database + [Flow](#)/[LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html) for observing purchases data`abstract val paymentsRepo: `[`IPaymentsRepo`](../-i-payments-repo/index.html) |
| [subsSkuDetailsLD](subs-sku-details-l-d.html) | [LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html) with list of [NetigenSkuDetails](../../pl.netigen.coreapi.payments.model/-netigen-sku-details/index.html), use this to get information of current available subscriptions`open val subsSkuDetailsLD: `[`LiveData`](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`NetigenSkuDetails`](../../pl.netigen.coreapi.payments.model/-netigen-sku-details/index.html)`>>` |

### Functions

| [onActivityResult](on-activity-result.html) | Used only in HMS payments implementation, to handle billing events Not need to be called manually`open fun onActivityResult(requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, resultCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, data: Intent): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| [GMSPayments](../../pl.netigen.gms.payments/-g-m-s-payments/index.html) | `class GMSPayments : `[`Payments`](./index.html) |

