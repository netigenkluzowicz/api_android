---
title: SingleLiveEvent - api_android
---

[api_android](../../index.html) / [pl.netigen.extensions](../index.html) / [SingleLiveEvent](./index.html)

# SingleLiveEvent

`abstract class SingleLiveEvent<T> : `[`LiveData`](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)`<T>`

### Constructors

| [&lt;init&gt;](-init-.html) | `SingleLiveEvent(value: T)`<br>`SingleLiveEvent()` |

### Functions

| [call](call.html) | `open fun call(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [observe](observe.html) | `open fun observe(owner: `[`LifecycleOwner`](https://developer.android.com/reference/kotlin/androidx/lifecycle/LifecycleOwner.html)`, observer: `[`Observer`](https://developer.android.com/reference/kotlin/androidx/lifecycle/Observer.html)`<in T>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [postValue](post-value.html) | `open fun postValue(value: T?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [removeObserver](remove-observer.html) | `fun removeObserver(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`open fun removeObserver(observer: `[`Observer`](https://developer.android.com/reference/kotlin/androidx/lifecycle/Observer.html)`<in T>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [setValue](set-value.html) | `open fun setValue(t: T?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| [MutableSingleLiveEvent](../-mutable-single-live-event/index.html) | `open class MutableSingleLiveEvent<T> : `[`SingleLiveEvent`](./index.html)`<T>` |

