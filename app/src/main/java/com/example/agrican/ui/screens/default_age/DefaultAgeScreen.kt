package com.example.agrican.ui.screens.default_age

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.agrican.R
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.spacing

object DefaultAgesDestination: NavigationDestination {
    override val route: String = "default_age"
    override val titleRes: Int = R.string.default_age
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultAgeScreen(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(MaterialTheme.spacing.medium)) {
        Text(
            text = stringResource(id = R.string.harvest_date),
            color = greenLight
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = {
                          // todo: open date picker
                },
                modifier = Modifier
                    .clip(RoundedCornerShape(MaterialTheme.spacing.medium))
                    .background(greenLight)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_visibility_on),
                    contentDescription = null,
                    tint = Color.White
                )
            }

            OutlinedTextField(
                readOnly = true,
                value = stringResource(id = R.string.day),
                onValueChange = { },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = false)
                },
                shape = RoundedCornerShape(MaterialTheme.spacing.medium),
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
                    .weight(1f)
                    .clickable {
                        // todo: open date picker
                    }
            )

            OutlinedTextField(
                readOnly = true,
                value = stringResource(id = R.string.month),
                onValueChange = { },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = false)
                },
                shape = RoundedCornerShape(MaterialTheme.spacing.medium),
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
                    .weight(1f)
                    .clickable {
                        // todo: open date picker
                    }
            )

            OutlinedTextField(
                readOnly = true,
                value = stringResource(id = R.string.year),
                onValueChange = { },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = false)
                },
                shape = RoundedCornerShape(MaterialTheme.spacing.medium),
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
                    .weight(1f)
                    .clickable {
                        // todo: open date picker
                    }
            )
        }

        Text(
            text = stringResource(id = R.string.crop_choose),
            color = greenLight
        )

        CropsList()

        Text(
            text = stringResource(id = R.string.crop_quality),
            color = greenLight
        )

        Row {
            var selected by rememberSaveable { mutableStateOf(0) }

            val qualities = listOf(
                R.string.quality_very_good,
                R.string.quality_good,
                R.string.quality_average,
                R.string.quality_below_average
            )
            LazyRow {
                items(qualities.size) {
                    QualityChips(
                        text = qualities[it],
                        selected = it == selected,
                        onSelect = { selected = it }
                    )
                }
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(containerColor = greenDark),
            ) {
                Text(
                    text = stringResource(id = R.string.get_results),
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
                )
            }

            CircularProgress(
                percent = 88f,
                modifier = Modifier.padding(MaterialTheme.spacing.medium)
            )

            Text(text = stringResource(id = R.string.default_age_title))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = stringResource(id = R.string.danger_degree))

                Indicators(
                    selectedItems = listOf(0, 1),
                    modifier = Modifier.padding(MaterialTheme.spacing.medium)
                )

                Spacer(modifier = Modifier.weight(1f))
            }

            Text(text = stringResource(id = R.string.danger_description_one))
            Text(text = stringResource(id = R.string.danger_description_two))
        }
    }
}

@Composable
fun CropsList(
    modifier: Modifier = Modifier
) {
    LazyRow(modifier = modifier) {
        items(9) {
            CropsListItem(modifier = Modifier.padding(MaterialTheme.spacing.small))
        }
    }
}

@Composable
fun CropsListItem(
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(MaterialTheme.spacing.large),
        shadowElevation = MaterialTheme.spacing.medium,
        modifier = modifier.size(75.dp)
    ) {
        Box {
            Icon(
                painter = painterResource(id = R.drawable.ic_visibility_on),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
            Text(text = "الأرز", modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QualityChips(
    text: Int,
    selected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier
) {
    FilterChip(
        selected = selected,
        onClick = onSelect,
        colors = FilterChipDefaults.elevatedFilterChipColors(
            selectedContainerColor = greenLight,
            selectedLabelColor = Color.White
        ),
        label = {
            Text(
                text = stringResource(id = text),
            )
        },
        shape = RoundedCornerShape(MaterialTheme.spacing.large),
        modifier = modifier.padding(MaterialTheme.spacing.small)
    )
}

@Composable
fun CircularProgress(
    percent: Float,
    modifier: Modifier = Modifier
) {

    val animatedProgress by animateFloatAsState(
        targetValue = percent / 100f,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    )

    Box(modifier = modifier) {
        CircularProgressIndicator(
            progress = 1f,
            color = LightGray,
            strokeWidth = 2.8.dp,
            modifier = Modifier.size(120.dp)
        )
        CircularProgressIndicator(
            progress = animatedProgress,
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(14.dp)),
            color = greenLight,
            strokeWidth = 2.8.dp
        )

        Text(
            text = "${percent.toInt()}%",
            modifier = Modifier.Companion.align(Alignment.Center)
        )
    }

}

@Composable
fun Indicators(
    selectedItems: List<Int>,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        modifier = modifier
    ) {
        repeat(10) {
            Indicator(isSelected = selectedItems.contains(it))
        }
    }
}

@Composable
fun Indicator(isSelected: Boolean) {
    Box(
        modifier = Modifier
            .size(10.dp)
            .clip(CircleShape)
            .background(
                color = if (isSelected) Red else gray
            )
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultAgeScreenPreview() {
    DefaultAgeScreen()
}