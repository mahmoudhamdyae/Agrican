package com.example.agrican.domain.usecase.validation

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: Int? = null
)