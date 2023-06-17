package com.example.agrican.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.spacing
import com.example.agrican.ui.theme.white

@Composable
fun Background(
    body1: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    body2: @Composable (() -> Unit)? = null,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(greenDark)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(if (body2 != null) 4f else 2f)
                .clip(
                    RoundedCornerShape(
                        bottomStart = MaterialTheme.spacing.extraLarge,
                        bottomEnd = MaterialTheme.spacing.extraLarge
                    )
                )
                .background(white)
                .fillMaxWidth()
        ) {
            body1()
        }
        Spacer(modifier = Modifier.weight(1f))
        if (body2 != null) {
            body2()
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Preview
@Composable
fun BackgroundPreview() {
    Background(body1 = {
        Text(text = "Agrican")
    })
}