package com.example.agrican.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
    val dp_60: Dp = 60.dp,
    val dp_75: Dp = 75.dp,
    val dp_80: Dp = 80.dp,
    val dp_90: Dp = 90.dp,
    val dp_100: Dp = 100.dp,
    val dp_110: Dp = 110.dp,
    val dp_130: Dp = 130.dp,
    val dp_150: Dp = 150.dp,
    val dp_200: Dp = 200.dp,

    val sp_10: TextUnit = 10.sp,
    val sp_11: TextUnit = 11.sp,
    val sp_12: TextUnit = 12.sp,
    val sp_13: TextUnit = 13.sp,
    val sp_14: TextUnit = 14.sp,
    val sp_15: TextUnit = 15.sp,
    val sp_16: TextUnit = 16.sp,
    val sp_17: TextUnit = 17.sp,
    val sp_18: TextUnit = 18.sp,
    val sp_20: TextUnit = 20.sp,
    val sp_25: TextUnit = 25.sp,
    val sp_30: TextUnit = 30.sp,
    val sp_32: TextUnit = 32.sp,
    val sp_62: TextUnit = 62.sp,
)

val LocalSpacing = compositionLocalOf { Spacing() }

val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current
