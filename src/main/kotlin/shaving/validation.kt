package shaving

fun <T : Any> validation(f: ValidationBuilder<T>.() -> Unit): Validator<T> {
    val builder = ValidationBuilder<T>()
    builder.f()
    return builder.build()
}