package com.example.agrican.ui.screens.auth.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agrican.domain.usecase.BaseUseCase
import com.example.agrican.ui.screens.auth.AuthFormEvent
import com.example.agrican.ui.screens.auth.AuthFormState
import com.example.agrican.ui.screens.auth.ValidationEvent
import com.example.agrican.ui.screens.home.HomeDestination
import kotlinx.coroutines.channels.Channel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val useCase: BaseUseCase,
): ViewModel() {

    var state by mutableStateOf(AuthFormState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: AuthFormEvent) {
        when (event) {
            is AuthFormEvent.UserNameChanged -> {
                state = state.copy(userName = event.userName)
            }
            is AuthFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }
            is AuthFormEvent.RepeatedPasswordChanged -> {
                state = state.copy(repeatedPassword = event.repeatedPassword)
            }
            is AuthFormEvent.PhoneNumberChanged -> {
                state = state.copy(phoneNumber = event.phoneNumber)
            }
            is AuthFormEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }
            is AuthFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val userNameResult = useCase.validateUserName(state.userName)
        val passwordResult = useCase.validatePassword(state.password)
        val repeatedPasswordResult = useCase.validateRepeatedPassword(
            state.password, state.repeatedPassword
        )
        val phoneNumberResult = useCase.validatePhoneNumber(state.phoneNumber)
        val emailResult = useCase.validateEmail(state.email)

        val hasError = listOf(
            userNameResult,
            passwordResult,
            repeatedPasswordResult,
            phoneNumberResult,
            emailResult
        ).any { !it.successful }

        if (hasError) {
            state = state.copy(
                userNameError = userNameResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                repeatedPasswordError = repeatedPasswordResult.errorMessage,
                phoneNumberError = phoneNumberResult.errorMessage,
                emailError = emailResult.errorMessage,
            )
            return
        }
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    fun clearState() {
        state = state.copy(
            userNameError = null,
            passwordError = null,
            repeatedPasswordError = null,
            phoneNumberError = null,
            emailError = null,
        )
    }

    fun onSignUpClick(navigate: (String) -> Unit) {
        viewModelScope.launch {
            useCase.signupUseCase(
                userName = state.userName,
                password = state.password,
                phoneNumber = state.phoneNumber,
                email = state.email
            )
            navigate(HomeDestination.route)
        }
    }
}