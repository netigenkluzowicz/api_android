---
title: DistinctObservableProperty - api_android
---

[api_android](../../index.html) / [pl.netigen.extensions](../index.html) / [DistinctObservableProperty](./index.html)

# DistinctObservableProperty

`abstract class DistinctObservableProperty<T> : `[`ReadWriteProperty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-write-property/index.html)`<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?, T>`

### Constructors

| [&lt;init&gt;](-init-.html) | `DistinctObservableProperty(initialValue: T)` |

### Functions

| [getValue](get-value.html) | `open fun getValue(thisRef: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?, property: `[`KProperty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-property/index.html)`<*>): T` |
| [onDistinctNewValue](on-distinct-new-value.html) | `open fun onDistinctNewValue(newValue: T): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [setValue](set-value.html) | `open fun setValue(thisRef: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?, property: `[`KProperty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-property/index.html)`<*>, value: T): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

