package com.theflankers.agrican.domain.use_case.validation

import com.theflankers.agrican.R
import com.theflankers.agrican.common.ext.passwordMatches

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