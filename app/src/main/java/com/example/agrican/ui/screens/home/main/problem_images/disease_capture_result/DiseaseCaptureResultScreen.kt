package com.example.agrican.ui.screens.home.main.problem_images.disease_capture_result

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.agrican.R
import com.example.agrican.ui.components.EmptyImage
import com.example.agrican.ui.components.SimpleTextField
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.screens.home.main.MainDestination
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.white

object DiseaseCaptureResultDestination: NavigationDestination {
    override val route: String = "disease_capture_result"
    override val titleRes: Int = R.string.problem_images
}

@Composable
fun DiseaseCaptureResultScreen(
    navigateUp: () -> Unit,
    openAndClear: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    DiseaseCaptureResultScreenContent(
        navigateUp = navigateUp,
        openAndClear = openAndClear,
        modifier = modifier
    )
}

@Composable
fun DiseaseCaptureResultScreenContent(
    navigateUp: () -> Unit,
    openAndClear: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var plantName by rememberSaveable { mutableStateOf("") }
    var diseaseName by rememberSaveable { mutableStateOf("") }
    var treatment by rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(12.dp)
    ) {
        BackButton(navigateUp = navigateUp)

        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ItemRow(
                title = R.string.plant_name,
                value = plantName,
                setValue = { plantName = it },
                focusManager = focusManager,
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
            )
            ItemIcon()
        }

        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ItemRow(
                title = R.string.disease_name,
                value = diseaseName,
                setValue = { diseaseName = it },
                focusManager = focusManager,
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
            )
            ItemIcon()
        }

        ItemRow(
            title = R.string.treatment,
            value = treatment,
            setValue = { treatment = it },
            focusManager = focusManager,
            imeAction = ImeAction.Done,
            modifier = Modifier.height(120.dp)
        )

        ReturnToMainButton(
            openAndClear = openAndClear,
            modifier = Modifier
                .padding(top = 8.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun BackButton(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = navigateUp,
        modifier = modifier
            .clip(CircleShape)
            .background(greenLight)
            .size(32.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
            contentDescription = null,
            tint = white,
            modifier = Modifier.padding(9.dp)
        )
    }
}

@Composable
fun ReturnToMainButton(
    openAndClear: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { openAndClear(MainDestination.route) },
        colors = ButtonDefaults.buttonColors(containerColor = greenDark),
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.return_to_main_button),
                fontSize = 14.sp
            )

            Icon(
                painter = painterResource(id = R.drawable.main),
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Composable
fun ItemRow(
    @StringRes title: Int,
    value: String,
    setValue: (String) -> Unit,
    focusManager: FocusManager,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Next
) {
    Box(modifier = modifier) {
        SimpleTextField(
            value = value,
            onNewValue = setValue,
            placeHolder = { },
            borderColor = greenLight,
            focusManager = focusManager,
            imeAction = imeAction,
            modifier = Modifier.padding(top = 8.dp).fillMaxHeight()
        )

        TitleSurface(
            title = title
        )
    }
}

@Composable
fun TitleSurface(
    @StringRes title: Int,
    modifier: Modifier = Modifier
) {
    val stroke = Stroke(
        width = 2f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(12f, 8f), 0f)
    )

    Surface(
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .padding(start = 16.dp)
            .width(90.dp)
            .drawBehind {
                drawRoundRect(
                    color = greenLight,
                    style = stroke,
                    cornerRadius = CornerRadius(12.dp.toPx(), 12.dp.toPx())
                )
            }
    ) {
        Text(
            text = stringResource(id = title),
            textAlign = TextAlign.Center,
            fontSize = 13.sp,
            modifier = Modifier.padding(vertical = 2.dp)
        )
    }
}

@Composable
fun ItemIcon(
    modifier: Modifier = Modifier
) {
    EmptyImage(
        tint = Color(0xffcbcbcb),
        background = Color(0xffe2e2e2),
        modifier = modifier
            .size(52.dp)
            .clip(RoundedCornerShape(12.dp))
    )
}

@Preview
@Composable
fun DiseaseCaptureResultScreenPreview() {
    DiseaseCaptureResultScreenContent(navigateUp = { }, openAndClear = { })
}