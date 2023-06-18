package com.example.agrican.ui.screens.auth

data class AuthFormState(
    val userName: String = "",
    val userNameError: Int? = null,
    val password: String = "",
    val passwordError: Int? = null,
    val repeatedPassword: String = "",
    val repeatedPasswordError: Int? = null,
    val phoneNumber: String = "",
    val phoneNumberError: Int? = null,
    val email: String = "",
    val emailError: Int? = null,
    val confirmCode: String = "",
    val confirmCodeError: Int? = null,
)
