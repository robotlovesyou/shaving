package com.github.robotlovesyou.shaving

import kotlin.reflect.KProperty1

interface StringValidations<T: Any>: CollectsValidations<T> {
    fun KProperty1<T, String>.isRequired() {
        validations += { target, state ->
            if (this.get(target).isEmpty()) {
                state.errors += ValidationError(this.name, "is required")
            }
        }
    }

    fun KProperty1<T, String>.hasMinLength(n: Long) {
        validations += { target, state ->
            if(this.get(target).length < n) {
                state.errors += ValidationError(this.name, "must have a length of at least $n")
            }
        }
    }

    fun KProperty1<T, String>.hasMaxLength(n: Long) {
        validations += { target, state ->
            if(this.get(target).length > n) {
                state.errors += ValidationError(this.name, "should not have a length longer than $n")
            }
        }
    }
}