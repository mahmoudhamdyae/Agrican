package com.example.agrican.ui.screens.home.agricanservices.default_age

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.agrican.R
import com.example.agrican.ui.components.Chip
import com.example.agrican.ui.components.CropsList
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.spacing

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
        getResults = viewModel::getResults,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultAgeScreenContent(
    uiState: DefaultAgeUiState,
    getResults: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(MaterialTheme.spacing.medium)
            .verticalScroll(rememberScrollState())
    ) {
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

        CropsList(crops = uiState.crops, setSelectedCrop = { uiState.currentCrop = it })

        Text(
            text = stringResource(id = R.string.crop_quality),
            color = greenLight
        )

        var selected by rememberSaveable { mutableStateOf(0) }
        val qualities = listOf(
            R.string.quality_very_good,
            R.string.quality_good,
            R.string.quality_average,
            R.string.quality_below_average
        )
        val context = LocalContext.current
        LazyRow {
            items(qualities.size) {
                Chip(
                    text = qualities[it],
                    selected = it == selected,
                    onSelect = {
                        uiState.currentQuality = context.getString(qualities[it])
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
                    Text(text = stringResource(id = R.string.danger_degree))

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
    DefaultAgeScreenContent(uiState = DefaultAgeUiState(), getResults = { })
}