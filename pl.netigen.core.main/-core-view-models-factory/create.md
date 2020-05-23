---
title: CoreViewModelsFactory.create - api_android
---

[api_android](../../index.html) / [pl.netigen.core.main](../index.html) / [CoreViewModelsFactory](index.html) / [create](./create.html)

# create

`open fun <T : `[`ViewModel`](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel.html)`?> create(modelClass: `[`Class`](https://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<T>): T`

Creates a new instance of the given [SplashVM](../../pl.netigen.coreapi.splash/-splash-v-m/index.html) or [CoreMainVM](../../pl.netigen.coreapi.main/-core-main-v-m/index.html)

### Parameters

`modelClass` - a {@code Class} whose instance is requested

`T` - The type parameter for the ViewModel.

**Return**
a newly created [SplashVM](../../pl.netigen.coreapi.splash/-splash-v-m/index.html) or [CoreMainVM](../../pl.netigen.coreapi.main/-core-main-v-m/index.html)

