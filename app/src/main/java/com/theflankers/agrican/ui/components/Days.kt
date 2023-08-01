package com.theflankers.agrican.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.theflankers.agrican.R
import com.theflankers.agrican.common.utils.getNumberOfDaysInMonth
import com.theflankers.agrican.common.utils.toMonth
import com.theflankers.agrican.common.utils.toPx
import com.theflankers.agrican.ui.theme.greenDark
import com.theflankers.agrican.ui.theme.white
import java.time.LocalDate

@Composable
fun Days(
    selectedDays: List<LocalDate>,
    onDayClicked: (LocalDate) -> Unit,
    modifier: Modifier = Modifier
) {
    val now = LocalDate.now()
    var month by rememberSaveable { mutableStateOf(now.monthValue) }
    var year by rememberSaveable { mutableStateOf(now.year) }

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            IconButton(
                onClick = {
                    if (month == 1) {
                        month = 12
                        year--
                    } else {
                        month--
                    }
                          },
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
                text = stringResource(id = month.toMonth()),
                color = greenDark
            )
            Text(
                text = year.toString(),
                color = greenDark
            )

            IconButton(
                onClick = {
                    if (month == 12) {
                        month = 1
                        year++
                    } else {
                        month++
                    }
                          },
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

        repeat(5) { row ->
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                repeat(7) { column ->
                    val currentDay = column + 1 + row * 7
                    if (currentDay < month.getNumberOfDaysInMonth() + 1) {
                        DayItem(
                            day = currentDay,
                            selected = selectedDays.contains(LocalDate.of(year, month, currentDay)),
                            onItemClicked = {
                                onDayClicked(LocalDate.of(year, month, it))
                            },
                            modifier = Modifier
                                .weight(1f)
                                .padding(2.dp)
                        )
                    } else {
                        Box(modifier = Modifier.weight(1f))
                    }
                }
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
            .drawBehind {
                if (!selected) {
                    drawRoundRect(color = greenDark, style = stroke)
                } else {
                    drawRoundRect(color = greenDark)
                }
            }
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
    Days(
        selectedDays = listOf(
            LocalDate.of(2023, 7, 9),
            LocalDate.of(2023, 7, 12),
            LocalDate.of(2023, 7, 25))
        ,
        onDayClicked = { }
    )
}