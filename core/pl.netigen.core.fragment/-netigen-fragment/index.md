---
title: NetigenFragment - core
---

[core](../../index.md) / [pl.netigen.core.fragment](../index.md) / [NetigenFragment](./index.md)

# NetigenFragment

`open class NetigenFragment : `[`Fragment`](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html)

Base fragment, provides [canCommitFragments](can-commit-fragments.html)

### Constructors

| [&lt;init&gt;](-init-.html) | Base fragment, provides [canCommitFragments](can-commit-fragments.html)`NetigenFragment()` |

### Properties

| [canCommitFragments](can-commit-fragments.html) | Indicates if we can safe perform Fragment transaction as [commit()](https://developer.android.com/reference/kotlin/androidx/fragment/app/FragmentTransaction.html#commit()) or [popBackStack()](https://developer.android.com/reference/kotlin/androidx/fragment/app/FragmentManager.html#popBackStack()) and others otherwise it will result with [IllegalStateException](https://docs.oracle.com/javase/6/docs/api/java/lang/IllegalStateException.html) crash`val canCommitFragments: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |

### Inheritors

| [CoreSplashFragment](../../pl.netigen.core.splash/-core-splash-fragment/index.md) | `abstract class CoreSplashFragment : `[`NetigenFragment`](./index.md)`, ICoreSplashFragment` |
| [NetigenVMFragment](../-netigen-v-m-fragment/index.md) | Base fragment for Api, provides [canCommitFragments](can-commit-fragments.html), and [ICoreMainVM](#)`open class NetigenVMFragment : `[`NetigenFragment`](./index.md) |

