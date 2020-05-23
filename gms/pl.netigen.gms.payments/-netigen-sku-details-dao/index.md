---
title: NetigenSkuDetailsDao - gms
---

[gms](../../index.md) / [pl.netigen.gms.payments](../index.md) / [NetigenSkuDetailsDao](./index.md)

# NetigenSkuDetailsDao

`interface NetigenSkuDetailsDao`

### Functions

| [getById](get-by-id.html) | `abstract fun getById(sku: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`): NetigenSkuDetails?` |
| [inAppSkuDetailsLiveData](in-app-sku-details-live-data.html) | `abstract fun inAppSkuDetailsLiveData(): `[`LiveData`](https://netigenkluzowicz.github.io/api_android/core/androidx/lifecycle/LiveData.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.md)`<NetigenSkuDetails>>` |
| [insert](insert.html) | `abstract fun insert(NetigenSkuDetails: NetigenSkuDetails): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [insertOrUpdate](insert-or-update.html) | `open fun insertOrUpdate(skuDetails: SkuDetails, isNoAds: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)` = false): SkuDetails`<br>`open fun insertOrUpdate(sku: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`, isNoAds: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)` = false): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [subscriptionSkuDetailsLiveData](subscription-sku-details-live-data.html) | `abstract fun subscriptionSkuDetailsLiveData(): `[`LiveData`](https://netigenkluzowicz.github.io/api_android/core/androidx/lifecycle/LiveData.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.md)`<NetigenSkuDetails>>` |
| [update](update.html) | `abstract fun update(sku: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`, isNoAds: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |

