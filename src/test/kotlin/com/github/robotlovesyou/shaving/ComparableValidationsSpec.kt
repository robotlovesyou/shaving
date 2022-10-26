package com.github.robotlovesyou.shaving

import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe
import java.time.Instant
import java.time.ZonedDateTime

class ComparableValidationsSpec : WordSpec({
    data class Target<T>(val p1: T)

    val fixedDateString = "2022-01-01T00:00:00Z"

    data class ComparisonConfig<T : Comparable<T>>(
        val type: String,
        val compareTo: T,
        val equalValue: T,
        val greaterValue: T,
        val lesserValue: T
    )

    fun <T : Comparable<T>> comparableTests(config: ComparisonConfig<T>) {
        "isEqualTo for ${config.type}" When {
            val targetValidator: Validator<Target<T>> = validation {
                Target<T>::p1 {
                    isEqualTo(config.compareTo)
                }
            }
            "comparing equal values" should {
                "return valid" {
                    targetValidator(Target(config.equalValue)) shouldBe Valid
                }
            }
            "comparing a greater value" should {
                "return a validation error" {
                    targetValidator(Target(config.greaterValue)) shouldHaveErrorCount 1
                }
            }
            "comparing a lesser value" should {
                "return a validation error" {
                    targetValidator(Target(config.lesserValue)) shouldHaveErrorCount 1
                }
            }
        }
        "isGreaterThanOrEqualTo for ${config.type}" When {
            val targetValidator: Validator<Target<T>> = validation {
                Target<T>::p1 {
                    isGreaterThanOrEqualTo(config.compareTo)
                }
            }
            "comparing equal values" should {
                "return valid" {
                    targetValidator(Target(config.equalValue)) shouldBe Valid
                }
            }
            "comparing a greater value" should {
                "return valid" {
                    targetValidator(Target(config.greaterValue)) shouldBe Valid
                }
            }
            "comparing a lesser value" should {
                "return a validation error" {
                    targetValidator(Target(config.lesserValue)) shouldHaveErrorCount 1
                }
            }
        }
        "isGreaterThan for ${config.type}" When {
            val targetValidator: Validator<Target<T>> = validation {
                Target<T>::p1 {
                    isGreaterThan(config.compareTo)
                }
            }
            "comparing equal values" should {
                "return a validation error" {
                    targetValidator(Target(config.equalValue)) shouldHaveErrorCount 1
                }
            }
            "comparing a greater value" should {
                "return valid" {
                    targetValidator(Target(config.greaterValue)) shouldBe Valid
                }
            }
            "comparing a lesser value" should {
                "return a validation error" {
                    targetValidator(Target(config.lesserValue)) shouldHaveErrorCount 1
                }
            }
        }
        "isLessThanOrEqualTo for ${config.type}" When {
            val targetValidator: Validator<Target<T>> = validation {
                Target<T>::p1 {
                    isLessThanOrEqualTo(config.compareTo)
                }
            }
            "comparing equal values" should {
                "return valid" {
                    targetValidator(Target(config.equalValue)) shouldBe Valid
                }
            }
            "comparing a greater value" should {
                "return a validation error" {
                    targetValidator(Target(config.greaterValue)) shouldHaveErrorCount 1
                }
            }
            "comparing a lesser value" should {
                "return valid" {
                    targetValidator(Target(config.lesserValue)) shouldBe Valid
                }
            }
        }
        "isLessThan for ${config.type}" When {
            val targetValidator: Validator<Target<T>> = validation {
                Target<T>::p1 {
                    isLessThan(config.compareTo)
                }
            }
            "comparing equal values" should {
                "return a validation error" {
                    targetValidator(Target(config.equalValue)) shouldHaveErrorCount 1
                }
            }
            "comparing a greater value" should {
                "return a validation error" {
                    targetValidator(Target(config.greaterValue)) shouldHaveErrorCount 1
                }
            }
            "comparing a lesser value" should {
                "return valid" {
                    targetValidator(Target(config.lesserValue)) shouldBe Valid
                }
            }
        }
    }
    comparableTests(
        ComparisonConfig(
            type = "Instant",
            compareTo = Instant.parse(fixedDateString),
            equalValue = Instant.parse(fixedDateString),
            greaterValue = Instant.parse(fixedDateString).plusSeconds(1),
            lesserValue = Instant.parse(fixedDateString).minusSeconds(1)
        )
    )
    comparableTests(
        ComparisonConfig(
            type = "ZonedDateTime",
            compareTo = ZonedDateTime.parse(fixedDateString),
            equalValue = ZonedDateTime.parse(fixedDateString),
            greaterValue = ZonedDateTime.parse(fixedDateString).plusSeconds(1),
            lesserValue = ZonedDateTime.parse(fixedDateString).minusSeconds(1)
        )
    )
    comparableTests(
        ComparisonConfig(
            type = "Int",
            compareTo = 1,
            equalValue = 1,
            greaterValue = 2,
            lesserValue = 0
        )
    )
    comparableTests(
        ComparisonConfig(
            type = "Long",
            compareTo = 1L,
            equalValue = 1L,
            greaterValue = 2L,
            lesserValue = 0L
        )
    )
    comparableTests(
        ComparisonConfig(
            type = "Double",
            compareTo = 1.0,
            equalValue = 1.0,
            greaterValue = 2.0,
            lesserValue = 0.0
        )
    )
    comparableTests(
        ComparisonConfig(
            type = "Float",
            compareTo = 1.0F,
            equalValue = 1.0F,
            greaterValue = 2.0F,
            lesserValue = 0.0F
        )
    )


})