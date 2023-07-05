package com.example.agrican.ui.screens.home.profile.engineer_map.add_progress

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.agrican.R
import com.example.agrican.ui.components.BackButtonTopBar
import com.example.agrican.ui.navigation.NavigationDestination

object AddProgressDestination: NavigationDestination {
    override val route: String = "add_progress"
    override val titleRes: Int = R.string.add_progress
}

@Composable
fun AddProgressScreen(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackButtonTopBar(
        title = AddProgressDestination.titleRes,
        navigateUp = navigateUp,
        modifier = modifier
    ) {
        AddProgressScreenContent()
    }
}

@Composable
fun AddProgressScreenContent(
    modifier: Modifier = Modifier
) {
}

@Preview
@Composable
fun AddProgressScreenPreview() {
    AddProgressScreen(navigateUp = { })
}