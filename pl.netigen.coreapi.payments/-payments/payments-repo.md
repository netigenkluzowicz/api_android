[core-api](../../index.md) / [pl.netigen.coreapi.payments](../index.md) / [Payments](index.md) / [paymentsRepo](./payments-repo.md)

# paymentsRepo

`abstract val paymentsRepo: `[`IPaymentsRepo`](../-i-payments-repo/index.md)

Current [IPaymentsRepo](../-i-payments-repo/index.md) implementation,
current design uses [androidx.room.Room](https://developer.android.com/reference/kotlin/androidx/room/Room.html) database + [Flow](#)/[LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html) for observing purchases data

