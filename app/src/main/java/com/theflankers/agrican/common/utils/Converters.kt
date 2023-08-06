package com.theflankers.agrican.common.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Composable
fun Dp.toPx() = with(LocalDensity.current) { this@toPx.toPx() }

fun millisecondsToTimeString(milliseconds: Int): String {
    var result = ""
    var secondsString = ""
    val hours = milliseconds / (1000 * 60 * 60)
    val minutes = (milliseconds % (1000 * 60 * 60)) / (1000 * 60)
    val seconds = (milliseconds % (1000 * 60 * 60) % (1000 * 60) / 1000)

    // Hours
    if (hours > 0) {
        result += "$hours:"
    }
    // Seconds
    secondsString += if (seconds < 10) {
        "0$seconds"
    } else {
        "$seconds"
    }
    
    result += "$result$minutes:$secondsString"

    return result
}