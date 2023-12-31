package com.theflankers.agrican.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.theflankers.agrican.R
import com.theflankers.agrican.ui.theme.gray
import com.theflankers.agrican.ui.theme.white

@Composable
fun EmptyImage(
    modifier: Modifier = Modifier,
    tint: Color = white,
    background: Color = gray

) {
    Box(modifier = modifier.background(background).fillMaxSize()) {
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