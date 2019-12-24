package pl.netigen.extensions

fun <T : Comparable<T>> T.validateNotSmaller(limit: T, name: String) = require(this >= limit) {
    "Invalid $name value=${this} $name must not be smaller than $limit"
}

fun <T : Comparable<T>> T.validateInRange(range: ClosedRange<T>, name: String) = require(this in range) {
    "Invalid $name value=${this} $name must be in $range range"
}

fun <T : Comparable<T>> T.validateIn(values: Iterable<T>, name: String) = require(values.contains(this)) {
    "Invalid $name value=${this} $name must be in $values"
}