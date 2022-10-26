package shaving

sealed interface ValidationResult
object Valid : ValidationResult
data class ValidationError(val property: String, val message: String)
class Invalid(vararg errors: ValidationError) : ArrayList<ValidationError>(listOf(*errors)), ValidationResult