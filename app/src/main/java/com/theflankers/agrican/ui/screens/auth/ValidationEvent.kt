package com.theflankers.agrican.ui.screens.auth

sealed class ValidationEvent {
    object AuthSuccess: ValidationEvent()
    object ConfirmSuccess: ValidationEvent()
}