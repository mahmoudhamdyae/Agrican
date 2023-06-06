package com.example.agrican.domain.usecase.validation

import com.example.agrican.R
import com.example.agrican.common.ext.passwordMatches

class ValidateRepeatedPassword {

    operator fun invoke(password: String, repeatedPassword: String): ValidationResult {
        return if (password.passwordMatches(repeatedPassword)) {
            ValidationResult(
                successful = true,
            )
        } else {
            ValidationResult(
                successful = false,
                errorMessage = R.string.password_match_error
            )
        }
    }
}