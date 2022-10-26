package com.github.robotlovesyou.shaving

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZonedDateTime
import kotlin.reflect.KProperty1

interface DateTimeValidations<T: Any>: CollectsValidations<T> {

    //TODO: There does not appear to be a better way to do this than implementing it for each Temporal type
    //TODO: but I would very much like to be wrong!

    @Suppress("INAPPLICABLE_JVM_NAME")
    @JvmName("isInTheFutureTInstant")
    fun KProperty1<T, Instant>.isInTheFuture() {
        validations += {target, state ->
            if (this.get(target) <= Instant.now()) {
                state.errors += ValidationError(this.name, "must be in the future")
            }
        }
    }

    @Suppress("INAPPLICABLE_JVM_NAME")
    @JvmName("isInTheFutureTZonedDateTime")
    fun KProperty1<T, ZonedDateTime>.isInTheFuture() {
        validations += {target, state ->
            if (this.get(target) <= ZonedDateTime.now()) {
                state.errors += ValidationError(this.name, "must be in the future")
            }
        }
    }

    @Suppress("INAPPLICABLE_JVM_NAME")
    @JvmName("isInTheFutureTLocalDateTime")
    fun KProperty1<T, LocalDateTime>.isInTheFuture() {
        validations += {target, state ->
            if (this.get(target) <= LocalDateTime.now()) {
                state.errors += ValidationError(this.name, "must be in the future")
            }
        }
    }

    // TODO: Add isInThePast for symmetry

}