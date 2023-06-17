package com.example.agrican.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.spacing
import com.example.agrican.ui.theme.white

@Composable
fun Days(
    selectedDays: List<Int>,
    onDayClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        modifier = modifier
    ) {
        repeat(31) {
            item {
                DayItem(
                    day = it + 1,
                    selected = selectedDays.contains(it + 1),
                    onItemClicked = onDayClicked,
                    modifier = Modifier.padding(MaterialTheme.spacing.extraSmall)
                )
            }
        }
    }
}

@Composable
fun DayItem(
    day: Int,
    selected: Boolean,
    onItemClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        border = BorderStroke(MaterialTheme.spacing.dp_1, gray),
        color = if (selected) greenLight else white,
        modifier = modifier.height(MaterialTheme.spacing.extraLarge).clickable {
            onItemClicked(day)
        }
    ) {
        Text(
            text = day.toString(),
            color = if (selected) white else greenDark,
            fontSize = MaterialTheme.spacing.sp_15,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(
                vertical = MaterialTheme.spacing.extraSmall,
                horizontal = MaterialTheme.spacing.small
            )
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DaysPreview() {
    Days(selectedDays = listOf(9, 12, 25), onDayClicked = { })
}