---
title: NetigenFragment - core
---

[core](../../index.md) / [pl.netigen.core.fragment](../index.md) / [NetigenFragment](./index.md)

# NetigenFragment

`open class NetigenFragment : `[`Fragment`](https://netigenkluzowicz.github.io/api_android/gms/androidx/fragment/app/Fragment.md))

Base fragment, provides [canCommitFragments](can-commit-fragments.md))

### Constructors

| [&lt;init&gt;](-init-.md)) | Base fragment, provides [canCommitFragments](can-commit-fragments.md))`NetigenFragment()` |

### Properties

| [canCommitFragments](can-commit-fragments.md)) | Indicates if we can safe perform Fragment transaction as [commit()](https://netigenkluzowicz.github.io/api_android/gms/androidx/fragment/app/FragmentTransaction.md)#commit()) or [popBackStack()](https://netigenkluzowicz.github.io/api_android/gms/androidx/fragment/app/FragmentManager.md)#popBackStack()) and others otherwise it will result with [IllegalStateException](https://docs.oracle.com/javase/6/docs/api/java/lang/IllegalStateException.md)) crash`val canCommitFragments: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |

### Inheritors

| [CoreSplashFragment](../../pl.netigen.core.splash/-core-splash-fragment/index.md) | `abstract class CoreSplashFragment : `[`NetigenFragment`](./index.md)`, ICoreSplashFragment` |
| [NetigenVMFragment](../-netigen-v-m-fragment/index.md) | Base fragment for Api, provides [canCommitFragments](can-commit-fragments.md)), and [ICoreMainVM](#)`open class NetigenVMFragment : `[`NetigenFragment`](./index.md) |

