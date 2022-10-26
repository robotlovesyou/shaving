package com.github.robotlovesyou.shaving

import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe


class StringValidationsSpec : WordSpec({

    data class Target(val p1: String)
    "isRequired" When {
        val targetValidator = validation {
            Target::p1 {
                isRequired()
            }
        }

        "checking a string which is not empty" should {
            "return Valid" {
                targetValidator(Target(p1 = "something")) shouldBe Valid
            }
        }
        "checking a string which is empty" should {
            "return a validation error" {
                targetValidator(Target(p1 = "")) shouldHaveErrorCount 1
            }
        }
    }
    "hasMinLength" When {
        val minLength = 5L
        val targetValidator = validation {

            Target::p1 {
                hasMinLength(minLength)
            }
        }
        "checking a string over the min length" should {
            "return valid" {
                targetValidator(Target(p1 = "A".repeat(minLength.toInt() + 1))) shouldBe Valid
            }
        }
        "checking a string of exactly the min length" should {
            "return valid" {
                targetValidator(Target(p1 = "A".repeat(minLength.toInt()))) shouldBe Valid
            }
        }
        "checking a string under the min length" should {
            "return a validation error" {
                targetValidator(Target(p1 = "A".repeat(minLength.toInt() - 1))) shouldHaveErrorCount 1
            }
        }
    }
    "hasMaxLength" When {
        val maxLength = 5L
        val targetValidator = validation {
            Target::p1 {
                hasMaxLength(maxLength)
            }
        }
        "checking a string under the max length" should {
            "return valid"{
                targetValidator(Target(p1="")) shouldBe Valid
            }
        }
        "checking a string of exactly the max length" should {
            "return valid" {
                targetValidator(Target(p1="A".repeat(maxLength.toInt()))) shouldBe Valid
            }
        }
        "checking a string over the max length" should {
            "return a validation error" {
                targetValidator(Target(p1="A".repeat(maxLength.toInt() + 1))) shouldHaveErrorCount 1
            }
        }
    }
    "multiple string validations" When {
        val minLength = 2L
        val maxLength = 5L
        val targetValidator = validation {
            Target::p1 {
                isRequired()
                hasMinLength(minLength)
                hasMaxLength(maxLength)
            }
        }
        "with a valid target" should {
            "return valid" {
                targetValidator(Target(p1="A".repeat(minLength.toInt() + 1))) shouldBe Valid
            }
        }
        "checking an empty string" should {
            "return the correct count of validation errors" {
                targetValidator(Target(p1="")) shouldHaveErrorCount 2
            }
        }
        "checking a string which is too short" should {
            "return the correct count of validation errors" {
                targetValidator(Target(p1="A".repeat(minLength.toInt() -1 ))) shouldHaveErrorCount 1
            }
        }
        "checking a string which is too long" should {
            "return the correct count of validation errors" {
                targetValidator(Target(p1="A".repeat(maxLength.toInt() + 1))) shouldHaveErrorCount 1
            }
        }
    }

})