---
title: NetigenSkuDetails - core-api
---

[api_android](../index.md)/[core-api](../../index.md)/[pl.netigen.coreapi.payments.model](../index.md)/[NetigenSkuDetails](./index.md)

# NetigenSkuDetails

`data class NetigenSkuDetails`

Represents an in-app product's or subscription's  details.

### Constructors

| [&lt;init&gt;](-init-.html) | Represents an in-app product's or subscription's  details.`NetigenSkuDetails(sku: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`, type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`? = null, isNoAds: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)`, price: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`? = null, title: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`? = null, description: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`? = null, originalJson: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`? = null)` |

### Properties

| [description](description.html) | Description of the product`val description: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`?` |
| [isNoAds](is-no-ads.html) | Indicates if this product turns off ads in app`val isNoAds: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |
| [originalJson](original-json.html) | String in JSON format that contains Sku details`val originalJson: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`?` |
| [price](price.html) | Formatted price of the item, including its currency sign.`val price: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`?` |
| [sku](sku.html) | This product Id`val sku: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) |
| [title](title.html) | Title of the product`val title: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`?` |
| [type](type.html) | SKU type (consumable, non-consumable, subscription)`val type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`?` |

