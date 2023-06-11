package com.example.agrican.ui.screens.home.profile.add_task

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.agrican.R
import com.example.agrican.ui.components.Days
import com.example.agrican.ui.components.DropDown
import com.example.agrican.ui.components.ProfileHeader
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.screens.home.profile.add_farm.AddFarmDestination
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.spacing

object AddTaskDestination: NavigationDestination {
    override val route: String = "add_task"
    override val titleRes: Int = R.string.add_task
}

@Composable
fun AddTaskScreen(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AddTaskViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ProfileHeader(navigateUp = navigateUp, headerText = AddFarmDestination.titleRes) {
        AddTaskScreenContent(uiState = uiState, addTask = viewModel::addTask, modifier = modifier)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreenContent(
    uiState: AddTaskUiState,
    addTask: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(MaterialTheme.spacing.medium)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
        ) {
            Text(
                text = stringResource(id = R.string.task_name),
                color = greenDark
            )

            OutlinedTextField(
                value = uiState.taskName,
                onValueChange = { uiState.taskName = it },
                placeholder = { Text(text = stringResource(id = R.string.task_name))},
                shape = RoundedCornerShape(MaterialTheme.spacing.medium),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = gray,
                    textColor = gray
                ),
                modifier = Modifier.weight(1f)
            )

            DropDown(availabilityOptions = arrayOf(
                R.string.add_task
            ),
                onSelect = { /*TODO*/ },
                modifier = Modifier.width(130.dp)
            )
        }
        
        Text(
            text = stringResource(id = R.string.task_dates_label),
            color = greenDark,
            modifier = Modifier.padding(vertical = MaterialTheme.spacing.medium)
        )
        
        Days(selectedDays = listOf(9, 12, 25), onDayAdded = { /*TODO*/ })
        
        Button(
            onClick = addTask,
            colors = ButtonDefaults.buttonColors(containerColor = greenDark),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(MaterialTheme.spacing.medium)
        ) {
            Text(text = stringResource(id = R.string.add_task_button))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddTaskScreenPreview() {
    AddTaskScreenContent(uiState = AddTaskUiState(), addTask = { })
}