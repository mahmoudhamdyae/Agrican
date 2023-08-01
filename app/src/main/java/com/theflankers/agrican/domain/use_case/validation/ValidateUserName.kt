package com.theflankers.agrican.domain.use_case.validation

import com.theflankers.agrican.R
import com.theflankers.agrican.common.ext.isValidUserName

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