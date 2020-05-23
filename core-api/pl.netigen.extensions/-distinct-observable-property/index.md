---
title: DistinctObservableProperty - core-api
---

[api_android](../index.md)(../index.md)/[core-api](../../index.md) / [pl.netigen.extensions](../index.md) / [DistinctObservableProperty](./index.md)

# DistinctObservableProperty

`abstract class DistinctObservableProperty<T> : `[`ReadWriteProperty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-write-property/index.md)`<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.md)`?, T>`

### Constructors

| [&lt;init&gt;](-init-.html) | `DistinctObservableProperty(initialValue: T)` |

### Functions

| [getValue](get-value.html) | `open fun getValue(thisRef: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.md)`?, property: `[`KProperty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-property/index.md)`<*>): T` |
| [onDistinctNewValue](on-distinct-new-value.html) | `open fun onDistinctNewValue(newValue: T): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [setValue](set-value.html) | `open fun setValue(thisRef: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.md)`?, property: `[`KProperty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-property/index.md)`<*>, value: T): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |

