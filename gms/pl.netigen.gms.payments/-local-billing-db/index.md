---
title: LocalBillingDb - gms
---

[api_android](../index.md)/[gms](../../index.md) / [pl.netigen.gms.payments](../index.md) / [LocalBillingDb](./index.md)

# LocalBillingDb

`abstract class LocalBillingDb : `[`RoomDatabase`](https://developer.android.com/reference/kotlin/androidx/room/RoomDatabase.html)

### Constructors

| [&lt;init&gt;](-init-.html) | `LocalBillingDb()` |

### Functions

| [purchaseDao](purchase-dao.html) | `abstract fun purchaseDao(): `[`PurchaseDao`](../-purchase-dao/index.md) |
| [skuDetailsDao](sku-details-dao.html) | `abstract fun skuDetailsDao(): `[`NetigenSkuDetailsDao`](../-netigen-sku-details-dao/index.md) |

### Companion Object Functions

| [getInstance](get-instance.html) | `fun getInstance(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`): `[`LocalBillingDb`](./index.md) |

