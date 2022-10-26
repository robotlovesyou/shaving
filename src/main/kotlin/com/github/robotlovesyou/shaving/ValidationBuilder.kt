package com.github.robotlovesyou.shaving

import kotlin.reflect.KProperty1

data class ValidationState(val errors: MutableList<ValidationError> = mutableListOf())
typealias Validation<T> = (T, ValidationState) -> Unit
typealias Validator<T> = (T) -> ValidationResult

class ValidationBuilder<T : Any>: StringValidations<T>, ComparableValidations<T> {
    override var validations: MutableList<Validation<T>> = mutableListOf()
    operator fun <U>KProperty1<T, U>.invoke(f: KProperty1<T, U>.() -> Unit) {
        this.f()
    }


    fun build(): Validator<T> = { target ->
        val state = ValidationState()
        validations.forEach {
            it(target, state)
        }

        if (state.errors.isEmpty()) {
            Valid
        } else {
            Invalid(*state.errors.toTypedArray())
        }
    }
}