---
title: GDPRDialogFragment - core
---

[core](../../index.md) / [pl.netigen.core.gdpr](../index.md) / [GDPRDialogFragment](./index.md)

# GDPRDialogFragment

`class GDPRDialogFragment : `[`NetigenDialogFragment`](../../pl.netigen.core.fragment/-netigen-dialog-fragment/index.md)

Fragment for showing GDPR user consent, see [IGDPRConsent](#)

### Constructors

| [&lt;init&gt;](-init-.md)) | Fragment for showing GDPR user consent, see [IGDPRConsent](#)`GDPRDialogFragment()` |

### Functions

| [bindGDPRListener](bind-g-d-p-r-listener.md)) | `fun bindGDPRListener(gdprClickListener: GDPRClickListener): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [onCreateView](on-create-view.md)) | `fun onCreateView(inflater: `[`LayoutInflater`](https://developer.android.com/reference/android/view/LayoutInflater.md))`, container: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.md))`?, savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.md))`?): `[`View`](https://developer.android.com/reference/android/view/View.md))`?` |
| [onDetach](on-detach.md)) | `fun onDetach(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [onStart](on-start.md)) | `fun onStart(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [onViewCreated](on-view-created.md)) | `fun onViewCreated(view: `[`View`](https://developer.android.com/reference/android/view/View.md))`, savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.md))`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [setIsPayOptions](set-is-pay-options.md)) | `fun setIsPayOptions(isNoAdsAvailable: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |

### Companion Object Functions

| [newInstance](new-instance.md)) | `fun newInstance(): `[`GDPRDialogFragment`](./index.md) |

