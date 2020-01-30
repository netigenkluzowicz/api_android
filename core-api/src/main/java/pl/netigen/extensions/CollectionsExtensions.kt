package pl.netigen.extensions

import java.util.*

inline fun <reified K : Enum<K>, V> Iterable<V>.mapToEnumMap(): EnumMap<K, V> = enumValues<K>().zip(this).toMap(EnumMap(K::class.java))
