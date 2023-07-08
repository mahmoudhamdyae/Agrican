package com.example.agrican.ui.screens.home.profile.engineer_map.add_problem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.agrican.ui.screens.home.profile.engineer_map.MapScreen
import com.example.agrican.ui.theme.black
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.white

object AddProblemDestination: NavigationDestination {
    override val route: String = "add_problem"
    override val titleRes: Int = R.string.add_problem
}

@Composable
fun AddProblemScreen(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AddProblemViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BackButtonTopBar(
        title = AddProblemDestination.titleRes,
        navigateUp = navigateUp
    ) {
        AddProblemScreenContent(
            uiState = uiState,
            addProblem = viewModel::addProblem,
            navigateUp = navigateUp,
            updateProblemKind = { viewModel.updateUiStates(problemKind = it) },
            updateProblemDescription = { viewModel.updateUiStates(problemDescription = it) },
            updateDay = { viewModel.updateUiStates(day = it) },
            updateMonth = { viewModel.updateUiStates(month = it) },
            updateYear = { viewModel.updateUiStates(year = it) },
            modifier = modifier
        )
    }
}

@Composable
fun AddProblemScreenContent(
    uiState: AddProblemUiState,
    addProblem: () -> Unit,
    navigateUp: () -> Unit,
    updateProblemKind: (String) -> Unit,
    updateProblemDescription: (String) -> Unit,
    updateDay: (Int) -> Unit,
    updateMonth: (Int) -> Unit,
    updateYear: (Int) -> Unit,
    modifier: Modifier = Modifier
) {

    val focusManager = LocalFocusManager.current

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(16.dp)
    ) {
        LabelWithTextField(
            value = uiState.problemKind,
            onNewValue = updateProblemKind,
            hint = R.string.problem_kind_hint,
            label = R.string.problem_kind,
            focusManager = focusManager
        )

        LabelWithTextField(
            value = uiState.problemDescription,
            onNewValue = updateProblemDescription,
            hint = R.string.problem_description_hint,
            label = R.string.problem_description,
            focusManager = focusManager,
            imeAction = ImeAction.Done
        )

        // Problem Date
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.height(IntrinsicSize.Max)
        ) {
            LabelItem(text = R.string.farming_date)

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

        // Choose Problem Label
        Text(
            text = stringResource(id = R.string.choose_problem_place),
            color = white
        )

        MapScreen(
            modifier = Modifier
                .clip(RoundedCornerShape(64.dp))
                .fillMaxWidth()
                .weight(1f)
                .background(black)
        )

        // Add Button
        Button(
            onClick = {
                addProblem()
                navigateUp()
            },
            colors = ButtonDefaults.buttonColors(containerColor = greenDark),
        ) {
            Text(
                text = stringResource(id = R.string.add),
                color = white,
                modifier = Modifier.padding(horizontal = 64.dp)
            )
        }
    }
}

@Preview
@Composable
fun AddProblemScreenPreview() {
    AddProblemScreenContent(
        uiState = AddProblemUiState(),
        addProblem = { },
        navigateUp = { },
        {}, {}, {}, {}, {}
    )
}