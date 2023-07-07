package com.example.agrican.ui.screens.auth.signup

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.agrican.R
import com.example.agrican.common.utils.toPx
import com.example.agrican.domain.model.UserType
import com.example.agrican.ui.components.Background
import com.example.agrican.ui.components.DialogBoxLoading
import com.example.agrican.ui.components.EmailField
import com.example.agrican.ui.components.PasswordField
import com.example.agrican.ui.components.PhoneNumberField
import com.example.agrican.ui.components.RepeatPasswordField
import com.example.agrican.ui.components.UserNameField
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.screens.auth.AuthFormEvent
import com.example.agrican.ui.screens.auth.AuthFormState
import com.example.agrican.ui.screens.auth.ValidationEvent
import com.example.agrican.ui.theme.body
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.title
import com.example.agrican.ui.theme.white

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
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val accountType by viewModel.accountType.collectAsStateWithLifecycle()

    var confirmAccount by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->
            when (event) {
                is ValidationEvent.AuthSuccess -> {
                    viewModel.onSignUpClick(accountType!!) { confirmAccount = true }
                }
                is ValidationEvent.ConfirmSuccess -> {
                    viewModel.onConfirmSignUpClick(openAndClear)
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
        confirmAccount = confirmAccount,
        modifier = modifier
    )

    AnimatedVisibility(isLoading) {
        DialogBoxLoading()
    }
}

@Composable
fun SignupScreenContent(
    accountType: UserType?,
    setAccountType: (UserType?) -> Unit,
    state: AuthFormState,
    clearState: () -> Unit,
    onEvent: (AuthFormEvent) -> Unit,
    navigateUp: () -> Unit,
    confirmAccount: Boolean,
    modifier: Modifier = Modifier
) {
    Background(modifier = modifier, body1 = {
        Box {
            IconButton(
                onClick = {
                    clearState()
                    if (accountType == null) navigateUp()
                    else setAccountType(null)
                },
                modifier = Modifier
                    .padding(12.dp)
                    .clip(CircleShape)
                    .background(greenDark)
                    .size(32.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
                    contentDescription = null,
                    tint = white,
                    modifier = Modifier.padding(9.dp)
                )
            }

            if (accountType == null) {
                AccountType(
                    setAccountType = setAccountType,
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(32.dp)
                )
            } else {
                Signup(
                    state = state,
                    onEvent = onEvent,
                    accountType = accountType,
                    confirmAccount = confirmAccount,
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(32.dp)
                )
            }
        }
    })
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
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(32.dp),
                border = BorderStroke(1.dp, gray),
                modifier = Modifier.padding(top = 32.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.create_new_user),
                    style = MaterialTheme.typography.title,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(
                        vertical = 8.dp,
                        horizontal = 16.dp
                    )
                )
            }

            Text(
                text = stringResource(id = R.string.choose_user_account),
                color = greenDark,
                style = MaterialTheme.typography.body,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.height(32.dp))

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
                accountTypeDescription = R.string.engineer_description,
                modifier = Modifier.padding(bottom = 16.dp)
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
        modifier = modifier.padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Button(
            onClick = { setAccountType(accountType) },
            colors = ButtonDefaults.buttonColors(containerColor = greenDark),
        ) {
            Text(
                text = stringResource(id = accountType.title),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
        Text(
            text = stringResource(id = accountTypeDescription),
            textAlign = TextAlign.Center,
            color = greenDark,
            style = MaterialTheme.typography.body,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

@Composable
fun Signup(
    state: AuthFormState,
    onEvent: (AuthFormEvent) -> Unit,
    accountType: UserType,
    confirmAccount: Boolean,
    modifier: Modifier = Modifier
) {
    if (!confirmAccount) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier.padding(top = 32.dp)
        ) {
            val focusManager = LocalFocusManager.current

            val stroke = Stroke(
                width = 2.dp.toPx(),
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(12.dp.toPx(), 8.dp.toPx()), 0.dp.toPx())
            )

            Surface(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .padding(bottom = 16.dp)
                    .drawBehind {
                        drawRoundRect(
                            color = greenDark,
                            style = stroke,
                            cornerRadius = CornerRadius(16.dp.toPx(), 16.dp.toPx())
                        )
                    }
            ) {
                Text(
                    text = stringResource(id = accountType.title),
                    color = greenDark,
                    style = MaterialTheme.typography.body,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(horizontal = 32.dp)
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
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.signup_button),
                    fontSize = 15.sp
                )
            }
        }
    } else {
        ConfirmSignUpScreen(
            value = state.confirmCode,
            onNewValue = { onEvent(AuthFormEvent.ConfirmCodeChanged(it)) },
            codeError = state.confirmCodeError,
            onEvent = onEvent,
            modifier = modifier
        )
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
        navigateUp = { },
        confirmAccount = true
    )
}

@Preview(showBackground = true)
@Composable
fun SignupPreview() {
    Signup(
        state = AuthFormState(),
        onEvent = { },
        accountType = UserType.FARMER,
        confirmAccount = false
    )
}

@Preview(showBackground = true)
@Composable
fun SignupConfirmAccountPreview() {
    Signup(
        state = AuthFormState(),
        onEvent = { },
        accountType = UserType.FARMER,
        confirmAccount = true
    )
}