package pl.netigen.extensions

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

abstract class DistinctObservableProperty<T>(initialValue: T) : ReadWriteProperty<Any?, T> {
    private var value = initialValue

    protected open fun onDistinctNewValue(newValue: T) = Unit

    override fun getValue(thisRef: Any?, property: KProperty<*>): T = value

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        if (this.value != value) {
            this.value = value
            onDistinctNewValue(value)
        }
    }
}