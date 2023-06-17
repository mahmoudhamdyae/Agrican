package com.example.agrican.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.agrican.R
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserNameField(
    value: String,
    onNewValue: (String) -> Unit,
    focusManager: FocusManager,
    userNameError: Int?,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        singleLine = true,
        value = value,
        onValueChange = { onNewValue(it) },
        isError = userNameError != null,
        placeholder = { Text(
            text = stringResource(R.string.user_name_text_field),
            color = gray,
        ) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = stringResource(id = R.string.user_name_text_field),
                tint = gray
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
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = gray,
            textColor = gray
        ),
        modifier = modifier.padding(MaterialTheme.spacing.small)
    )
    if (userNameError != null) {
        Text(
            text = stringResource(id = userNameError),
            color = MaterialTheme.colorScheme.error,
            modifier = modifier
        )
    }
}

@Composable
fun PasswordField(
    value: String,
    onNewValue: (String) -> Unit,
    focusManager: FocusManager,
    passwordError: Int?,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Next
) {
    PasswordField(value, R.string.password_text_field, onNewValue, passwordError, focusManager, modifier, imeAction)
}

@Composable
fun RepeatPasswordField(
    value: String,
    onNewValue: (String) -> Unit,
    focusManager: FocusManager,
    repeatedPasswordError: Int?,
    modifier: Modifier = Modifier,
) {
    PasswordField(value, R.string.repeat_password_text_field, onNewValue, repeatedPasswordError, focusManager, modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PasswordField(
    value: String,
    @StringRes placeholder: Int,
    onNewValue: (String) -> Unit,
    passwordError: Int?,
    focusManager: FocusManager,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Next
) {
    var isVisible by rememberSaveable { mutableStateOf(false) }

    val icon =
        if (isVisible) painterResource(R.drawable.ic_visibility_on)
        else painterResource(R.drawable.ic_visibility_off)

    val visualTransformation =
        if (isVisible) VisualTransformation.None else PasswordVisualTransformation()

    OutlinedTextField(
        value = value,
        onValueChange = { onNewValue(it) },
        singleLine = true,
        isError = passwordError != null,
        placeholder = { Text(
            text = stringResource(placeholder),
            color = gray,
        ) },
        leadingIcon = { Icon(
            imageVector = Icons.Default.Lock,
            contentDescription = "Lock",
            tint = gray
        ) },
        trailingIcon = {
            IconButton(onClick = { isVisible = !isVisible }) {
                Icon(painter = icon, contentDescription = "Visibility", tint = gray)
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() },
            onNext = { focusManager.moveFocus(FocusDirection.Down)}
        ),
        visualTransformation = visualTransformation,
        shape = RoundedCornerShape(MaterialTheme.spacing.medium),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = gray,
            textColor = gray
        ),
        modifier = modifier.padding(MaterialTheme.spacing.small)
    )
    if (passwordError != null) {
        Text(
            text = stringResource(id = passwordError),
            color = MaterialTheme.colorScheme.error,
            modifier = modifier
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneNumberField(
    value: String,
    onNewValue: (String) -> Unit,
    focusManager: FocusManager,
    phoneNumberError: Int?,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        singleLine = true,
        value = value,
        onValueChange = { onNewValue(it) },
        isError = phoneNumberError != null,
        placeholder = { Text(
            text = stringResource(R.string.phone_number_text_field),
            color = gray,
        ) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = stringResource(id = R.string.phone_number_text_field),
                tint = gray
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        ),
        shape = RoundedCornerShape(MaterialTheme.spacing.medium),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = gray,
            textColor = gray
        ),
        modifier = modifier.padding(MaterialTheme.spacing.small)
    )
    if (phoneNumberError != null) {
        Text(
            text = stringResource(id = phoneNumberError),
            color = MaterialTheme.colorScheme.error,
            modifier = modifier
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailField(
    value: String,
    onNewValue: (String) -> Unit,
    focusManager: FocusManager,
    emailError: Int?,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        singleLine = true,
        value = value,
        onValueChange = { onNewValue(it) },
        isError = emailError != null,
        placeholder = { Text(
            text = stringResource(R.string.email_text_field),
            color = gray,
        ) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = stringResource(id = R.string.email_text_field),
                tint = gray
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() }
        ),
        shape = RoundedCornerShape(MaterialTheme.spacing.medium),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = gray,
            textColor = gray
        ),
        modifier = modifier.padding(MaterialTheme.spacing.small)
    )
    if (emailError != null) {
        Text(
            text = stringResource(id = emailError),
            color = MaterialTheme.colorScheme.error,
            modifier = modifier
        )
    }
}

@Composable
fun SimpleTextField(
    value: String,
    onNewValue: (String) -> Unit,
    placeHolder: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    borderColor: Color = gray,
    focusManager: FocusManager? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next
) {
    BasicTextField(
        singleLine = true,
        value = value,
        onValueChange = onNewValue,
        decorationBox = { innerTextField ->

            if (value.isEmpty()) {
                placeHolder()
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(MaterialTheme.spacing.dp_10),
            contentAlignment = Alignment.Center
            ) {
                innerTextField()
            }
                        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(
            onNext = { focusManager?.moveFocus(FocusDirection.Down) },
            onDone = { focusManager?.clearFocus() }
        ),
        modifier = modifier
            .border(
                border = BorderStroke(MaterialTheme.spacing.dp_1, borderColor),
                shape = RoundedCornerShape(MaterialTheme.spacing.medium)
            )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesField(
    value: String,
    onNewValue: (String) -> Unit,
    focusManager: FocusManager,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onNewValue,
        placeholder = { },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() }
        ),
        shape = RoundedCornerShape(MaterialTheme.spacing.small),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = gray,
            textColor = gray
        ),
        modifier = modifier
    )
}