---
title: IPayments - core-api
---

[core-api](../../index.md) / [pl.netigen.coreapi.payments](../index.md) / [IPayments](./index.md)

# IPayments

`interface IPayments : `[`INoAds`](../-i-no-ads/index.md)

Interface for payments, extends [INoAds](../-i-no-ads/index.md)

Use this for:

* observe no-ads payments changes,
* observe owned payments changes (refreshed on application start),
* observe available payments with details ([NetigenSkuDetails](../../pl.netigen.coreapi.payments.model/-netigen-sku-details/index.md)) fetched form store
* making calls for launch payment flow

### Properties

| [inAppSkuDetailsLD](in-app-sku-details-l-d.html) | [LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html) with list of [NetigenSkuDetails](../../pl.netigen.coreapi.payments.model/-netigen-sku-details/index.md), use this to get information of current available in app payments`abstract val inAppSkuDetailsLD: `[`LiveData`](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.md)`<`[`NetigenSkuDetails`](../../pl.netigen.coreapi.payments.model/-netigen-sku-details/index.md)`>>` |
| [inAppSkuList](in-app-sku-list.html) | List of all payments sku available in Application for default it is only one default no-ads sku: [packageName](../-i-no-ads/package-name.html) +".noads"`abstract val inAppSkuList: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`>` |
| [lastPaymentEvent](last-payment-event.html) | [SingleLiveEvent](../../pl.netigen.extensions/-single-live-event/index.md) indicating last payment event it can be success [PaymentEvent.PaymentSuccess](#), [PaymentEvent.PaymentRestored](#) or [PaymentEvent.Error](#)`abstract val lastPaymentEvent: `[`SingleLiveEvent`](../../pl.netigen.extensions/-single-live-event/index.md)`<`[`PaymentEvent`](../../pl.netigen.coreapi.payments.model/-payment-event.html)`>` |
| [ownedPurchasesSkuLD](owned-purchases-sku-l-d.html) | [LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html) with list of payments owned by user (this payments [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) sku)`abstract val ownedPurchasesSkuLD: `[`LiveData`](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`>>` |
| [subsSkuDetailsLD](subs-sku-details-l-d.html) | [LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html) with list of [NetigenSkuDetails](../../pl.netigen.coreapi.payments.model/-netigen-sku-details/index.md), use this to get information of current available subscriptions`abstract val subsSkuDetailsLD: `[`LiveData`](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.md)`<`[`NetigenSkuDetails`](../../pl.netigen.coreapi.payments.model/-netigen-sku-details/index.md)`>>` |

### Functions

| [makePurchase](make-purchase.html) | Makes call to current payment implementation to launch billing flow for given [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) sku.`abstract fun makePurchase(activity: `[`Activity`](https://developer.android.com/reference/android/app/Activity.html)`, sku: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [onActivityResult](on-activity-result.html) | Used only in HMS payments implementation, to handle billing events Not need to be called manually`abstract fun onActivityResult(requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md)`, resultCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md)`, data: `[`Intent`](https://developer.android.com/reference/android/content/Intent.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |

### Inheritors

| [ICoreMainVM](../../pl.netigen.coreapi.main/-i-core-main-v-m/index.md) | This implementations, provides access to api modules:`interface ICoreMainVM : `[`IPayments`](./index.md)`, `[`IAds`](../../pl.netigen.coreapi.ads/-i-ads/index.md)`, `[`INetworkStatus`](../../pl.netigen.coreapi.network/-i-network-status/index.md)`, `[`IGDPRConsent`](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.md)`, `[`IAppConfig`](../../pl.netigen.coreapi.main/-i-app-config/index.md) |
| [Payments](../-payments/index.md) | Base class for [IPayments](./index.md) implementations`abstract class Payments : `[`IPayments`](./index.md)`, `[`IPaymentsRepo`](../-i-payments-repo/index.md) |

