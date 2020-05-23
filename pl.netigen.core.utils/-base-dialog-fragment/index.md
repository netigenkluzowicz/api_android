---
title: BaseDialogFragment - api_android
---

[api_android](../../index.html) / [pl.netigen.core.utils](../index.html) / [BaseDialogFragment](./index.html)

# BaseDialogFragment

`abstract class BaseDialogFragment : `[`NetigenDialogFragment`](../../pl.netigen.core.fragment/-netigen-dialog-fragment/index.html)

see [NetigenDialogFragment](../../pl.netigen.core.fragment/-netigen-dialog-fragment/index.html)

### Constructors

| [&lt;init&gt;](-init-.html) | see [NetigenDialogFragment](../../pl.netigen.core.fragment/-netigen-dialog-fragment/index.html)`BaseDialogFragment()` |

### Functions

| [onStart](on-start.html) | `open fun onStart(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onViewCreated](on-view-created.html) | `open fun onViewCreated(view: View, savedInstanceState: Bundle?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [setDialogSize](set-dialog-size.html) | `fun setDialogSize(dp: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| [ChangeLanguageDialogFragment](../../pl.netigen.core.language/-change-language-dialog-fragment/index.html) | Dialog for show user possibility to change language of application`open class ChangeLanguageDialogFragment : `[`BaseDialogFragment`](./index.html) |
| [RateFragment](../../pl.netigen.core.rateus/-rate-fragment/index.html) | [BaseDialogFragment](./index.html) used for show users "Rate us" dialog, see [IRateUs](../../pl.netigen.coreapi.rateus/-i-rate-us/index.html)`class RateFragment : `[`BaseDialogFragment`](./index.html) |
| [TranslationInfoDialogFragment](../../pl.netigen.core.language.info/-translation-info-dialog-fragment/index.html) | `open class TranslationInfoDialogFragment : `[`BaseDialogFragment`](./index.html) |

