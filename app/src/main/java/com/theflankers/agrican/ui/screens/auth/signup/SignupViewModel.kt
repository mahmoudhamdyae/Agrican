package com.theflankers.agrican.ui.screens.auth.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.theflankers.agrican.domain.model.UserType
import com.theflankers.agrican.domain.repository.LogService
import com.theflankers.agrican.domain.use_case.BaseUseCase
import com.theflankers.agrican.ui.screens.BaseViewModel
import com.theflankers.agrican.ui.screens.auth.AuthFormEvent
import com.theflankers.agrican.ui.screens.auth.AuthFormState
import com.theflankers.agrican.ui.screens.auth.ValidationEvent
import com.theflankers.agrican.ui.screens.home.HomeDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val useCase: BaseUseCase,
    logService: LogService
): BaseViewModel(logService) {

    var state by mutableStateOf(AuthFormState())

    private var _accountType = MutableStateFlow<UserType?>(null)
    val accountType = _accountType.asStateFlow()

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun setAccountType(accountType: UserType?) {
        _accountType.value = accountType
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
            is AuthFormEvent.PhoneNumberChanged -> {
                state = state.copy(phoneNumber = event.phoneNumber)
            }
            is AuthFormEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }
            is AuthFormEvent.ConfirmCodeChanged -> {
                state = state.copy(confirmCode = event.code)
            }
            is AuthFormEvent.Submit -> {
                submitData()
            }
            is AuthFormEvent.ConfirmSignUp -> {
                submitConfirmSignUp()
            }
            else -> {
                throw Exception("Unknown event")
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
        launchCatching {
            validationEventChannel.send(ValidationEvent.AuthSuccess)
        }
    }

    private fun submitConfirmSignUp() {
        val codeResult = useCase.validateConfirmCodeCode(state.confirmCode)

        val hasError = !codeResult.successful

        if (hasError) {
            state = state.copy(confirmCodeError = codeResult.errorMessage)
            return
        }
        launchCatching {
            validationEventChannel.send(ValidationEvent.ConfirmSuccess)
        }
    }

    fun clearState() {
        state = state.copy(
            userNameError = null,
            passwordError = null,
            repeatedPasswordError = null,
            phoneNumberError = null,
            emailError = null,
            confirmCodeError = null
        )
    }

    fun onSignUpClick(accountType: UserType, onSuccess: () -> Unit) {
        launchCatching {
            _isLoading.value = true
            useCase.signupUseCase(
                userName = state.userName,
                password = state.password,
                phoneNumber = state.phoneNumber,
                email = state.email,
                accountType = accountType,
                onSuccess = {
                    onSuccess()
                    _isLoading.value = false
                },
                onError = {
                    _isLoading.value = false
                }
            )
        }
    }

    fun onConfirmSignUpClick(navigate: (String) -> Unit) {
        launchCatching {
            _isLoading.value = true
            useCase.confirmSignUpUseCase(
                userName = state.userName,
                code = state.confirmCode,
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
}