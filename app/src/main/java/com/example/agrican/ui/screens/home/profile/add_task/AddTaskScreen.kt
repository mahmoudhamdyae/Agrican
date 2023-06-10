package com.example.agrican.ui.screens.home.profile.add_task

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.agrican.R
import com.example.agrican.ui.navigation.NavigationDestination

object AddTaskDestination: NavigationDestination {
    override val route: String = "add_task"
    override val titleRes: Int = R.string.add_task
}

@Composable
fun AddTaskScreen(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
}

@Preview(showBackground = true)
@Composable
fun AddTaskScreenPreview() {
    AddTaskScreen(navigateUp = { })
}