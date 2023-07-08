package com.example.agrican.ui.screens.home.profile.engineer_map.add_progress

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.agrican.R
import com.example.agrican.common.utils.DateUtils
import com.example.agrican.ui.components.BackButtonTopBar
import com.example.agrican.ui.components.DateDropDown
import com.example.agrican.ui.components.LabelItem
import com.example.agrican.ui.components.LabelWithTextField
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.textGray
import com.example.agrican.ui.theme.white

object AddProgressDestination: NavigationDestination {
    override val route: String = "add_progress"
    override val titleRes: Int = R.string.add_progress
}

@Composable
fun AddProgressScreen(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AddProgressViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BackButtonTopBar(
        title = AddProgressDestination.titleRes,
        navigateUp = navigateUp,
        modifier = modifier
    ) {
        AddProgressScreenContent(
            uiState = uiState,
            navigateUp = navigateUp,
            updateProgress = { viewModel.updateUiStates(progress = it) },
            updateDay = { viewModel.updateUiStates(day = it) },
            updateMonth = { viewModel.updateUiStates(month = it) },
            updateYear = { viewModel.updateUiStates(year = it) },
            addProgress = viewModel::addProgress
        )
    }
}

@Composable
fun AddProgressScreenContent(
    uiState: AddProgressUiState,
    navigateUp: () -> Unit,
    updateProgress: (String) -> Unit,
    updateDay: (Int) -> Unit,
    updateMonth: (Int) -> Unit,
    updateYear: (Int) -> Unit,
    addProgress: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(vertical = 8.dp, horizontal = 16.dp)
    ) {

        // Problem Kind
        ProgressItem(
            title = R.string.problem_kind,
            description = "تبقع الأوراق"
        )

        // Problem Description
        ProgressItem(
            title = R.string.problem_description,
            description = "تضرر الجزء السفلى من ساق النبات",
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // Problem Date
        ProgressItem(
            title = R.string.date,
            description = "12-12-2012"
        )

        // Add progress
        LabelWithTextField(
            value = uiState.progress,
            onNewValue = updateProgress,
            hint = R.string.progress_add_hint,
            label = R.string.progress_add,
            focusManager = LocalFocusManager.current,
            imeAction = ImeAction.Done
        )

        // Progress Date
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.height(IntrinsicSize.Max)
        ) {
            LabelItem(text = R.string.progress_date)

            // Days
            DateDropDown(
                options = DateUtils().days,
                onSelect = { if (it != 0) updateDay(it) },
                selectedOption = uiState.day,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            )
            // Months
            DateDropDown(
                options = DateUtils().months,
                onSelect = { if (it != 0) updateMonth(it) },
                selectedOption = uiState.month,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            )
            // Years
            DateDropDown(
                options = DateUtils().years,
                onSelect = { if (it != 0) updateYear(it) },
                selectedOption = uiState.year,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            )
        }

        // Add Button
        Button(
            onClick = {
                addProgress()
                navigateUp()
                      },
            colors = ButtonDefaults.buttonColors(containerColor = greenDark),
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.add),
                color = white,
                modifier = Modifier.padding(horizontal = 64.dp)
            )
        }
    }
}

@Composable
fun ProgressItem(
    @StringRes title: Int,
    description: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        // Title
        Text(
            text = stringResource(id = title),
            color = greenDark
        )

        // Description
        Text(
            text = description,
            color = textGray
        )
    }
}

@Preview
@Composable
fun AddProgressScreenPreview() {
    AddProgressScreenContent(
        uiState = AddProgressUiState(),
        navigateUp = { },
        {}, {}, {}, {}, {}
    )
}