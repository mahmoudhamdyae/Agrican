package com.example.agrican.domain.usecase.validation

import com.example.agrican.R
import com.example.agrican.common.ext.isValidUserName

class ValidateUserName {

    operator fun invoke(userName: String): ValidationResult {
        return if (userName.isValidUserName()) {
            ValidationResult(
                successful = true,
            )
        } else {
            ValidationResult(
                successful = false,
                errorMessage = R.string.user_name_error
            )
        }
    }
}