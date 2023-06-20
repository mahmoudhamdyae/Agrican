package com.example.agrican.ui.screens.auth.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.agrican.R
import com.example.agrican.domain.model.UserType
import com.example.agrican.ui.components.ConfirmField
import com.example.agrican.ui.screens.auth.AuthFormEvent
import com.example.agrican.ui.screens.auth.AuthFormState
import com.example.agrican.ui.theme.greenDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ConfirmSignUpScreen(
    value: String,
    onNewValue: (String) -> Unit,
    codeError: Int?,
    onEvent: (AuthFormEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        ConfirmField(
            value = value,
            onNewValue = onNewValue,
            codeError = codeError,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = { onEvent(AuthFormEvent.ConfirmSignUp) },
            colors = ButtonDefaults.buttonColors(containerColor = greenDark),
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.confirm_signup_button),
                fontSize = 15.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConfirmSignupPreview() {
    Signup(
        state = AuthFormState(),
        onEvent = { },
        accountType = UserType.FARMER,
        confirmAccount = true
    )
}