package com.github.robotlovesyou.shaving

interface CollectsValidations<T> {
    var validations: MutableList<Validation<T>>
}