package com.example.agrican.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    val default: Dp = 0.dp,
    val extraSmall: Dp = 4.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 16.dp,
    val large: Dp = 32.dp,
    val extraLarge: Dp = 64.dp,

    val dp_1: Dp = 1.dp,
    val dp_2: Dp = 2.dp,
    val dp_3: Dp = 3.dp,
    val dp_10: Dp = 10.dp,
    val dp_20: Dp = 20.dp,
    val dp_24: Dp = 24.dp,
    val dp_40: Dp = 40.dp,
    val dp_50: Dp = 50.dp,
    val dp_75: Dp = 75.dp,
    val dp_80: Dp = 80.dp,
    val dp_100: Dp = 100.dp,
    val dp_130: Dp = 130.dp,
    val dp_150: Dp = 150.dp,
    val dp_200: Dp = 200.dp,
)

val LocalSpacing = compositionLocalOf { Spacing() }

val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current
