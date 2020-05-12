[core-api](../../index.md) / [pl.netigen.coreapi.payments](../index.md) / [INoAds](index.md) / [makeNoAdsPayment](./make-no-ads-payment.md)

# makeNoAdsPayment

`abstract fun makeNoAdsPayment(activity: `[`Activity`](https://developer.android.com/reference/android/app/Activity.html)`, noAdsSku: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "${packageName}.noads"): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Makes call to current payment implementation to launch billing flow for an no ads in-app purchase.
It will show the purchase screen to the user

### Parameters

`activity` - activity used to as context for launch

`noAdsSku` -

optional parameter - no ads in-app purchase identifier (sku).
Use when it is multiple no-ads sku-s, because this takes current provided no ads sku from [noAdsInAppSkuList](no-ads-in-app-sku-list.md).




For default it is [packageName](package-name.md) +".noads"

