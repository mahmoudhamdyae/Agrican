package com.example.agrican.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.agrican.R
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.title
import com.example.agrican.ui.theme.white

@Composable
fun BackButtonTopBar(
    @StringRes title: Int,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {},
) {
    BackButton(
        navigateUp = navigateUp,
        modifier = modifier,
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(greenLight)
            ) {
                Text(
                    text = stringResource(id = title),
                    color = white,
                    style = MaterialTheme.typography.title,
                    modifier = Modifier
                        .padding(12.dp)
                        .align(Alignment.TopCenter)
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 60.dp)
                        .clip(RoundedCornerShape(25.dp))
                        .background(white)
                        .padding(top = 12.dp)
                ) {
                    content()
                }
            }
        }
    )
}

@Composable
fun BackButton(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(greenLight)
    ) {
        content()

        IconButton(
            onClick = navigateUp,
            modifier = Modifier
                .padding(12.dp)
                .clip(CircleShape)
                .background(white)
                .size(32.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
                contentDescription = null,
                tint = greenLight,
                modifier = Modifier.padding(9.dp)
            )
        }
    }
}

@Composable
fun GreenBackButton(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = navigateUp,
        modifier = modifier
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
}

@Preview
@Composable
fun BackButtonPreview() {
    BackButtonTopBar(
        title = R.string.problem_images,
        navigateUp = { }
    ) {
        Text(text = "Preview")
    }
}