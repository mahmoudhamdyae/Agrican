package com.example.agrican.ui.screens.auth.signup

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.agrican.R
import com.example.agrican.domain.model.UserType
import com.example.agrican.ui.components.BackButton
import com.example.agrican.ui.components.Background
import com.example.agrican.ui.components.EmailField
import com.example.agrican.ui.components.PasswordField
import com.example.agrican.ui.components.PhoneNumberField
import com.example.agrican.ui.components.RepeatPasswordField
import com.example.agrican.ui.components.UserNameField
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.screens.auth.AuthFormEvent
import com.example.agrican.ui.screens.auth.AuthFormState
import com.example.agrican.ui.screens.auth.ValidationEvent
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.spacing

object SignupDestination: NavigationDestination {
    override val route: String = "signup"
    override val titleRes: Int = R.string.app_name
}

@Composable
fun SignupScreen(
    openAndClear: (String) -> Unit,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignupViewModel = hiltViewModel()
) {

    val state = viewModel.state
    val context = LocalContext.current

    val accountType by viewModel.accountType.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->
            when (event) {
                is ValidationEvent.Success -> {
                    viewModel.onSignUpClick(accountType!!, openAndClear)
                }
            }
        }
    }

    SignupScreenContent(
        accountType = accountType,
        setAccountType = viewModel::setAccountType,
        state = state,
        clearState = viewModel::clearState,
        onEvent = viewModel::onEvent,
        navigateUp = navigateUp,
        modifier = modifier
    )
}

@Composable
fun SignupScreenContent(
    accountType: UserType?,
    setAccountType: (UserType?) -> Unit,
    state: AuthFormState,
    clearState: () -> Unit,
    onEvent: (AuthFormEvent) -> Unit,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackButton(navigateUp = {
        clearState()
        if (accountType == null) navigateUp()
        else setAccountType(null)
    }) {
        Background(modifier = modifier, body1 = {
            if (accountType == null) {
                AccountType(
                    setAccountType = setAccountType,
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                )
            } else {
                Signup(
                    state = state,
                    onEvent = onEvent,
                    accountType = accountType,
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                )
            }
        })
    }
}

@Composable
fun AccountType(
    setAccountType: (UserType) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,) {
            OutlinedButton(
                onClick = {  },
                border = BorderStroke(1.dp, gray)
            ) {
                Text(
                    text = stringResource(id = R.string.create_new_user),
                    color = greenDark
                )
            }
            Text(
                text = stringResource(id = R.string.choose_user_account),
                color = greenDark
            )
        }

        Column {
            // Farmer
            AccountTypeItem(
                setAccountType = setAccountType,
                accountType = UserType.FARMER,
                accountTypeDescription = R.string.farms_description
            )
            // Farm
            AccountTypeItem(
                setAccountType = setAccountType,
                accountType = UserType.FARM,
                accountTypeDescription = R.string.farm_description
            )
            // Engineer
            AccountTypeItem(
                setAccountType = setAccountType,
                accountType = UserType.ENGINEER,
                accountTypeDescription = R.string.engineer_description
            )
        }
    }
}

@Composable
fun AccountTypeItem(
    setAccountType: (UserType) -> Unit,
    accountType: UserType,
    accountTypeDescription: Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(MaterialTheme.spacing.medium)
    ) {
        Button(
            onClick = { setAccountType(accountType) },
            colors = ButtonDefaults.buttonColors(containerColor = greenDark),
        ) {
            Text(
                text = stringResource(id = accountType.title),
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small)
            )
        }
        Text(
            text = stringResource(id = accountTypeDescription),
            textAlign = TextAlign.Center,
            color = greenDark,
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.large)
        )
    }
}

@Composable
fun Signup(
    state: AuthFormState,
    onEvent: (AuthFormEvent) -> Unit,
    accountType: UserType,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Button(
            onClick = {  },
            colors = ButtonDefaults.buttonColors(containerColor = greenDark),
        ) {
            Text(
                text = stringResource(id = accountType.title),
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
            )
        }

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

        RepeatPasswordField(
            value = state.repeatedPassword,
            onNewValue = { onEvent(AuthFormEvent.RepeatedPasswordChanged(it)) },
            focusManager = focusManager,
            repeatedPasswordError = state.repeatedPasswordError,
            modifier = Modifier.fillMaxWidth()
        )

        PhoneNumberField(
            value = state.phoneNumber,
            onNewValue = { onEvent(AuthFormEvent.PhoneNumberChanged(it)) },
            focusManager = focusManager,
            phoneNumberError = state.phoneNumberError,
            modifier = Modifier.fillMaxWidth()
        )

        EmailField(
            value = state.email,
            onNewValue = { onEvent(AuthFormEvent.EmailChanged(it)) },
            focusManager = focusManager,
            emailError = state.emailError,
            modifier = Modifier.fillMaxWidth()
        )

        // Sign Up Button
        Button(
            onClick = { onEvent(AuthFormEvent.Submit) },
            colors = ButtonDefaults.buttonColors(containerColor = greenDark),
            modifier = Modifier.padding(MaterialTheme.spacing.medium)
        ) {
            Text(
                text = stringResource(id = R.string.signup_button),
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignupScreenPreview() {
    SignupScreenContent(
        accountType = null,
        setAccountType = { },
        state = AuthFormState(),
        clearState = { },
        onEvent = { },
        navigateUp = { }
    )
}

@Preview(showBackground = true)
@Composable
fun SignupPreview() {
    Signup(
        state = AuthFormState(),
        onEvent = { },
        accountType = UserType.FARMER
    )
}