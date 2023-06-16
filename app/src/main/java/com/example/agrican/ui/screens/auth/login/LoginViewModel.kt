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
            is AuthFormEvent.Submit -> {
                submitData()
            }
            is AuthFormEvent.ForgotPassword -> {
                onForgotPasswordClick()
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
            validationEventChannel.send(ValidationEvent.Success)
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
            useCase.loginUseCase(
                userName = state.userName,
                password = state.password,
                onSuccess = { navigate(HomeDestination.route) }
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
            useCase.forgotPasswordUseCase(userName = state.userName) { }
        }
    }
}