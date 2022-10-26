import kotlin.reflect.KProperty1
import shaving.validation

fun main(args: Array<String>) {
    val thingValidation = validation<Thing> {
        Thing::a {
            isRequired()
            hasMinLength(5)
            hasMaxLength(123)
        }
    }
    println(thingValidation(Thing("", 123)))
    println(thingValidation(Thing("123", 123)))
}

data class Thing(val a: String, val b: Int) {

}

//interface ValidationResult
//object Valid : ValidationResult
//data class ValidationError(val property: String, val message: String)
//class Invalid(vararg errors: ValidationError) : ArrayList<ValidationError>(listOf(*errors)), ValidationResult
//data class ValidationState(val errors: MutableList<ValidationError> = mutableListOf())
//typealias Validation<T> = (T, ValidationState) -> Unit
//typealias Validator<T> = (T) -> ValidationResult
//
//class ValidationBuilder<T : Any>: StringValidations<T> {
//    override var validations: MutableList<Validation<T>> = mutableListOf()
//    operator fun KProperty1<T, String>.invoke(f: KProperty1<T, String>.() -> Unit) {
//        this.f()
//    }
//
//
//    fun build(): Validator<T> = { target ->
//        val state = ValidationState()
//        validations.forEach {
//            it(target, state)
//        }
//
//        if (state.errors.isEmpty()) {
//            Valid
//        } else {
//            Invalid(*state.errors.toTypedArray())
//        }
//    }
//}
//
//interface StringValidations<T: Any> {
//    var validations: MutableList<Validation<T>>
//
//    fun KProperty1<T, String>.isRequired() {
//        validations += { target, state ->
//            if (this.get(target).isEmpty()) {
//                state.errors += ValidationError(this.name, "is required")
//            }
//        }
//    }
//
//    fun KProperty1<T, String>.hasMinLength(n: Long) {
//        validations += { target, state ->
//            if(this.get(target).length < n) {
//                state.errors += ValidationError(this.name, "must have a length of at least $n")
//            }
//        }
//    }
//
//    fun KProperty1<T, String>.hasMaxLength(n: Long) {
//        validations += { target, state ->
//            if(this.get(target).length > n) {
//                state.errors += ValidationError(this.name, "should not have a length longer than $n")
//            }
//        }
//    }
//}

//fun <T : Any> validation(f: ValidationBuilder<T>.() -> Unit): Validator<T> {
//    val builder = ValidationBuilder<T>()
//    builder.f()
//    return builder.build()
//}

//interface ValidationResult


//
//typealias Validator<T> = (T) =>
//typealias Validation<T> = (T) -> ValidationResult
//
//fun <T: Any> validation(f: T.()-> Unit): Validation<T> {
//    val errors: MutableList<ValidationError> = mutableListOf()
//
//    fun KProperty1<T, String>.required() {
//        if (this.get(target).isEmpty()) {
//            errors += ValidationError(this.name, "is required")
//        }
//    }
//
//    return {target ->
//        target.f()
//
//        if (errors.isEmpty()) {
//            Valid
//        } else {
//            ValidationErrors(*errors.toTypedArray())
//        }
//    }
//
//
//}