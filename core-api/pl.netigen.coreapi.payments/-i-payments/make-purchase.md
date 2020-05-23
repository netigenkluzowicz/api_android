---
title: IPayments.makePurchase - core-api
---

[core-api](../../index.html) / [pl.netigen.coreapi.payments](../index.html) / [IPayments](index.html) / [makePurchase](./make-purchase.html)

# makePurchase

`abstract fun makePurchase(activity: `[`Activity`](https://developer.android.com/reference/android/app/Activity.html)`, sku: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Makes call to current payment implementation to launch billing flow for given [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) sku.

It will show the purchase screen to the user

When user successfully complete purchase, [ownedPurchasesSkuLD](owned-purchases-sku-l-d.html) live data will be updated with this purchase sku

### Parameters

`activity` - activity used to as context for launch

`sku` - in-app purchase identifier (sku)