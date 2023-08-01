package com.theflankers.agrican.domain.use_case.validation

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: Int? = null
)