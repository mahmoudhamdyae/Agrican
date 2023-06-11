package com.example.agrican.domain.use_case.validation

import com.example.agrican.R
import com.example.agrican.common.ext.isValidPhoneNumber

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