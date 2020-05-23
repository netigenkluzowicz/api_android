---
title: ICoreMainActivity.canCommitFragments - api_android
---

[api_android](../../index.html) / [pl.netigen.coreapi.main](../index.html) / [ICoreMainActivity](index.html) / [canCommitFragments](./can-commit-fragments.html)

# canCommitFragments

`abstract val canCommitFragments: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Indicates if we can safe perform Fragment transaction
as [commit()](https://developer.android.com/reference/kotlin/androidx/fragment/app/FragmentTransaction.html#commit()) or [popBackStack()](https://developer.android.com/reference/kotlin/androidx/fragment/app/FragmentManager.html#popBackStack()) and others
otherwise it will result with
[IllegalStateException](https://docs.oracle.com/javase/6/docs/api/java/lang/IllegalStateException.html) crash

see: [FragmentManager.isStateSaved](https://developer.android.com/reference/kotlin/androidx/fragment/app/FragmentManager.html#isStateSaved())

see: [stackoverflow](https://stackoverflow.com/a/44064149/3442734)

