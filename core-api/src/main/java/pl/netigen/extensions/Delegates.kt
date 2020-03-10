package pl.netigen.extensions

import kotlin.properties.ReadWriteProperty

inline fun <T> distinctObservable(
    initialValue: T,
    crossinline onChange: (newValue: T) -> Unit
): ReadWriteProperty<Any?, T> =
    object : DistinctObservableProperty<T>(initialValue) {
        override fun onDistinctNewValue(newValue: T) = onChange(newValue)
    }