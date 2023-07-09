package com.example.agrican.ui.screens.home.profile.add_task

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.agrican.R
import com.example.agrican.common.utils.toPx
import com.example.agrican.ui.components.BackButtonTopBar
import com.example.agrican.ui.components.Days
import com.example.agrican.ui.components.DropDown
import com.example.agrican.ui.components.LabelWithTextField
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.body
import com.example.agrican.ui.theme.greenDark

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

    BackButtonTopBar(
        title = AddTaskDestination.titleRes,
        navigateUp = navigateUp,
        modifier = modifier
    ) {
        AddTaskScreenContent(
            days = uiState.days,
            flipDay = viewModel::flipDay,
            taskName = taskName,
            onTaskNameChanged = { taskName = it },
            addTask = viewModel::addTask,
            navigateUp = navigateUp
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
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.height(40.dp)
        ) {
            LabelWithTextField(
                value = taskName,
                onNewValue = onTaskNameChanged,
                hint = R.string.task_name,
                label = R.string.task_name,
                focusManager = LocalFocusManager.current,
                modifier = Modifier.weight(1f)
            )

            DropDown(
                options = listOf(
                    R.string.add_task
                ),
                onSelect = { /*TODO*/ },
                textColor = greenDark,
                modifier = Modifier.width(130.dp)
            )
        }

        val stroke = Stroke(
            width = 2.dp.toPx(),
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(12.dp.toPx(), 8.dp.toPx()), 0.dp.toPx())
        )

        Surface(
            shape = RoundedCornerShape(16.dp),
            modifier = modifier
                .padding(vertical = 16.dp)
                .drawBehind {
                    drawRoundRect(
                        color = greenDark,
                        style = stroke,
                        cornerRadius = CornerRadius(16.dp.toPx(), 16.dp.toPx())
                    )
                }
        ) {
            Text(
                text = stringResource(id = R.string.task_dates_label),
                textAlign = TextAlign.Center,
                color = greenDark,
                style = MaterialTheme.typography.body,
                fontSize = 12.sp,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp)
            )
        }

        Days(selectedDays = days, onDayClicked = { flipDay(it) })
        
        // Add Task Button
        Button(
            onClick = {
                addTask(taskName)
                navigateUp()
                      },
            colors = ButtonDefaults.buttonColors(containerColor = greenDark),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
                .height(38.dp)
                .width(225.dp)
        ) {
            Text(
                text = stringResource(id = R.string.add_task_button),
                fontSize = 15.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddTaskScreenPreview() {
    AddTaskScreenContent(
        days = listOf(),
        flipDay = { },
        taskName = "",
        onTaskNameChanged = { },
        addTask = { },
        navigateUp = { }
    )
}