---
title: GMSPayments.makePurchase - api_android
---

[api_android](../../index.html) / [pl.netigen.gms.payments](../index.html) / [GMSPayments](index.html) / [makePurchase](./make-purchase.html)

# makePurchase

`fun makePurchase(activity: Activity, sku: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Makes call to current payment implementation to launch billing flow for given [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) sku.

It will show the purchase screen to the user

When user successfully complete purchase, [ownedPurchasesSkuLD](../../pl.netigen.coreapi.payments/-i-payments/owned-purchases-sku-l-d.html) live data will be updated with this purchase sku

### Parameters

`activity` - activity used to as context for launch

`sku` - in-app purchase identifier (sku)