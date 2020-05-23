---
title: Payments.paymentsRepo - core-api
---

[core-api](../../index.html) / [pl.netigen.coreapi.payments](../index.html) / [Payments](index.html) / [paymentsRepo](./payments-repo.html)

# paymentsRepo

`abstract val paymentsRepo: `[`IPaymentsRepo`](../-i-payments-repo/index.html)

Current [IPaymentsRepo](../-i-payments-repo/index.html) implementation,
current design uses [androidx.room.Room](https://developer.android.com/reference/kotlin/androidx/room/Room.html) database + [Flow](#)/[LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html) for observing purchases data

