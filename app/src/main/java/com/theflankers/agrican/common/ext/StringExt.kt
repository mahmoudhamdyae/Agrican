package com.theflankers.agrican.common.ext

import com.theflankers.agrican.R
import java.util.regex.Pattern

private const val MIN_PASS_LENGTH = 6
private const val PASS_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$"

val phoneNumberRegex = "-?[0-9]+(\\.[0-9]+)?".toRegex()

private val emailRegex =
    ("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+").toRegex()

fun String.isValidUserName(): Boolean {
    return this.isNotBlank()
}

fun String.isValidConfirmSignUpCode(): Boolean {
    return this.isNotBlank()
}

fun String.passwordErrorMessage(): Int? {
    return if (this.isBlank()) {
        R.string.empty_password_error
    } else if (this.length < MIN_PASS_LENGTH) {
        R.string.password_length_error
    } else if (!Pattern.compile(PASS_PATTERN).matcher(this).matches()) {
        R.string.password_error
    } else {
        null
    }
}

fun String.passwordMatches(repeated: String): Boolean {
    return this == repeated
}

fun String.isValidPhoneNumber(): Boolean {
    return this.isNotBlank() && phoneNumberRegex.matches(this)
}

fun String.isValidEmail(): Boolean {
    return this.isNotBlank() && emailRegex.matches(this)
}