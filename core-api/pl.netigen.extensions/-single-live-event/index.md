---
title: SingleLiveEvent - core-api
---

[core-api](../../index.md) / [pl.netigen.extensions](../index.md) / [SingleLiveEvent](./index.md)

# SingleLiveEvent

`abstract class SingleLiveEvent<T> : `[`LiveData`](https://netigenkluzowicz.github.io/api_android/gms/androidx/lifecycle/LiveData.md))`<T>`

### Constructors

| [&lt;init&gt;](-init-.md)) | `SingleLiveEvent(value: T)`<br>`SingleLiveEvent()` |

### Functions

| [call](call.md)) | `open fun call(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [observe](observe.md)) | `open fun observe(owner: `[`LifecycleOwner`](https://netigenkluzowicz.github.io/api_android/gms/androidx/lifecycle/LifecycleOwner.md))`, observer: `[`Observer`](https://netigenkluzowicz.github.io/api_android/gms/androidx/lifecycle/Observer.md))`<in T>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [postValue](post-value.md)) | `open fun postValue(value: T?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [removeObserver](remove-observer.md)) | `fun removeObserver(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md)<br>`open fun removeObserver(observer: `[`Observer`](https://netigenkluzowicz.github.io/api_android/gms/androidx/lifecycle/Observer.md))`<in T>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [setValue](set-value.md)) | `open fun setValue(t: T?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |

### Inheritors

| [MutableSingleLiveEvent](../-mutable-single-live-event/index.md) | `open class MutableSingleLiveEvent<T> : `[`SingleLiveEvent`](./index.md)`<T>` |

