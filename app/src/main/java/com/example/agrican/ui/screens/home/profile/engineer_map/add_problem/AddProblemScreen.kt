package com.example.agrican.ui.screens.home.profile.engineer_map.add_problem

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.agrican.R
import com.example.agrican.ui.navigation.NavigationDestination

object AddProblemDestination: NavigationDestination {
    override val route: String = "add_problem"
    override val titleRes: Int = R.string.engineer_map
}

@Composable
fun AddProblemScreen(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AddProblemViewModel = hiltViewModel()
) {
    AddProblemScreenContent(
        addProblem = { viewModel.addProblem() },
        modifier = modifier
    )
}

@Composable
fun AddProblemScreenContent(
    addProblem: () -> Unit,
    modifier: Modifier = Modifier
) {
}

@Preview
@Composable
fun AddProblemScreenPreview() {
    AddProblemScreenContent(addProblem = { })
}