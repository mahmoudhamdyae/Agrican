package com.theflankers.agrican.domain.use_case.validation

import com.theflankers.agrican.R
import com.theflankers.agrican.common.ext.isValidConfirmSignUpCode

class ValidateConfirmCodeCode {

    operator fun invoke(userName: String): ValidationResult {
        return if (userName.isValidConfirmSignUpCode()) {
            ValidationResult(
                successful = true,
            )
        } else {
            ValidationResult(
                successful = false,
                errorMessage = R.string.code_error
            )
        }
    }
}