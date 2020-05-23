---
title: NetigenDialogFragment - api_android
---

[api_android](../../index.html) / [pl.netigen.core.fragment](../index.html) / [NetigenDialogFragment](./index.html)

# NetigenDialogFragment

`open class NetigenDialogFragment : `[`AppCompatDialogFragment`](https://developer.android.com/reference/kotlin/androidx/appcompat/app/AppCompatDialogFragment.html)

Base [AppCompatDialogFragment](https://developer.android.com/reference/kotlin/androidx/appcompat/app/AppCompatDialogFragment.html) for Api, provides [canCommitFragments](can-commit-fragments.html), and [ICoreMainVM](../../pl.netigen.coreapi.main/-i-core-main-v-m/index.html)

### Constructors

| [&lt;init&gt;](-init-.html) | Base [AppCompatDialogFragment](https://developer.android.com/reference/kotlin/androidx/appcompat/app/AppCompatDialogFragment.html) for Api, provides [canCommitFragments](can-commit-fragments.html), and [ICoreMainVM](../../pl.netigen.coreapi.main/-i-core-main-v-m/index.html)`NetigenDialogFragment()` |

### Properties

| [canCommitFragments](can-commit-fragments.html) | Indicates if we can safe perform Fragment transaction as [commit()](https://developer.android.com/reference/kotlin/androidx/fragment/app/FragmentTransaction.html#commit()) or [popBackStack()](https://developer.android.com/reference/kotlin/androidx/fragment/app/FragmentManager.html#popBackStack()) and others otherwise it will result with [IllegalStateException](https://docs.oracle.com/javase/6/docs/api/java/lang/IllegalStateException.html) crash`val canCommitFragments: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [viewModel](view-model.html) | Provides access to Api by [ICoreMainVM](../../pl.netigen.coreapi.main/-i-core-main-v-m/index.html)`val viewModel: `[`ICoreMainVM`](../../pl.netigen.coreapi.main/-i-core-main-v-m/index.html) |

### Inheritors

| [BaseDialogFragment](../../pl.netigen.core.utils/-base-dialog-fragment/index.html) | see [NetigenDialogFragment](./index.html)`abstract class BaseDialogFragment : `[`NetigenDialogFragment`](./index.html) |
| [GDPRDialogFragment](../../pl.netigen.core.gdpr/-g-d-p-r-dialog-fragment/index.html) | Fragment for showing GDPR user consent, see [IGDPRConsent](../../pl.netigen.coreapi.gdpr/-i-g-d-p-r-consent/index.html)`class GDPRDialogFragment : `[`NetigenDialogFragment`](./index.html) |

