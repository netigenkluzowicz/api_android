---
title: PurchaseDao - gms
---

[gms](../../index.html) / [pl.netigen.gms.payments](../index.html) / [PurchaseDao](./index.html)

# PurchaseDao

`interface PurchaseDao`

### Functions

| [delete](delete.html) | `abstract suspend fun delete(vararg purchases: `[`CachedPurchase`](../-cached-purchase/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`abstract suspend fun delete(purchase: Purchase): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [deleteAll](delete-all.html) | `abstract suspend fun deleteAll(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [get](get.html) | `abstract suspend fun get(purchase: Purchase): `[`CachedPurchase`](../-cached-purchase/index.html)`?` |
| [getPurchasesFlow](get-purchases-flow.html) | `abstract fun getPurchasesFlow(): Flow<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`CachedPurchase`](../-cached-purchase/index.html)`>>` |
| [insert](insert.html) | `abstract suspend fun insert(purchase: `[`CachedPurchase`](../-cached-purchase/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`open suspend fun insert(vararg purchases: Purchase): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

