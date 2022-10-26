package com.github.robotlovesyou.shaving

import kotlin.reflect.KProperty1

interface ComparableValidations<T>: CollectsValidations<T> {
    fun <U: Comparable<U>>KProperty1<T, U>.isEqualTo(other: U) {
        validations += {target, state ->
            if (this.get(target) != other) {
                state.errors += ValidationError(this.name, "must be equal to $other")
            }
        }
    }

    fun <U: Comparable<U>>KProperty1<T, U>.isGreaterThanOrEqualTo(other: U) {
        validations += { target, state ->
            if (this.get(target) < other) {
                state.errors += ValidationError(this.name, "must be greater than or equal to $other")
            }
        }
    }

    fun <U: Comparable<U>>KProperty1<T, U>.isGreaterThan(other: U) {
        validations += { target, state ->
            if (this.get(target) <= other) {
                state.errors += ValidationError(this.name, "must be greater than $other")
            }
        }
    }

    fun <U: Comparable<U>>KProperty1<T, U>.isLessThanOrEqualTo(other: U) {
        validations += { target, state ->
            if(this.get(target) > other) {
                state.errors += ValidationError(this.name, "must be less than or equal to $other")
            }
        }
    }

    fun <U: Comparable<U>>KProperty1<T, U>.isLessThan(other: U) {
        validations += { target, state ->
            if (this.get(target) >= other) {
                state.errors += ValidationError(this.name, "must be less than $other")
            }
        }
    }
}