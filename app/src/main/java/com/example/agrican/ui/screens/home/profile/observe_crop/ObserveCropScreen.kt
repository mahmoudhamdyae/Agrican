package com.example.agrican.ui.screens.home.profile.observe_crop

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.agrican.R
import com.example.agrican.ui.navigation.NavigationDestination

object ObserveCropDestination: NavigationDestination {
    override val route: String = "observe_crop"
    override val titleRes: Int = R.string.observe_crop
}

@Composable
fun ObserveCropScreen(
    navigateUp: () -> Unit,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
}

@Preview(showBackground = true)
@Composable
fun ObserveCropScreenPreview() {
    ObserveCropScreen(navigateUp = { }, openScreen = { })
}