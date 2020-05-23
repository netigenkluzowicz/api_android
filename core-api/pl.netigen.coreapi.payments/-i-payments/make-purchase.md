---
title: IPayments.makePurchase - core-api
---

[core-api](../../index.md) / [pl.netigen.coreapi.payments](../index.md) / [IPayments](index.md) / [makePurchase](./make-purchase.md))

# makePurchase

`abstract fun makePurchase(activity: `[`Activity`](https://developer.android.com/reference/android/app/Activity.md))`, sku: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md)

Makes call to current payment implementation to launch billing flow for given [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) sku.

It will show the purchase screen to the user

When user successfully complete purchase, [ownedPurchasesSkuLD](owned-purchases-sku-l-d.md)) live data will be updated with this purchase sku

### Parameters

`activity` - activity used to as context for launch

`sku` - in-app purchase identifier (sku)