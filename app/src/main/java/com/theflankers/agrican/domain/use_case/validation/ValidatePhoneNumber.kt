package com.theflankers.agrican.domain.use_case.validation

import com.theflankers.agrican.R
import com.theflankers.agrican.common.ext.isValidPhoneNumber

class ValidatePhoneNumber {

    operator fun invoke(phoneNumber: String): ValidationResult {
        return if (phoneNumber.isValidPhoneNumber()) {
            ValidationResult(
                successful = true,
            )
        } else {
            ValidationResult(
                successful = false,
                errorMessage = R.string.phone_number_error
            )
        }
    }
}