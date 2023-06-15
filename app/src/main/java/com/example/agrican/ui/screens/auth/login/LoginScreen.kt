package com.example.agrican.ui.screens.auth.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.agrican.R
import com.example.agrican.domain.model.UserType
import com.example.agrican.ui.components.Background
import com.example.agrican.ui.components.DropDown
import com.example.agrican.ui.components.PasswordField
import com.example.agrican.ui.components.UserNameField
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.screens.auth.AuthFormEvent
import com.example.agrican.ui.screens.auth.AuthFormState
import com.example.agrican.ui.screens.auth.ValidationEvent
import com.example.agrican.ui.screens.auth.signup.SignupDestination
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.spacing

object LoginDestination: NavigationDestination {
    override val route: String = "login"
    override val titleRes: Int = R.string.app_name
}

@Composable
fun LoginScreen(
    openScreen: (String) -> Unit,
    openAndClear: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val context = LocalContext.current

    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->
            when (event) {
                is ValidationEvent.Success -> {
                    viewModel.onSignInClick(openAndClear)
                }
            }
        }
    }

    LoginScreenContent(
        changeAccountType = viewModel::setAccountType,
        state = state,
        clearState = viewModel::clearState,
        onEvent = viewModel::onEvent,
        onForgotPassword = viewModel::onForgotPassword,
        openScreen = openScreen,
        modifier = modifier
    )
}

@Composable
fun LoginScreenContent(
    changeAccountType: (UserType) -> Unit,
    state: AuthFormState,
    clearState: () -> Unit,
    onEvent: (AuthFormEvent) -> Unit,
    onForgotPassword: () -> Unit,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    Background(body1 = {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Profile Image
            Icon(
                painter = painterResource(id = R.drawable.default_image),
                contentDescription = null,
                tint = gray,
                modifier = Modifier
                    .padding(bottom = MaterialTheme.spacing.medium)
                    .size(100.dp)
            )

            UserNameField(
                value = state.userName,
                onNewValue = { onEvent(AuthFormEvent.UserNameChanged(it)) },
                focusManager = focusManager,
                userNameError = state.userNameError,
                modifier = Modifier.fillMaxWidth()
            )

            PasswordField(
                value = state.password,
                onNewValue = { onEvent(AuthFormEvent.PasswordChanged(it)) },
                focusManager = focusManager,
                passwordError = state.passwordError,
                imeAction = ImeAction.Done,
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
                    .padding(MaterialTheme.spacing.small)
            ) {
                Text(text = stringResource(id = R.string.forgot_password_text))
                Text(
                    text = stringResource(id = R.string.click_here),
                    textDecoration = TextDecoration.Underline,
                    color = Color.Blue,
                    modifier = Modifier.clickable { onForgotPassword() }
                )
            }
            Row(modifier = Modifier.fillMaxWidth().height(50.dp)) {
                Spacer(modifier = Modifier.weight(1f))

                // Account Type Drop Down
                DropDown(options = listOf(
                    R.string.farmer,
                    R.string.farm,
                    R.string.engineer,
                ),
                    isGray = true,
                    onSelect = {
                        when (it) {
                            R.string.engineer -> { changeAccountType(UserType.ENGINEER) }
                            R.string.farm -> { changeAccountType(UserType.FARM) }
                            else -> { changeAccountType(UserType.FARMER) }
                        }
                               },
                    modifier = Modifier.weight(1f).padding(vertical = MaterialTheme.spacing.small)
                )
            }

            // Log In Button
            Button(
                onClick = { onEvent(AuthFormEvent.Submit) },
                colors = ButtonDefaults.buttonColors(containerColor = greenDark),
                modifier = Modifier.padding(MaterialTheme.spacing.small)
            ) {
                Text(text = stringResource(id = R.string.login_button))
            }

            // Navigate To Sign Up Screen
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(id = R.string.new_user_text),
                    modifier = Modifier.padding(MaterialTheme.spacing.small)
                )
                OutlinedButton(
                    onClick = {
                        openScreen(SignupDestination.route)
                        clearState() },
                    border = BorderStroke(1.dp, gray)
                ) {
                    Text(
                        text = stringResource(id = R.string.signup_button),
                        color = greenDark
                    )
                }
            }
        }
    }, modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreenContent(
        changeAccountType = { },
        state = AuthFormState(),
        clearState = { },
        onEvent = { },
        onForgotPassword = { },
        openScreen = { }
    )
}