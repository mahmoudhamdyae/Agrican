package com.example.agrican.domain.usecase.validation

import com.example.agrican.R
import com.example.agrican.common.ext.isValidEmail

class ValidateEmail {

    operator fun invoke(email: String): ValidationResult {
        return if (email.isValidEmail()) {
            ValidationResult(
                successful = true,
            )
        } else {
            ValidationResult(
                successful = false,
                errorMessage = R.string.email_error
            )
        }
    }
}