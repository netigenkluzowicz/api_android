package pl.netigen.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> String.fromJson(): T {
    val jsonAdapter = object : TypeToken<T>() {}.type
    return Gson().fromJson(this, jsonAdapter)
}

inline fun <reified T> T.toJson(): String {
    val gson = Gson()
    return gson.toJson(this)
}