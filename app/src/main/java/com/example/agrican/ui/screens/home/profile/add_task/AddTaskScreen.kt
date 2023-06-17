package com.example.agrican.ui.screens.home.profile.add_task

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.agrican.R
import com.example.agrican.ui.components.Days
import com.example.agrican.ui.components.DropDown
import com.example.agrican.ui.components.ProfileHeader
import com.example.agrican.ui.components.SimpleTextField
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.screens.home.profile.add_farm.AddFarmDestination
import com.example.agrican.ui.theme.body
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
    var taskName by rememberSaveable { mutableStateOf("") }

    ProfileHeader(navigateUp = navigateUp, headerText = AddFarmDestination.titleRes) {
        AddTaskScreenContent(
            days = uiState.days,
            flipDay = viewModel::flipDay,
            taskName = taskName,
            onTaskNameChanged = { taskName = it },
            addTask = viewModel::addTask,
            modifier = modifier
        )
    }
}

@Composable
fun AddTaskScreenContent(
    days: List<Int>,
    flipDay: (Int) -> Unit,
    taskName: String,
    onTaskNameChanged: (String) -> Unit,
    addTask: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(MaterialTheme.spacing.medium)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
            modifier = Modifier.height(MaterialTheme.spacing.dp_40)
        ) {
            Text(
                text = stringResource(id = R.string.task_name),
                color = greenDark,
                style = MaterialTheme.typography.body
            )

            // Task Name Text Field
            SimpleTextField(
                value = taskName,
                onNewValue = onTaskNameChanged,
                placeHolder = {
                    Box(modifier = Modifier.fillMaxHeight()) {
                        Text(
                            text = stringResource(id = R.string.task_name),
                            color = gray,
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(start = MaterialTheme.spacing.small)
                        )
                    } },
                modifier = Modifier.weight(1f)
            )

            DropDown(options = listOf(
                R.string.add_task
            ),
                onSelect = { /*TODO*/ },
                modifier = Modifier.width(MaterialTheme.spacing.dp_130)
            )
        }
        
        Text(
            text = stringResource(id = R.string.task_dates_label),
            color = greenDark,
            style = MaterialTheme.typography.body,
            modifier = Modifier.padding(vertical = MaterialTheme.spacing.medium)
        )

        Days(selectedDays = days, onDayClicked = { flipDay(it) })
        
        // Add Task Button
        Button(
            onClick = { addTask(taskName) },
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
    AddTaskScreenContent(days = listOf(), flipDay = { }, taskName = "", onTaskNameChanged = { }, addTask = { })
}