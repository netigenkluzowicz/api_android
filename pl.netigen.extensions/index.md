---
title: pl.netigen.extensions - api_android
---

[api_android](../index.html) / [pl.netigen.extensions](./index.html)

## Package pl.netigen.extensions

### Types

| [DistinctObservableProperty](-distinct-observable-property/index.html) | `abstract class DistinctObservableProperty<T> : `[`ReadWriteProperty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-write-property/index.html)`<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?, T>` |
| [MutableSingleLiveEvent](-mutable-single-live-event/index.html) | `open class MutableSingleLiveEvent<T> : `[`SingleLiveEvent`](-single-live-event/index.html)`<T>` |
| [SingleLiveEvent](-single-live-event/index.html) | `abstract class SingleLiveEvent<T> : `[`LiveData`](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)`<T>` |

### Extensions for External Classes

| [android.graphics.drawable.Drawable](android.graphics.drawable.-drawable/index.html) |  |
| [android.view.View](android.view.-view/index.html) |  |
| [android.widget.ImageView](android.widget.-image-view/index.html) |  |
| [android.widget.SeekBar](android.widget.-seek-bar/index.html) |  |
| [androidx.fragment.app.DialogFragment](androidx.fragment.app.-dialog-fragment/index.html) |  |
| [androidx.fragment.app.Fragment](androidx.fragment.app.-fragment/index.html) |  |
| [androidx.lifecycle.LiveData](androidx.lifecycle.-live-data/index.html) |  |
| [androidx.lifecycle.ViewModel](androidx.lifecycle.-view-model/index.html) |  |
| [androidx.navigation.NavController](androidx.navigation.-nav-controller/index.html) |  |
| [kotlin.collections.Iterable](kotlin.collections.-iterable/index.html) |  |
| [kotlin.Int](kotlin.-int/index.html) |  |
| [kotlin.Pair](kotlin.-pair/index.html) |  |
| [kotlin.String](kotlin.-string/index.html) |  |

### Functions

| [distinctObservable](distinct-observable.html) | `fun <T> distinctObservable(initialValue: T, onChange: (newValue: T) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`ReadWriteProperty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-write-property/index.html)`<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?, T>` |
| [toJson](to-json.html) | `fun <T> T.toJson(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [validateIn](validate-in.html) | `fun <T : `[`Comparable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-comparable/index.html)`<T>> T.validateIn(values: `[`Iterable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/index.html)`<T>, name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [validateInRange](validate-in-range.html) | `fun <T : `[`Comparable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-comparable/index.html)`<T>> T.validateInRange(range: `[`ClosedRange`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-closed-range/index.html)`<T>, name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [validateNotSmaller](validate-not-smaller.html) | `fun <T : `[`Comparable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-comparable/index.html)`<T>> T.validateNotSmaller(limit: T, name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

