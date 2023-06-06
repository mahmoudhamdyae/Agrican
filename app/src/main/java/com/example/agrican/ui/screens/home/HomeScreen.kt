package com.example.agrican.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.agrican.R
import com.example.agrican.ui.navigation.NavigationDestination

object HomeDestination: NavigationDestination {
    override val route: String = "home"
    override val titleRes: Int = R.string.app_name
}

@Composable
fun HomeScreen(
    openAndClear: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.initialize(openAndClear)
    }

    HomeScreenContent(
        modifier = modifier,
    )
}

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier
) {
}

@Preview
@Composable
fun HomeScreenContentPreview() {
    HomeScreenContent()
}