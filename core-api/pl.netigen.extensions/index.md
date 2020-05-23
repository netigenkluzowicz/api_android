---
title: pl.netigen.extensions - core-api
---

[core-api](../index.md) / [pl.netigen.extensions](./index.md)

## Package pl.netigen.extensions

### Types

| [DistinctObservableProperty](-distinct-observable-property/index.md) | `abstract class DistinctObservableProperty<T> : `[`ReadWriteProperty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-write-property/index.md)`<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.md)`?, T>` |
| [MutableSingleLiveEvent](-mutable-single-live-event/index.md) | `open class MutableSingleLiveEvent<T> : `[`SingleLiveEvent`](-single-live-event/index.md)`<T>` |
| [SingleLiveEvent](-single-live-event/index.md) | `abstract class SingleLiveEvent<T> : `[`LiveData`](https://netigenkluzowicz.github.io/api_android/gms/androidx/lifecycle/LiveData.md))`<T>` |

### Extensions for External Classes

| [android.graphics.drawable.Drawable](android.graphics.drawable.-drawable/index.md) |  |
| [android.view.View](android.view.-view/index.md) |  |
| [android.widget.ImageView](android.widget.-image-view/index.md) |  |
| [android.widget.SeekBar](android.widget.-seek-bar/index.md) |  |
| [androidx.fragment.app.DialogFragment](androidx.fragment.app.-dialog-fragment/index.md) |  |
| [androidx.fragment.app.Fragment](androidx.fragment.app.-fragment/index.md) |  |
| [androidx.lifecycle.LiveData](androidx.lifecycle.-live-data/index.md) |  |
| [androidx.lifecycle.ViewModel](androidx.lifecycle.-view-model/index.md) |  |
| [androidx.navigation.NavController](androidx.navigation.-nav-controller/index.md) |  |
| [kotlin.collections.Iterable](kotlin.collections.-iterable/index.md) |  |
| [kotlin.Int](kotlin.-int/index.md) |  |
| [kotlin.Pair](kotlin.-pair/index.md) |  |
| [kotlin.String](kotlin.-string/index.md) |  |

### Functions

| [distinctObservable](distinct-observable.md)) | `fun <T> distinctObservable(initialValue: T, onChange: (newValue: T) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md)`): `[`ReadWriteProperty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-write-property/index.md)`<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.md)`?, T>` |
| [toJson](to-json.md)) | `fun <T> T.toJson(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md) |
| [validateIn](validate-in.md)) | `fun <T : `[`Comparable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-comparable/index.md)`<T>> T.validateIn(values: `[`Iterable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/index.md)`<T>, name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [validateInRange](validate-in-range.md)) | `fun <T : `[`Comparable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-comparable/index.md)`<T>> T.validateInRange(range: `[`ClosedRange`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-closed-range/index.md)`<T>, name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |
| [validateNotSmaller](validate-not-smaller.md)) | `fun <T : `[`Comparable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-comparable/index.md)`<T>> T.validateNotSmaller(limit: T, name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.md) |

