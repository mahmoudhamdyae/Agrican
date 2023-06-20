package com.example.agrican.ui.screens.auth.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.agrican.R
import com.example.agrican.ui.components.ConfirmField
import com.example.agrican.ui.components.PasswordField
import com.example.agrican.ui.screens.auth.AuthFormEvent
import com.example.agrican.ui.screens.auth.AuthFormState
import com.example.agrican.ui.theme.greenDark
import androidx.compose.ui.unit.dp

@Composable
fun ResetPasswordScreen(
    state: AuthFormState,
    onEvent: (AuthFormEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(32.dp)
    ) {
        ConfirmField(
            value = state.confirmCode,
            onNewValue = { onEvent(AuthFormEvent.ConfirmCodeChanged(it)) },
            codeError = state.confirmCodeError,
            modifier = Modifier.fillMaxWidth().height(50.dp)
        )

        PasswordField(
            value = state.repeatedPassword,
            onNewValue = { onEvent(AuthFormEvent.RepeatedPasswordChanged(it)) },
            focusManager = focusManager,
            passwordError = state.repeatedPasswordError,
            hint = R.string.new_password_text_field,
            imeAction = ImeAction.Done,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = { onEvent(AuthFormEvent.ConfirmResetPassword) },
            colors = ButtonDefaults.buttonColors(containerColor = greenDark),
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.confirm_reset_password_button),
                fontSize = 15.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResetPasswordScreenPreview() {
    ResetPasswordScreen(state = AuthFormState(), onEvent = { })
}