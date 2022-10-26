package com.github.robotlovesyou.shaving

import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZonedDateTime

class DateTimeValidationsSpec: WordSpec({
    "Instant.isInTheFuture" When {
        data class Target(val p1: Instant)
        val targetValidator = validation {
            Target::p1 {
                isInTheFuture()
            }
        }
        "compared to a date in the future" should {
            "return valid" {
                targetValidator(Target(Instant.now().plusSeconds(10))) shouldBe Valid
            }
        }
        "compared to a date in the past should" should {
            "return a validation error" {
                targetValidator(Target(Instant.now().minusSeconds(1))) shouldHaveErrorCount 1
            }
        }
    }
    "LocalDateTime.isITheFuture" When {
        data class Target(val p1: LocalDateTime)
        val targetValidator = validation {
            Target::p1 {
                isInTheFuture()
            }
        }
        "compared to a date in the future" should {
            "return valid" {
                targetValidator(Target(LocalDateTime.now().plusSeconds(10))) shouldBe Valid
            }
        }
        "compared to a date in the past should" should {
            "return a validation error" {
                targetValidator(Target(LocalDateTime.now().minusSeconds(1))) shouldHaveErrorCount 1
            }
        }
    }
    "ZonedDateTime.isITheFuture" When {
        data class Target(val p1: ZonedDateTime)
        val targetValidator = validation {
            Target::p1 {
                isInTheFuture()
            }
        }
        "compared to a date in the future" should {
            "return valid" {
                targetValidator(Target(ZonedDateTime.now().plusSeconds(10))) shouldBe Valid
            }
        }
        "compared to a date in the past should" should {
            "return a validation error" {
                targetValidator(Target(ZonedDateTime.now().minusSeconds(1))) shouldHaveErrorCount 1
            }
        }
    }
})