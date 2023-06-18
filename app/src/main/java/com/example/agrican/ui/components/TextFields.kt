package com.example.agrican.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.agrican.R
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.spacing

@Composable
fun UserNameField(
    value: String,
    onNewValue: (String) -> Unit,
    focusManager: FocusManager,
    userNameError: Int?,
    modifier: Modifier = Modifier,
) {
    AuthEditText(
        value = value,
        onNewValue = { onNewValue(it) },
        icon = Icons.Default.Person,
        hint = R.string.user_name_text_field,
        fieldError = userNameError,
        focusManager = focusManager,
        modifier = modifier
    )
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

    AuthEditText(
        value = value,
        onNewValue = { onNewValue(it) },
        icon = Icons.Default.Lock,
        hint = placeholder,
        fieldError = passwordError,
        focusManager = focusManager,
        keyboardType = KeyboardType.Password,
        imeAction = imeAction,
        visualTransformation = visualTransformation,
        trailingIcon = {
            IconButton(
                onClick = { isVisible = !isVisible },
                modifier = Modifier.height(MaterialTheme.spacing.dp_24)
            ) {
                Icon(painter = icon, contentDescription = "Visibility", tint = gray)
            }
        },
        modifier = modifier
    )
}

@Composable
fun PhoneNumberField(
    value: String,
    onNewValue: (String) -> Unit,
    focusManager: FocusManager,
    phoneNumberError: Int?,
    modifier: Modifier = Modifier,
) {
    AuthEditText(
        value = value,
        onNewValue = { onNewValue(it) },
        icon = Icons.Default.Person,
        hint = R.string.phone_number_text_field,
        fieldError = phoneNumberError,
        focusManager = focusManager,
        keyboardType = KeyboardType.Number,
        modifier = modifier
    )
}

@Composable
fun EmailField(
    value: String,
    onNewValue: (String) -> Unit,
    focusManager: FocusManager,
    emailError: Int?,
    modifier: Modifier = Modifier,
) {
    AuthEditText(
        value = value,
        onNewValue = { onNewValue(it) },
        icon = Icons.Default.Person,
        hint = R.string.email_text_field,
        fieldError = emailError,
        focusManager = focusManager,
        keyboardType = KeyboardType.Email,
        imeAction = ImeAction.Done,
        modifier = modifier
    )
}

@Composable
fun ConfirmSignUpField(
    value: String,
    onNewValue: (String) -> Unit,
    codeError: Int?,
    modifier: Modifier = Modifier,
    focusManager: FocusManager = LocalFocusManager.current,
) {
    AuthEditText(
        value = value,
        onNewValue = { onNewValue(it) },
        icon = null,
        hint = R.string.confirm_sign_up_text_field,
        fieldError = codeError,
        focusManager = focusManager,
        keyboardType = KeyboardType.Number,
        imeAction = ImeAction.Done,
        modifier = modifier
    )
}

@Composable
fun AuthEditText(
    value: String,
    onNewValue: (String) -> Unit,
    @StringRes hint: Int,
    @StringRes fieldError: Int?,
    focusManager: FocusManager,
    modifier: Modifier = Modifier,
    icon: ImageVector? = Icons.Default.Person,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    trailingIcon: @Composable () -> Unit = { },
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    BasicTextField(
        singleLine = true,
        value = value,
        onValueChange = { onNewValue(it) },
        decorationBox = { innerTextField ->

            Row(verticalAlignment = Alignment.CenterVertically) {
                icon?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = stringResource(id = hint),
                        tint = gray,
                        modifier = Modifier.padding(MaterialTheme.spacing.small)
                    )
                }

                if (value.isEmpty()) {
                    Text(
                        text = stringResource(hint),
                        color = gray,
                        fontSize = MaterialTheme.spacing.sp_12,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(MaterialTheme.spacing.small)
                    )
                } else {
                    Box(modifier = Modifier.padding(MaterialTheme.spacing.small)) {
                        innerTextField()
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                trailingIcon()
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) },
            onDone = { focusManager.clearFocus() }
        ),
        visualTransformation = visualTransformation,
        modifier = modifier
            .padding(MaterialTheme.spacing.small)
            .border(
                border = BorderStroke(
                    width = MaterialTheme.spacing.dp_1,
                    color = if (fieldError != null) MaterialTheme.colorScheme.error else gray
                ),
                shape = RoundedCornerShape(MaterialTheme.spacing.medium)
            )
    )
    if (fieldError != null) {
        Text(
            text = stringResource(id = fieldError),
            color = MaterialTheme.colorScheme.error,
            modifier = modifier.padding(horizontal = MaterialTheme.spacing.small)
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