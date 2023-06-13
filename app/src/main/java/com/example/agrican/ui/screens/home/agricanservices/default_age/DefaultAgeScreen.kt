package com.example.agrican.ui.screens.home.agricanservices.default_age

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.agrican.R
import com.example.agrican.common.enums.Quality
import com.example.agrican.common.utils.DateUtils
import com.example.agrican.domain.model.Crop
import com.example.agrican.ui.components.Chip
import com.example.agrican.ui.components.CropsList
import com.example.agrican.ui.components.DateDropDown
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.spacing
import java.time.LocalDate

object DefaultAgesDestination: NavigationDestination {
    override val route: String = "default_age"
    override val titleRes: Int = R.string.default_age
}

@Composable
fun DefaultAgeScreen(
    modifier: Modifier = Modifier,
    viewModel: DefaultAgeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    DefaultAgeScreenContent(
        uiState = uiState,
        updateDay = { viewModel.updateUiStates(day = it) },
        updateMonth = { viewModel.updateUiStates(month = it) },
        updateYear = { viewModel.updateUiStates(year = it) },
        updateCurrentCrop = { viewModel.updateUiStates(currentCrop = it) },
        updateCurrentQuality = { viewModel.updateUiStates(currentQuality = it) },
        getResults = viewModel::getResults,
        modifier = modifier
    )
}

@Composable
fun DefaultAgeScreenContent(
    uiState: DefaultAgeUiState,
    updateDay: (Int) -> Unit,
    updateMonth: (Int) -> Unit,
    updateYear: (Int) -> Unit,
    updateCurrentCrop: (Crop) -> Unit,
    updateCurrentQuality: (Int) -> Unit,
    getResults: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    // Declaring DatePickerDialog and setting
    // initial values as current values (present year, month and day)
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, day: Int ->
            updateDay(day)
            updateMonth(month)
            updateYear(year)
        }, LocalDate.now().year, LocalDate.now().monthValue, LocalDate.now().dayOfMonth
    )

    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        Text(
            text = stringResource(id = R.string.harvest_date),
            color = greenLight,
            modifier = Modifier.padding(MaterialTheme.spacing.small)
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = {
                    datePickerDialog.show()
                },
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
                    .clip(RoundedCornerShape(MaterialTheme.spacing.medium))
                    .background(greenLight)
            ) {
                Icon(
                    imageVector = Icons.Outlined.CalendarMonth,
                    contentDescription = null,
                    tint = Color.White
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
                modifier = Modifier
                    .padding(end = MaterialTheme.spacing.medium)
                    .height(MaterialTheme.spacing.large)
                    .weight(1f)
            ) {
                DateDropDown(
                    options = DateUtils().days,
                    onSelect = { if (it != 0) updateDay(it) },
                    selectedOption = uiState.day,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                )
                DateDropDown(
                    options = DateUtils().months,
                    onSelect = { if (it != 0) updateMonth(it) },
                    selectedOption = uiState.month,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                )
                DateDropDown(
                    options = DateUtils().years,
                    onSelect = { if (it != 0) updateYear(it) },
                    selectedOption = uiState.year,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                )
            }
        }


        Text(
            text = stringResource(id = R.string.crop_choose),
            color = greenLight,
            modifier = Modifier.padding(MaterialTheme.spacing.small)
        )

        CropsList(crops = uiState.crops, setSelectedCrop = { updateCurrentCrop(it) })

        Text(
            text = stringResource(id = R.string.crop_quality),
            color = greenLight,
            modifier = Modifier.padding(MaterialTheme.spacing.small)
        )

        var selected by rememberSaveable { mutableStateOf(0) }
        val qualities = listOf(
            Quality.VERY_GOOD,
            Quality.GOOD,
            Quality.AVERAGE,
            Quality.BELOW_AVERAGE,
        )

        LazyRow {
            items(qualities.size) {
                Chip(
                    text = qualities[it].title,
                    selected = it == selected,
                    onSelect = {
                        updateCurrentQuality(it)
                        selected = it
                    }
                )
            }
        }

        Button(
            onClick = getResults,
            colors = ButtonDefaults.buttonColors(containerColor = greenDark),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = stringResource(id = R.string.get_results),
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
            )
        }
        AnimatedVisibility(visible = uiState.defaultAge != null && uiState.dangerDegree != null) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                CircularProgress(
                    percent = uiState.defaultAge!!,
                    modifier = Modifier.padding(MaterialTheme.spacing.medium)
                )

                Text(text = stringResource(id = R.string.default_age_title))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(id = R.string.danger_degree),
                        modifier = Modifier.padding(MaterialTheme.spacing.small)
                    )

                    Indicators(
                        selectedItems = listOf(0, 1),
                        modifier = Modifier.padding(MaterialTheme.spacing.medium)
                    )

                    Spacer(modifier = Modifier.weight(1f))
                }

                Text(text = uiState.dangerAdvice)
            }
        }
    }
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
fun DefaultAgeScreenContent() {
    DefaultAgeScreenContent(uiState = DefaultAgeUiState(), { }, { }, { }, { }, { }, { })
}