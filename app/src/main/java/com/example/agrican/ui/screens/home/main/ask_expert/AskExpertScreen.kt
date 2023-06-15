package com.example.agrican.ui.screens.home.main.ask_expert

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.agrican.R
import com.example.agrican.ui.components.BackButton
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.greenLight
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object AskExpertDestination: NavigationDestination {
    override val route: String = "ask_expert"
    override val titleRes: Int = R.string.ask_expert
}

@Composable
fun AskExpertScreen(
    navigateUp: () -> Unit,
    openAndPopUp: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {

    // todo: change this
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

    BackButton(navigateUp = navigateUp) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.fillMaxSize()
        ) {
            Text(
                text = stringResource(id = R.string.ask_expert_text_one),
                fontWeight = FontWeight.Bold,
                color = greenLight
            )
            Text(
                text = stringResource(id = R.string.ask_expert_text_two),
                color = greenLight
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AskExpertScreenPreview() {
    AskExpertScreen(openAndPopUp = { _, _ -> }, navigateUp = { })
}