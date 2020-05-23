---
title: GDPRDialogFragment - core
---

[home page](../index.md)/[core](../../index.md) / [pl.netigen.core.gdpr](../index.md) / [GDPRDialogFragment](./index.md)

# GDPRDialogFragment

`class GDPRDialogFragment : `[`NetigenDialogFragment`](../../pl.netigen.core.fragment/-netigen-dialog-fragment/index.md)

Fragment for showing GDPR user consent, see [IGDPRConsent](#)

### Constructors

| [&lt;init&gt;](-init-.html) | Fragment for showing GDPR user consent, see [IGDPRConsent](#)`GDPRDialogFragment()` |

### Functions

| [bindGDPRListener](bind-g-d-p-r-listener.html) | `fun bindGDPRListener(gdprClickListener: GDPRClickListener): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [onCreateView](on-create-view.html) | `fun onCreateView(inflater: `[`LayoutInflater`](https://developer.android.com/reference/android/view/LayoutInflater.html)`, container: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`?, savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`View`](https://developer.android.com/reference/android/view/View.html)`?` |
| [onDetach](on-detach.html) | `fun onDetach(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [onStart](on-start.html) | `fun onStart(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [onViewCreated](on-view-created.html) | `fun onViewCreated(view: `[`View`](https://developer.android.com/reference/android/view/View.html)`, savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [setIsPayOptions](set-is-pay-options.html) | `fun setIsPayOptions(isNoAdsAvailable: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |

### Companion Object Functions

| [newInstance](new-instance.html) | `fun newInstance(): `[`GDPRDialogFragment`](./index.md) |

