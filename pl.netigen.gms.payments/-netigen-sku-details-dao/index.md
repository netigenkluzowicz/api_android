---
title: NetigenSkuDetailsDao - api_android
---

[api_android](../../index.html) / [pl.netigen.gms.payments](../index.html) / [NetigenSkuDetailsDao](./index.html)

# NetigenSkuDetailsDao

`interface NetigenSkuDetailsDao`

### Functions

| [getById](get-by-id.html) | `abstract fun getById(sku: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`NetigenSkuDetails`](../../pl.netigen.coreapi.payments.model/-netigen-sku-details/index.html)`?` |
| [inAppSkuDetailsLiveData](in-app-sku-details-live-data.html) | `abstract fun inAppSkuDetailsLiveData(): `[`LiveData`](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`NetigenSkuDetails`](../../pl.netigen.coreapi.payments.model/-netigen-sku-details/index.html)`>>` |
| [insert](insert.html) | `abstract fun insert(NetigenSkuDetails: `[`NetigenSkuDetails`](../../pl.netigen.coreapi.payments.model/-netigen-sku-details/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [insertOrUpdate](insert-or-update.html) | `open fun insertOrUpdate(skuDetails: SkuDetails, isNoAds: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false): SkuDetails`<br>`open fun insertOrUpdate(sku: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, isNoAds: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [subscriptionSkuDetailsLiveData](subscription-sku-details-live-data.html) | `abstract fun subscriptionSkuDetailsLiveData(): `[`LiveData`](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`NetigenSkuDetails`](../../pl.netigen.coreapi.payments.model/-netigen-sku-details/index.html)`>>` |
| [update](update.html) | `abstract fun update(sku: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, isNoAds: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

