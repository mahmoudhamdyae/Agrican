package com.theflankers.agrican.domain.use_case.validation

import com.theflankers.agrican.R
import com.theflankers.agrican.common.ext.isValidEmail

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