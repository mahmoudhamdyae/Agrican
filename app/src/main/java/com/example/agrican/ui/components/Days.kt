package com.example.agrican.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Days(
    selectedDays: List<Int>,
    onDayAdded: () -> Unit,
    modifier: Modifier = Modifier
) {
    // todo: Calendar
}

@Preview
@Composable
fun DaysPreview() {
    Days(selectedDays = listOf(9, 12, 25), onDayAdded = { })
}