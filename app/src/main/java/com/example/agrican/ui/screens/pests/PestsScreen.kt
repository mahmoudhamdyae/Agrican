package com.example.agrican.ui.screens.pests

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.agrican.R
import com.example.agrican.ui.components.BackButton
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.screens.diseases.DiseasesScreenContent

object PestsDestination: NavigationDestination {
    override val route: String = "pests"
    override val titleRes: Int = R.string.pests
}

@Composable
fun PestsScreen(
    navigateUp: () -> Unit,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    BackButton(navigateUp = navigateUp) {
        DiseasesScreenContent(
            navigateUp = navigateUp,
            onItemClick = { openScreen(PestDestination.route) },
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PestsScreenPreview() {
    PestsScreen(navigateUp = { }, openScreen = { })
}