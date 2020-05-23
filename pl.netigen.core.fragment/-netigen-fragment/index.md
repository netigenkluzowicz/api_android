---
title: NetigenFragment - api_android
---

[api_android](../../index.html) / [pl.netigen.core.fragment](../index.html) / [NetigenFragment](./index.html)

# NetigenFragment

`open class NetigenFragment : `[`Fragment`](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html)

Base fragment, provides [canCommitFragments](can-commit-fragments.html)

### Constructors

| [&lt;init&gt;](-init-.html) | Base fragment, provides [canCommitFragments](can-commit-fragments.html)`NetigenFragment()` |

### Properties

| [canCommitFragments](can-commit-fragments.html) | Indicates if we can safe perform Fragment transaction as [commit()](https://developer.android.com/reference/kotlin/androidx/fragment/app/FragmentTransaction.html#commit()) or [popBackStack()](https://developer.android.com/reference/kotlin/androidx/fragment/app/FragmentManager.html#popBackStack()) and others otherwise it will result with [IllegalStateException](https://docs.oracle.com/javase/6/docs/api/java/lang/IllegalStateException.html) crash`val canCommitFragments: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

### Inheritors

| [CoreSplashFragment](../../pl.netigen.core.splash/-core-splash-fragment/index.html) | `abstract class CoreSplashFragment : `[`NetigenFragment`](./index.html)`, `[`ICoreSplashFragment`](../../pl.netigen.coreapi.gdpr/-i-core-splash-fragment.html) |
| [NetigenVMFragment](../-netigen-v-m-fragment/index.html) | Base fragment for Api, provides [canCommitFragments](can-commit-fragments.html), and [ICoreMainVM](../../pl.netigen.coreapi.main/-i-core-main-v-m/index.html)`open class NetigenVMFragment : `[`NetigenFragment`](./index.html) |

