---
title: BaseDialogFragment - core
---

[core](../../index.md) / [pl.netigen.core.utils](../index.md) / [BaseDialogFragment](./index.md)

# BaseDialogFragment

`abstract class BaseDialogFragment : `[`NetigenDialogFragment`](../../pl.netigen.core.fragment/-netigen-dialog-fragment/index.md)

see [NetigenDialogFragment](../../pl.netigen.core.fragment/-netigen-dialog-fragment/index.md)

### Constructors

| [&lt;init&gt;](-init-.md)) | see [NetigenDialogFragment](../../pl.netigen.core.fragment/-netigen-dialog-fragment/index.md)`BaseDialogFragment()` |

### Functions

| [onStart](on-start.md)) | `open fun onStart(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [onViewCreated](on-view-created.md)) | `open fun onViewCreated(view: `[`View`](https://developer.android.com/reference/android/view/View.md))`, savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.md))`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [setDialogSize](set-dialog-size.md)) | `fun setDialogSize(dp: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |

### Inheritors

| [ChangeLanguageDialogFragment](../../pl.netigen.core.language/-change-language-dialog-fragment/index.md) | Dialog for show user possibility to change language of application`open class ChangeLanguageDialogFragment : `[`BaseDialogFragment`](./index.md) |
| [RateFragment](../../pl.netigen.core.rateus/-rate-fragment/index.md) | [BaseDialogFragment](./index.md) used for show users "Rate us" dialog, see [IRateUs](#)`class RateFragment : `[`BaseDialogFragment`](./index.md) |
| [TranslationInfoDialogFragment](../../pl.netigen.core.language.info/-translation-info-dialog-fragment/index.md) | `open class TranslationInfoDialogFragment : `[`BaseDialogFragment`](./index.md) |

