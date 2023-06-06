package com.example.agrican.ui.screens.auth

sealed class ValidationEvent {
    object Success: ValidationEvent()
}