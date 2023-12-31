package com.theflankers.agrican.ui.screens.home.main.ask_expert

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.theflankers.agrican.R
import com.theflankers.agrican.ui.navigation.NavigationDestination
import com.theflankers.agrican.ui.theme.body
import com.theflankers.agrican.ui.theme.greenDark
import com.theflankers.agrican.ui.theme.title
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object AskExpertDestination: NavigationDestination {
    override val route: String = "ask_expert"
    override val titleRes: Int = R.string.ask_expert
}

@Composable
fun AskExpertScreen(
    openAndPopUp: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = context) {
        scope.launch {
            // Start Chat after 3 Seconds
            delay(3000)
            // Open Chat Screen
            openAndPopUp(ChatDestination.route, AskExpertDestination.route)
        }
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.ask_expert_text_one),
            style = MaterialTheme.typography.title,
            fontSize = 16.sp
        )
        Text(
            text = stringResource(id = R.string.ask_expert_text_two),
            color = greenDark,
            style = MaterialTheme.typography.body,
            fontSize = 14.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AskExpertScreenPreview() {
    AskExpertScreen(openAndPopUp = { _, _ -> })
}