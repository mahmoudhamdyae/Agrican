package com.example.agrican.ui.screens.home.main.problem_images

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.agrican.R
import com.example.agrican.ui.components.BackButton
import com.example.agrican.ui.navigation.NavigationDestination

object ProblemImagesDestination: NavigationDestination {
    override val route: String = "problem_images"
    override val titleRes: Int = R.string.problem_images
}

@Composable
fun ProblemImagesScreen(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {

    BackButton(navigateUp = navigateUp) {
    }
}

@Preview(showBackground = true)
@Composable
fun ProblemImageScreenPreview() {
    ProblemImagesScreen(navigateUp = { })
}