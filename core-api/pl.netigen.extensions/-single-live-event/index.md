---
title: SingleLiveEvent - core-api
---

[home page](../index.md)/[core-api](../../index.md) / [pl.netigen.extensions](../index.md) / [SingleLiveEvent](./index.md)

# SingleLiveEvent

`abstract class SingleLiveEvent<T> : `[`LiveData`](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)`<T>`

### Constructors

| [&lt;init&gt;](-init-.html) | `SingleLiveEvent(value: T)`<br>`SingleLiveEvent()` |

### Functions

| [call](call.html) | `open fun call(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [observe](observe.html) | `open fun observe(owner: `[`LifecycleOwner`](https://developer.android.com/reference/kotlin/androidx/lifecycle/LifecycleOwner.html)`, observer: `[`Observer`](https://developer.android.com/reference/kotlin/androidx/lifecycle/Observer.html)`<in T>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [postValue](post-value.html) | `open fun postValue(value: T?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [removeObserver](remove-observer.html) | `fun removeObserver(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md)<br>`open fun removeObserver(observer: `[`Observer`](https://developer.android.com/reference/kotlin/androidx/lifecycle/Observer.html)`<in T>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [setValue](set-value.html) | `open fun setValue(t: T?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |

### Inheritors

| [MutableSingleLiveEvent](../-mutable-single-live-event/index.md) | `open class MutableSingleLiveEvent<T> : `[`SingleLiveEvent`](./index.md)`<T>` |

