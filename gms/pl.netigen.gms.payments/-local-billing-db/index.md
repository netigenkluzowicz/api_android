---
title: LocalBillingDb - gms
---

[gms](../../index.html) / [pl.netigen.gms.payments](../index.html) / [LocalBillingDb](./index.html)

# LocalBillingDb

`abstract class LocalBillingDb : `[`RoomDatabase`](https://developer.android.com/reference/kotlin/androidx/room/RoomDatabase.html)

### Constructors

| [&lt;init&gt;](-init-.html) | `LocalBillingDb()` |

### Functions

| [purchaseDao](purchase-dao.html) | `abstract fun purchaseDao(): `[`PurchaseDao`](../-purchase-dao/index.html) |
| [skuDetailsDao](sku-details-dao.html) | `abstract fun skuDetailsDao(): `[`NetigenSkuDetailsDao`](../-netigen-sku-details-dao/index.html) |

### Companion Object Functions

| [getInstance](get-instance.html) | `fun getInstance(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`): `[`LocalBillingDb`](./index.html) |

