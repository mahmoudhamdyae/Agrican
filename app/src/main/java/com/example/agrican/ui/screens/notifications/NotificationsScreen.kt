package com.example.agrican.ui.screens.notifications

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.agrican.R
import com.example.agrican.ui.components.BackButtonTopBar
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.greenDark

object NotificationsDestination: NavigationDestination {
    override val route: String = "notifications"
    override val titleRes: Int = R.string.notifications
}

@Composable
fun NotificationsScreen(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NotificationsViewModel = hiltViewModel()
) {

    BackButtonTopBar(
        title = NotificationsDestination.titleRes,
        navigateUp = navigateUp,
        modifier = modifier
    ) {
        NotificationsScreenContent()
    }
}

@Composable
fun NotificationsScreenContent(
    modifier: Modifier = Modifier
) {
    // Check if notifications is empty

    NotificationsEmpty()
}

@Composable
fun NotificationsEmpty(
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.notifications_empty),
            color = greenDark
        )
    }
}

@Preview
@Composable
fun NotificationsScreenPreview() {
    NotificationsScreenContent()
}