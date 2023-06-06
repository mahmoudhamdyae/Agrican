package com.example.agrican.ui.screens.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.agrican.R
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.screens.auth.AuthFormEvent
import com.example.agrican.ui.screens.auth.AuthFormState
import com.example.agrican.ui.screens.auth.ValidationEvent
import com.example.agrican.ui.screens.auth.signup.SignupDestination
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
        state = state,
        onEvent = viewModel::onEvent,
        openScreen = openScreen,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreenContent(
    state: AuthFormState,
    onEvent: (AuthFormEvent) -> Unit,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .weight(2f)
                .clip(
                    RoundedCornerShape(
                        bottomStart = MaterialTheme.spacing.extraLarge,
                        bottomEnd = MaterialTheme.spacing.extraLarge
                    )
                )
                .background(Color.White)
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.large)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = null
                )

                OutlinedTextField(
                    singleLine = true,
                    value = state.userName,
                    onValueChange = { onEvent(AuthFormEvent.UserNameChanged(it)) },
                    isError = state.userNameError != null,
                    placeholder = { Text(stringResource(R.string.user_name_text_field)) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = stringResource(id = R.string.user_name_text_field)
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                    shape = RoundedCornerShape(MaterialTheme.spacing.medium),
                    modifier = Modifier.padding(MaterialTheme.spacing.small)
                )
                if (state.userNameError != null) {
                    Text(
                        text = stringResource(id = state.userNameError),
                        color = MaterialTheme.colorScheme.error,
                    )
                }

                var isVisible by rememberSaveable { mutableStateOf(false) }

                val icon =
                    if (isVisible) painterResource(R.drawable.ic_visibility_on)
                    else painterResource(R.drawable.ic_visibility_off)

                val visualTransformation =
                    if (isVisible) VisualTransformation.None else PasswordVisualTransformation()

                OutlinedTextField(
                    value = state.password,
                    onValueChange = { onEvent(AuthFormEvent.PasswordChanged(it)) },
                    isError = state.passwordError != null,
                    placeholder = { Text(text = stringResource(R.string.password_text_field)) },
                    leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Lock") },
                    trailingIcon = {
                        IconButton(onClick = { isVisible = !isVisible }) {
                            Icon(painter = icon, contentDescription = "Visibility")
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.clearFocus() }
                    ),
                    visualTransformation = visualTransformation,
                    shape = RoundedCornerShape(MaterialTheme.spacing.medium),
                    modifier = Modifier.padding(MaterialTheme.spacing.small)
                )
                if (state.passwordError != null) {
                    Text(
                        text = stringResource(id = state.passwordError),
                        color = MaterialTheme.colorScheme.error,
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(text = stringResource(id = R.string.forgot_password_text))
                    Text(
                        text = stringResource(id = R.string.click_here),
                        color = Color.Blue
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    AccountTypeDropDown()
                }

                Button(
                    onClick = { onEvent(AuthFormEvent.Submit) },
                    modifier = Modifier.padding(MaterialTheme.spacing.medium)
                ) {
                    Text(text = stringResource(id = R.string.login_button))
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(id = R.string.new_user_text),
                        modifier = Modifier.padding(MaterialTheme.spacing.small)
                    )
                    OutlinedButton(onClick = { openScreen(SignupDestination.route) }) {
                        Text(text = stringResource(id = R.string.signup_button))
                    }
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountTypeDropDown(
    modifier: Modifier = Modifier
) {
    val availabilityOptions = arrayOf(
        R.string.farms,
        R.string.farm,
        R.string.engineer,
    )
    var expanded by remember { mutableStateOf(false) }
    var selectedAvailabilityOption by remember { mutableStateOf(availabilityOptions[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            readOnly = true,
            value = stringResource(id = selectedAvailabilityOption),
            onValueChange = { },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            shape = RoundedCornerShape(MaterialTheme.spacing.medium),
            modifier = Modifier.menuAnchor().padding(MaterialTheme.spacing.small)
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            availabilityOptions.forEach { selectionOptions ->
                DropdownMenuItem(
                    text = { Text(text = stringResource(id = selectionOptions)) },
                    onClick = {
                        selectedAvailabilityOption = selectionOptions
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreenContent(state = AuthFormState(), onEvent = { }, openScreen = { })
}