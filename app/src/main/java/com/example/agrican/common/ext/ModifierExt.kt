package com.example.agrican.common.ext

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.unit.Dp

fun Modifier.bottomElevation(paddingDp: Dp): Modifier = this.then(Modifier.drawWithContent {
    val paddingPx = paddingDp.toPx()
    clipRect(
        left = 0f,
        top = 0f,
        right = size.width,
        bottom = size.height + paddingPx
    ) {
        this@drawWithContent.drawContent()
    }
})