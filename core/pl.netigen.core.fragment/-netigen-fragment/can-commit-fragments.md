---
title: NetigenFragment.canCommitFragments - core
---

[core](../../index.md) / [pl.netigen.core.fragment](../index.md) / [NetigenFragment](index.md) / [canCommitFragments](./can-commit-fragments.md))

# canCommitFragments

`val canCommitFragments: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)

Indicates if we can safe perform Fragment transaction
as [commit()](https://netigenkluzowicz.github.io/api_android/gms/androidx/fragment/app/FragmentTransaction.md)#commit()) or [popBackStack()](https://netigenkluzowicz.github.io/api_android/gms/androidx/fragment/app/FragmentManager.md)#popBackStack()) and others
otherwise it will result with
[IllegalStateException](https://docs.oracle.com/javase/6/docs/api/java/lang/IllegalStateException.md)) crash

see: [FragmentManager.isStateSaved](https://netigenkluzowicz.github.io/api_android/gms/androidx/fragment/app/FragmentManager.md)#isStateSaved())

see: [stackoverflow](https://stackoverflow.com/a/44064149/3442734)

