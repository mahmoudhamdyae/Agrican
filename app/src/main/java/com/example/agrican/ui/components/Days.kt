package com.example.agrican.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.agrican.R
import com.example.agrican.common.utils.toPx
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.white

@Composable
fun Days(
    selectedDays: List<Int>,
    onDayClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
                    contentDescription = null,
                    tint = greenDark,
                    modifier = Modifier.size(18.dp)
                )
            }

            Text(
                text = "يونيو",
                color = greenDark
            )
            Text(
                text = "2023",
                color = greenDark
            )

            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
                    contentDescription = null,
                    tint = greenDark,
                    modifier = Modifier.size(18.dp)
                )
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            modifier = modifier
        ) {
            items(count = 31, key = { it }) {
                DayItem(
                    day = it + 1,
                    selected = selectedDays.contains(it + 1),
                    onItemClicked = onDayClicked,
                    modifier = Modifier.padding(2.dp)
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
    val stroke = Stroke(
        width = 2.dp.toPx(),
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(6.dp.toPx(), 6.dp.toPx()), 0.dp.toPx())
    )

    Surface(
        color = if (selected) greenDark else white,
        modifier = modifier
            .height(55.dp)
            .clickable { onItemClicked(day) }
            .drawBehind { drawRoundRect(color = greenDark, style = stroke) }
    ) {
        Text(
            text = day.toString(),
            color = if (selected) white else greenDark,
            fontSize = 15.sp,
            modifier = Modifier.padding(
                vertical = 4.dp,
                horizontal = 8.dp
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DaysPreview() {
    Days(selectedDays = listOf(9, 12, 25), onDayClicked = { })
}