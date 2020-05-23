---
title: LanguagesRecyclerViewAdapter - core
---

[api_android](../index.md)/[core](../../index.md) / [pl.netigen.core.language](../index.md) / [LanguagesRecyclerViewAdapter](./index.md)

# LanguagesRecyclerViewAdapter

`open class LanguagesRecyclerViewAdapter : `[`Adapter`](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView/Adapter.html)`<LanguageRowViewHolder!>`

### Types

| [LanguageRowViewHolder](-language-row-view-holder/index.md) | `open class LanguageRowViewHolder : `[`ViewHolder`](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView/ViewHolder.html) |

### Constructors

| [&lt;init&gt;](-init-.html) | `LanguagesRecyclerViewAdapter(languageModelArrayList: `[`ArrayList`](https://docs.oracle.com/javase/6/docs/api/java/util/ArrayList.html)`<`[`LanguageModel`](../-language-model/index.md)`!>!, typeface: `[`Typeface`](https://developer.android.com/reference/android/graphics/Typeface.html)`!)` |

### Functions

| [getItemCount](get-item-count.html) | `open fun getItemCount(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md) |
| [getItemId](get-item-id.html) | `open fun getItemId(position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md)`): `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.md) |
| [getSelectedItem](get-selected-item.html) | `open fun getSelectedItem(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`!` |
| [onBindViewHolder](on-bind-view-holder.html) | `open fun onBindViewHolder(holder: LanguageRowViewHolder, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [onCreateViewHolder](on-create-view-holder.html) | `open fun onCreateViewHolder(parent: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`, viewType: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.md)`): LanguageRowViewHolder` |

