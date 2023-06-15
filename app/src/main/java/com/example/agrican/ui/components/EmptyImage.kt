package com.example.agrican.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.agrican.R
import com.example.agrican.ui.theme.gray

@Composable
fun EmptyImage(
    modifier: Modifier = Modifier,
    tint: Color = Color.White,
    background: Color = gray

) {
    Box(modifier = modifier.background(background)) {
        Icon(
            painter = painterResource(id = R.drawable.empty),
            contentDescription = null,
            tint = tint,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyImagePreview() {
    EmptyImage()
}