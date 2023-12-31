package com.theflankers.agrican.ui.screens.auth.login

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.theflankers.agrican.R
import com.theflankers.agrican.domain.model.UserType
import com.theflankers.agrican.ui.components.Background
import com.theflankers.agrican.ui.components.DialogBoxLoading
import com.theflankers.agrican.ui.components.DropDown
import com.theflankers.agrican.ui.components.PasswordField
import com.theflankers.agrican.ui.components.UserNameField
import com.theflankers.agrican.ui.navigation.NavigationDestination
import com.theflankers.agrican.ui.screens.auth.AuthFormEvent
import com.theflankers.agrican.ui.screens.auth.AuthFormState
import com.theflankers.agrican.ui.screens.auth.ValidationEvent
import com.theflankers.agrican.ui.screens.auth.signup.SignupDestination
import com.theflankers.agrican.ui.theme.body
import com.theflankers.agrican.ui.theme.gray
import com.theflankers.agrican.ui.theme.greenDark

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
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val confirmResetScreen by viewModel.confirmResetScreen.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->
            when (event) {
                is ValidationEvent.AuthSuccess -> {
                    viewModel.onSignInClick(openAndClear)
                }
                is ValidationEvent.ConfirmSuccess -> {
                    viewModel.onConfirmResetPassword(openAndClear)
                }
            }
        }
    }

    LoginScreenContent(
        changeAccountType = viewModel::setAccountType,
        state = state,
        clearState = viewModel::clearState,
        onEvent = viewModel::onEvent,
        openScreen = openScreen,
        confirmResetScreen = confirmResetScreen,
        modifier = modifier
    )

    AnimatedVisibility(isLoading) {
        DialogBoxLoading()
    }
}

@Composable
fun LoginScreenContent(
    changeAccountType: (UserType) -> Unit,
    state: AuthFormState,
    clearState: () -> Unit,
    onEvent: (AuthFormEvent) -> Unit,
    openScreen: (String) -> Unit,
    confirmResetScreen: Boolean,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    Background(body1 = {

        if (!confirmResetScreen) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(32.dp)
            ) {
                // Profile Image
                Icon(
                    painter = painterResource(id = R.drawable.default_image),
                    contentDescription = null,
                    tint = Color(0xffe5e5e5),
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .size(150.dp)
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
                        .padding(8.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.forgot_password_text),
                        style = MaterialTheme.typography.body,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.click_here),
                        textDecoration = TextDecoration.Underline,
                        color = Color.Blue,
                        style = MaterialTheme.typography.body,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.clickable {
                            onEvent(AuthFormEvent.ForgotPassword)
                        }
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .height(40.dp)
                ) {
                    Spacer(modifier = Modifier.weight(2f))

                    // Account Type Drop Down
                    DropDown(
                        options = listOf(
                            R.string.farmer,
                            R.string.farm,
                            R.string.engineer,
                        ),
                        textColor = Color(0xffc1c1c1),
                        onSelect = {
                            when (it) {
                                R.string.engineer -> { changeAccountType(UserType.ENGINEER) }
                                R.string.farm -> { changeAccountType(UserType.FARM) }
                                else -> { changeAccountType(UserType.FARMER) }
                            }
                        },
                        modifier = Modifier.weight(1f)
                    )
                }

                // Log In Button
                Button(
                    onClick = { onEvent(AuthFormEvent.Submit) },
                    colors = ButtonDefaults.buttonColors(containerColor = greenDark),
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = stringResource(id = R.string.login_button))
                }

                // Navigate To Sign Up Screen
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(id = R.string.new_user_text),
                        style = MaterialTheme.typography.body,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(8.dp)
                    )
                    OutlinedButton(
                        onClick = {
                            openScreen(SignupDestination.route)
                            clearState() },
                        border = BorderStroke(1.dp, gray)
                    ) {
                        Text(
                            text = stringResource(id = R.string.signup_button),
                            color = greenDark,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
            }
        } else {
            ResetPasswordScreen(
                state = state,
                onEvent = onEvent
            )
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
        openScreen = { },
        confirmResetScreen = false
    )
}

@Preview(showBackground = true)
@Composable
fun LoginResetPasswordScreenPreview() {
    LoginScreenContent(
        changeAccountType = { },
        state = AuthFormState(),
        clearState = { },
        onEvent = { },
        openScreen = { },
        confirmResetScreen = true
    )
}