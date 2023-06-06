package com.example.agrican.ui.screens.auth.signup

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.agrican.R
import com.example.agrican.ui.navigation.NavigationDestination

object SignupDestination: NavigationDestination {
    override val route: String = "signup"
    override val titleRes: Int = R.string.app_name
}

@Composable
fun SignupScreen(
    openAndClear: (String) -> Unit,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignupViewModel = hiltViewModel()
) {
}