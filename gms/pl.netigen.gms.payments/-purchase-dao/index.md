---
title: PurchaseDao - gms
---

[api_android](../index.md)(../index.md)/[gms](../../index.md) / [pl.netigen.gms.payments](../index.md) / [PurchaseDao](./index.md)

# PurchaseDao

`interface PurchaseDao`

### Functions

| [delete](delete.html) | `abstract suspend fun delete(vararg purchases: `[`CachedPurchase`](../-cached-purchase/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md)<br>`abstract suspend fun delete(purchase: Purchase): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [deleteAll](delete-all.html) | `abstract suspend fun deleteAll(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [get](get.html) | `abstract suspend fun get(purchase: Purchase): `[`CachedPurchase`](../-cached-purchase/index.md)`?` |
| [getPurchasesFlow](get-purchases-flow.html) | `abstract fun getPurchasesFlow(): Flow<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.md)`<`[`CachedPurchase`](../-cached-purchase/index.md)`>>` |
| [insert](insert.html) | `abstract suspend fun insert(purchase: `[`CachedPurchase`](../-cached-purchase/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md)<br>`open suspend fun insert(vararg purchases: Purchase): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |

