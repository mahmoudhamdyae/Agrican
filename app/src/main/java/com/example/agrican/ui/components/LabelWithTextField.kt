package com.example.agrican.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.agrican.R
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.spacing

@Composable
fun LabelWithTextField(
    value: String,
    onNewValue: (String) -> Unit,
    hint: Int,
    label: Int,
    focusManager: FocusManager,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next
) {
    Box(
        modifier = modifier.height(IntrinsicSize.Max)
    ) {
        SimpleTextField(
            value = value,
            onNewValue = onNewValue,
            placeHolder = {
                Text(
                    text = stringResource(hint),
                    color = gray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(MaterialTheme.spacing.small)
                )
                          },
            focusManager = focusManager,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 70.dp),
            keyboardType = keyboardType,
            imeAction = imeAction
        )
        LabelItem(text = label, modifier = Modifier.fillMaxHeight())
    }
}

@Composable
fun LabelItem(
    text: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(MaterialTheme.spacing.medium),
        border = BorderStroke(1.dp, greenDark),
        modifier = modifier.width(100.dp)
    ) {
        Text(
            text = stringResource(id = text),
            textAlign = TextAlign.Center,
            color = greenDark,
            modifier = Modifier.padding(vertical = MaterialTheme.spacing.small)
        )
    }
}

@Preview
@Composable
fun LabelWithTextFieldPreview() {
    LabelWithTextField(
        value = "",
        onNewValue = { },
        hint = R.string.app_name,
        label = R.string.app_name,
        focusManager = LocalFocusManager.current)
}