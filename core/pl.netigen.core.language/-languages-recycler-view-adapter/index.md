---
title: LanguagesRecyclerViewAdapter - core
---

[core](../../index.md) / [pl.netigen.core.language](../index.md) / [LanguagesRecyclerViewAdapter](./index.md)

# LanguagesRecyclerViewAdapter

`open class LanguagesRecyclerViewAdapter : `[`Adapter`](https://netigenkluzowicz.github.io/api_android/gms/androidx/recyclerview/widget/RecyclerView/Adapter.md))`<LanguageRowViewHolder!>`

### Types

| [LanguageRowViewHolder](-language-row-view-holder/index.md) | `open class LanguageRowViewHolder : `[`ViewHolder`](https://netigenkluzowicz.github.io/api_android/gms/androidx/recyclerview/widget/RecyclerView/ViewHolder.md)) |

### Constructors

| [&lt;init&gt;](-init-.md)) | `LanguagesRecyclerViewAdapter(languageModelArrayList: `[`ArrayList`](https://docs.oracle.com/javase/6/docs/api/java/util/ArrayList.md))`<`[`LanguageModel`](../-language-model/index.md)`!>!, typeface: `[`Typeface`](https://developer.android.com/reference/android/graphics/Typeface.md))`!)` |

### Functions

| [getItemCount](get-item-count.md)) | `open fun getItemCount(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md) |
| [getItemId](get-item-id.md)) | `open fun getItemId(position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md)`): `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.md) |
| [getSelectedItem](get-selected-item.md)) | `open fun getSelectedItem(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`!` |
| [onBindViewHolder](on-bind-view-holder.md)) | `open fun onBindViewHolder(holder: LanguageRowViewHolder, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [onCreateViewHolder](on-create-view-holder.md)) | `open fun onCreateViewHolder(parent: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.md))`, viewType: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md)`): LanguageRowViewHolder` |

