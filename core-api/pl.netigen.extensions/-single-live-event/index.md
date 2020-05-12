[core-api](../../index.md) / [pl.netigen.extensions](../index.md) / [SingleLiveEvent](./index.md)

# SingleLiveEvent

`abstract class SingleLiveEvent<T> : `[`LiveData`](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)`<T>`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `SingleLiveEvent(value: T)`<br>`SingleLiveEvent()` |

### Functions

| Name | Summary |
|---|---|
| [call](call.md) | `open fun call(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [observe](observe.md) | `open fun observe(owner: `[`LifecycleOwner`](https://developer.android.com/reference/kotlin/androidx/lifecycle/LifecycleOwner.html)`, observer: `[`Observer`](https://developer.android.com/reference/kotlin/androidx/lifecycle/Observer.html)`<in T>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [postValue](post-value.md) | `open fun postValue(value: T?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [removeObserver](remove-observer.md) | `fun removeObserver(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`open fun removeObserver(observer: `[`Observer`](https://developer.android.com/reference/kotlin/androidx/lifecycle/Observer.html)`<in T>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [setValue](set-value.md) | `open fun setValue(t: T?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [MutableSingleLiveEvent](../-mutable-single-live-event/index.md) | `open class MutableSingleLiveEvent<T> : `[`SingleLiveEvent`](./index.md)`<T>` |
