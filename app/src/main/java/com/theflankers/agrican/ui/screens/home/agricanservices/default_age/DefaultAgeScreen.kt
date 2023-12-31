package com.theflankers.agrican.ui.screens.home.agricanservices.default_age

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.theflankers.agrican.R
import com.theflankers.agrican.common.enums.Quality
import com.theflankers.agrican.common.utils.DateUtils
import com.theflankers.agrican.domain.model.Crop
import com.theflankers.agrican.ui.components.BackButtonTopBar
import com.theflankers.agrican.ui.components.CalenderDropDown
import com.theflankers.agrican.ui.components.Chip
import com.theflankers.agrican.ui.components.CropsList
import com.theflankers.agrican.ui.components.DateDropDown
import com.theflankers.agrican.ui.navigation.NavigationDestination
import com.theflankers.agrican.ui.theme.body
import com.theflankers.agrican.ui.theme.gray
import com.theflankers.agrican.ui.theme.greenDark
import com.theflankers.agrican.ui.theme.greenLight
import com.theflankers.agrican.ui.theme.red
import com.theflankers.agrican.ui.theme.textGray
import com.theflankers.agrican.ui.theme.title
import com.theflankers.agrican.ui.theme.white
import java.time.LocalDate

object DefaultAgesDestination: NavigationDestination {
    override val route: String = "default_age"
    override val titleRes: Int = R.string.default_age
}

@Composable
fun DefaultAgeScreen(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DefaultAgeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BackButtonTopBar(
        title = DefaultAgesDestination.titleRes,
        navigateUp = navigateUp,
        modifier = modifier,
    ) {
        DefaultAgeScreenContent(
            uiState = uiState,
            updateDay = { viewModel.updateUiStates(day = it) },
            updateMonth = { viewModel.updateUiStates(month = it) },
            updateYear = { viewModel.updateUiStates(year = it) },
            updateCurrentCrop = { viewModel.updateUiStates(currentCrop = it) },
            updateCurrentQuality = { viewModel.updateUiStates(currentQuality = it) },
            getResults = viewModel::getResults,
            modifier = Modifier.padding(bottom = 60.dp)
        )
    }
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
        // Harvest Date
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Harvest Date Label
            Text(
                text = stringResource(id = R.string.harvest_date),
                style = MaterialTheme.typography.title,
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .padding(end = 16.dp)
                    .height(32.dp)
                    .weight(1f)
            ) {
                // Days Drop Down
                DateDropDown(
                    options = DateUtils().days(uiState.month, uiState.year),
                    onSelect = updateDay,
                    selectedOption = uiState.day,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                )
                // Months Drop Down
                DateDropDown(
                    options = DateUtils().months,
                    onSelect = updateMonth,
                    selectedOption = uiState.month,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                )
                // Years Drop Down
                DateDropDown(
                    options = DateUtils().years,
                    onSelect = updateYear,
                    selectedOption = uiState.year,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                )
            }

            // Calender Button
            CalenderDropDown(
                onDayClicked = {
                    updateDay(it.dayOfMonth)
                    updateMonth(it.monthValue)
                    updateYear(it.year)
                },
                modifier = Modifier.padding(end = 16.dp)
            )
        }

        // Choose Crop Label
        Text(
            text = stringResource(id = R.string.crop_choose),
            style = MaterialTheme.typography.title,
            fontSize = 16.sp,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        )

        CropsList(
            isLoading = uiState.isLoading,
            crops = uiState.crops,
            setSelectedCrop = { updateCurrentCrop(it) }
        )

        // Crop Quality Label
        Text(
            text = stringResource(id = R.string.crop_quality),
            style = MaterialTheme.typography.title,
            fontSize = 16.sp,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        )

        var selected by rememberSaveable { mutableStateOf(0) }
        val qualities = listOf(
            Quality.VERY_GOOD,
            Quality.GOOD,
            Quality.AVERAGE,
            Quality.BELOW_AVERAGE,
        )

        // Qualities Lazy Row
        LazyRow(modifier = Modifier.padding(horizontal = 8.dp)) {
            items(qualities.size) {
                Chip(
                    text = qualities[it].title,
                    selected = it == selected,
                    textColor = if (selected == it) white else textGray,
                    onSelect = {
                        updateCurrentQuality(it)
                        selected = it
                    },
                    borderColor = if (it == selected) greenLight else gray,
                    modifier= Modifier
                        .width(100.dp)
                        .padding(vertical = 8.dp, horizontal = 4.dp)
                )
            }
        }

        // Results Button
        Button(
            onClick = getResults,
            colors = ButtonDefaults.buttonColors(containerColor = greenDark),
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .height(38.dp)
                .width(225.dp)
        ) {
            Text(
                text = stringResource(id = R.string.get_results),
                fontSize = 15.sp
            )
        }
        AnimatedVisibility(visible = uiState.defaultAge != null && uiState.dangerDegree != null) {
            DefaultAgeResponse(
                defaultAge = uiState.defaultAge,
                dangerAdvice = uiState.dangerAdvice
            )
        }
    }
}

@Composable
fun DefaultAgeResponse(
    defaultAge: Float?,
    dangerAdvice: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {

        CircularProgress(
            percent = defaultAge!!,
            modifier = Modifier.padding(16.dp)
        )

        Text(
            text = stringResource(id = R.string.default_age_title),
            style = MaterialTheme.typography.body,
            fontSize = 14.sp,
        )

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.danger_degree),
                style = MaterialTheme.typography.body,
                fontSize = 14.sp,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            )

            Indicators(
                selectedItems = listOf(0, 1),
                modifier = Modifier.padding(16.dp)
            )
        }

        // Advice
        Text(
            text = dangerAdvice,
            style = MaterialTheme.typography.body,
            fontSize = 11.sp
        )

        Spacer(modifier = Modifier.height(35.dp))
    }
}


@Composable
fun CircularProgress(
    percent: Float,
    modifier: Modifier = Modifier
) {

    val animatedProgress by animateFloatAsState(
        targetValue = percent / 100f,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec, label = ""
    )

    Box(modifier = modifier) {
        // Gray Indicator
        CircularProgressIndicator(
            progress = 1f,
            color = LightGray,
            strokeWidth = 3.dp,
            modifier = Modifier.size(130.dp)
        )
        // Progress Indicator
        CircularProgressIndicator(
            progress = animatedProgress,
            modifier = Modifier
                .size(130.dp)
                .clip(RoundedCornerShape(16.dp)),
            color = greenLight,
            strokeWidth = 3.dp
        )

        // Progress Label
        Text(
            text = "${percent.toInt()}%",
            style = MaterialTheme.typography.body,
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold,
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
        horizontalArrangement = Arrangement.spacedBy(8.dp),
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
                color = if (isSelected) red else gray
            )
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultAgeScreenContent() {
    DefaultAgeScreenContent(uiState = DefaultAgeUiState(), { }, { }, { }, { }, { }, { })
}

@Preview(showBackground = true)
@Composable
fun DefaultAgeResponsePreview() {
    DefaultAgeResponse(defaultAge = 88f, dangerAdvice = "Danger Advice")
}