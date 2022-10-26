package shaving

import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

infix fun ValidationResult.shouldHaveErrorCount(n: Long) {
    this.shouldBeInstanceOf<Invalid>()
    this.size shouldBe n
}