---
title: GMSPayments.makeNoAdsPayment - api_android
---

[api_android](../../index.html) / [pl.netigen.gms.payments](../index.html) / [GMSPayments](index.html) / [makeNoAdsPayment](./make-no-ads-payment.html)

# makeNoAdsPayment

`fun makeNoAdsPayment(activity: Activity, noAdsSku: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Makes call to current payment implementation to launch billing flow for an no ads in-app purchase.
It will show the purchase screen to the user

### Parameters

`activity` - activity used to as context for launch

`noAdsSku` -

optional parameter - no ads in-app purchase identifier (sku).
Use when it is multiple no-ads sku-s, because this takes current provided no ads sku from [noAdsInAppSkuList](../../pl.netigen.coreapi.payments/-i-no-ads/no-ads-in-app-sku-list.html).




For default it is [packageName](../../pl.netigen.coreapi.payments/-i-no-ads/package-name.html) +".noads"

