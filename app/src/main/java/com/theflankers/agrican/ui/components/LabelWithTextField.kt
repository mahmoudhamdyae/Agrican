package com.theflankers.agrican.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.theflankers.agrican.R
import com.theflankers.agrican.common.utils.toPx
import com.theflankers.agrican.ui.theme.body
import com.theflankers.agrican.ui.theme.gray
import com.theflankers.agrican.ui.theme.greenDark

@Composable
fun LabelWithTextField(
    value: String,
    onNewValue: (String) -> Unit,
    hint: Int?,
    label: Int,
    focusManager: FocusManager,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    Box(
        modifier = modifier.height(IntrinsicSize.Max)
    ) {
        SimpleTextField(
            value = value,
            onNewValue = onNewValue,
            placeHolder = {
                if (hint != null) {
                    Text(
                        text = stringResource(hint),
                        color = gray,
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(8.dp)
                    )
                }
                          },
            focusManager = focusManager,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 75.dp),
            keyboardType = keyboardType,
            imeAction = imeAction,
            visualTransformation = visualTransformation
        )
        LabelItem(text = label, modifier = Modifier.fillMaxHeight())
    }
}

@Composable
fun LabelItem(
    text: Int,
    modifier: Modifier = Modifier
) {
    val stroke = Stroke(
        width = 2.dp.toPx(),
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(6.dp.toPx(), 6.dp.toPx()), 0.dp.toPx())
    )

    Surface(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .width(100.dp)
            .drawBehind {
                drawRoundRect(
                    color = greenDark,
                    style = stroke,
                    cornerRadius = CornerRadius(16.dp.toPx(), 16.dp.toPx())
                )
            }
    ) {
        Text(
            text = stringResource(id = text),
            textAlign = TextAlign.Center,
            color = greenDark,
            style = MaterialTheme.typography.body,
            fontSize = 12.sp,
            modifier = Modifier.padding(vertical = 8.dp)
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