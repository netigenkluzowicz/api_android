---
title: LocalBillingDb - gms
---

[gms](../../index.md) / [pl.netigen.gms.payments](../index.md) / [LocalBillingDb](./index.md)

# LocalBillingDb

`abstract class LocalBillingDb : `[`RoomDatabase`](https://netigenkluzowicz.github.io/api_android/core/androidx/room/RoomDatabase.md))

### Constructors

| [&lt;init&gt;](-init-.md)) | `LocalBillingDb()` |

### Functions

| [purchaseDao](purchase-dao.md)) | `abstract fun purchaseDao(): `[`PurchaseDao`](../-purchase-dao/index.md) |
| [skuDetailsDao](sku-details-dao.md)) | `abstract fun skuDetailsDao(): `[`NetigenSkuDetailsDao`](../-netigen-sku-details-dao/index.md) |

### Companion Object Functions

| [getInstance](get-instance.md)) | `fun getInstance(context: `[`Context`](https://developer.android.com/reference/android/content/Context.md))`): `[`LocalBillingDb`](./index.md) |

