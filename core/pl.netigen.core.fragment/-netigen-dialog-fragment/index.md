---
title: NetigenDialogFragment - core
---

[core](../../index.md) / [pl.netigen.core.fragment](../index.md) / [NetigenDialogFragment](./index.md)

# NetigenDialogFragment

`open class NetigenDialogFragment : `[`AppCompatDialogFragment`](https://netigenkluzowicz.github.io/api_android/gms/androidx/appcompat/app/AppCompatDialogFragment.md))

Base [AppCompatDialogFragment](https://netigenkluzowicz.github.io/api_android/gms/androidx/appcompat/app/AppCompatDialogFragment.md)) for Api, provides [canCommitFragments](can-commit-fragments.md)), and [ICoreMainVM](#)

### Constructors

| [&lt;init&gt;](-init-.md)) | Base [AppCompatDialogFragment](https://netigenkluzowicz.github.io/api_android/gms/androidx/appcompat/app/AppCompatDialogFragment.md)) for Api, provides [canCommitFragments](can-commit-fragments.md)), and [ICoreMainVM](#)`NetigenDialogFragment()` |

### Properties

| [canCommitFragments](can-commit-fragments.md)) | Indicates if we can safe perform Fragment transaction as [commit()](https://netigenkluzowicz.github.io/api_android/gms/androidx/fragment/app/FragmentTransaction.md)#commit()) or [popBackStack()](https://netigenkluzowicz.github.io/api_android/gms/androidx/fragment/app/FragmentManager.md)#popBackStack()) and others otherwise it will result with [IllegalStateException](https://docs.oracle.com/javase/6/docs/api/java/lang/IllegalStateException.md)) crash`val canCommitFragments: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md) |
| [viewModel](view-model.md)) | Provides access to Api by [ICoreMainVM](#)`val viewModel: ICoreMainVM` |

### Inheritors

| [BaseDialogFragment](../../pl.netigen.core.utils/-base-dialog-fragment/index.md) | see [NetigenDialogFragment](./index.md)`abstract class BaseDialogFragment : `[`NetigenDialogFragment`](./index.md) |
| [GDPRDialogFragment](../../pl.netigen.core.gdpr/-g-d-p-r-dialog-fragment/index.md) | Fragment for showing GDPR user consent, see [IGDPRConsent](#)`class GDPRDialogFragment : `[`NetigenDialogFragment`](./index.md) |

