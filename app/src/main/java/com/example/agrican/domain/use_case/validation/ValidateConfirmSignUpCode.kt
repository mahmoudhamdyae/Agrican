package com.example.agrican.domain.use_case.validation

import com.example.agrican.R
import com.example.agrican.common.ext.isValidConfirmSignUpCode

class ValidateConfirmSignUpCode {

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