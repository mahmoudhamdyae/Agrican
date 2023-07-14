package com.example.agrican.ui.screens.auth.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.agrican.domain.model.UserType
import com.example.agrican.domain.use_case.BaseUseCase
import com.example.agrican.ui.screens.BaseViewModel
import com.example.agrican.ui.screens.auth.AuthFormEvent
import com.example.agrican.ui.screens.auth.AuthFormState
import com.example.agrican.ui.screens.auth.ValidationEvent
import com.example.agrican.ui.screens.home.HomeDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: BaseUseCase,
): BaseViewModel() {

    var state by mutableStateOf(AuthFormState())
    var accountType = mutableStateOf(UserType.ENGINEER)

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _confirmResetScreen = MutableStateFlow(false)
    val confirmResetScreen = _confirmResetScreen.asStateFlow()

    fun setAccountType(accountType: UserType) {
        this.accountType.value = accountType
    }

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
            is AuthFormEvent.ConfirmCodeChanged -> {
                state = state.copy(confirmCode = event.code)
            }
            is AuthFormEvent.Submit -> {
                submitData()
            }
            is AuthFormEvent.ForgotPassword -> {
                onForgotPasswordClick()
            }
            is AuthFormEvent.ConfirmResetPassword -> {
                onConfirmResetPasswordClick()
            }
            else -> {
                throw Exception("Unknown event")
            }
        }
    }

    private fun submitData() {
        val userNameResult = useCase.validateUserName(state.userName)
        val passwordResult = useCase.validatePassword(state.password, true)

        val hasError = listOf(
            userNameResult,
            passwordResult
        ).any { !it.successful }

        if (hasError) {
            state = state.copy(
                userNameError = userNameResult.errorMessage,
                passwordError = passwordResult.errorMessage
            )
            return
        }
        launchCatching {
            validationEventChannel.send(ValidationEvent.AuthSuccess)
        }
    }

    fun clearState() {
        state = state.copy(
            userNameError = null,
            passwordError = null,
        )
    }

    fun onSignInClick(navigate: (String) -> Unit) {
        launchCatching {
            _isLoading.value = true
            useCase.loginUseCase(
                userName = state.userName,
                password = state.password,
                onSuccess = {
                    navigate(HomeDestination.route)
                    _isLoading.value = false
                },
                onError = {
                    _isLoading.value = false
                }
            )
        }
    }

    private fun onForgotPasswordClick() {
        val userNameResult = useCase.validateUserName(state.userName)

        if (!userNameResult.successful) {
            state = state.copy(userNameError = userNameResult.errorMessage)
            return
        }

        launchCatching {
            _isLoading.value = true
            useCase.forgotPasswordUseCase(
                userName = state.userName,
                onSuccess = {
                    _confirmResetScreen.value = true
                    _isLoading.value = false
                            },
                onError = { _isLoading.value = false }
            )
        }
    }

    private fun onConfirmResetPasswordClick() {
        val passwordResult = useCase.validatePassword(state.repeatedPassword, true)
        val codeResult = useCase.validateConfirmCodeCode(state.confirmCode)

        val hasError = listOf(
            passwordResult,
            codeResult
        ).any { !it.successful }

        if (hasError) {
            state = state.copy(
                repeatedPasswordError = passwordResult.errorMessage,
                confirmCodeError = codeResult.errorMessage
            )
            return
        }
        launchCatching {
            validationEventChannel.send(ValidationEvent.ConfirmSuccess)
        }
    }

    fun onConfirmResetPassword(navigate: (String) -> Unit) {
        launchCatching {
            _isLoading.value = true
            useCase.confirmResetPasswordUseCase(
                userName = state.userName,
                newPassword = state.repeatedPassword,
                code = state.confirmCode,
                onSuccess = {
                    navigate(HomeDestination.route)
                    _isLoading.value = false
                },
                onError = {
                    _isLoading.value = false
                }
            )
        }
    }
}