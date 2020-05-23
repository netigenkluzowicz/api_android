---
title: LanguagesRecyclerViewAdapter - core
---

[core](../../index.html) / [pl.netigen.core.language](../index.html) / [LanguagesRecyclerViewAdapter](./index.html)

# LanguagesRecyclerViewAdapter

`open class LanguagesRecyclerViewAdapter : `[`Adapter`](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView/Adapter.html)`<LanguageRowViewHolder!>`

### Types

| [LanguageRowViewHolder](-language-row-view-holder/index.html) | `open class LanguageRowViewHolder : `[`ViewHolder`](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView/ViewHolder.html) |

### Constructors

| [&lt;init&gt;](-init-.html) | `LanguagesRecyclerViewAdapter(languageModelArrayList: `[`ArrayList`](https://docs.oracle.com/javase/6/docs/api/java/util/ArrayList.html)`<`[`LanguageModel`](../-language-model/index.html)`!>!, typeface: `[`Typeface`](https://developer.android.com/reference/android/graphics/Typeface.html)`!)` |

### Functions

| [getItemCount](get-item-count.html) | `open fun getItemCount(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getItemId](get-item-id.html) | `open fun getItemId(position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [getSelectedItem](get-selected-item.html) | `open fun getSelectedItem(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`!` |
| [onBindViewHolder](on-bind-view-holder.html) | `open fun onBindViewHolder(holder: LanguageRowViewHolder, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onCreateViewHolder](on-create-view-holder.html) | `open fun onCreateViewHolder(parent: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`, viewType: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): LanguageRowViewHolder` |

