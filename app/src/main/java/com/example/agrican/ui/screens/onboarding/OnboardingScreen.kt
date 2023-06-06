package com.example.agrican.ui.screens.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.agrican.R
import com.example.agrican.ui.navigation.NavigationDestination

object OnboardingDestination: NavigationDestination {
    override val route: String = "onboarding"
    override val titleRes: Int = R.string.app_name
}

@Composable
fun OnboardingScreen(
    openAndClear: (String) -> Unit,
    modifier: Modifier = Modifier
) {
}

@Preview
@Composable
fun OnboardingScreenPreview() {
    OnboardingScreen(
        openAndClear = { }
    )
}