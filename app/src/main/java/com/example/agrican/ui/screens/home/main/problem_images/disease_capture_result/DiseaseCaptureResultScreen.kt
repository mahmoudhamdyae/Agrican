package com.example.agrican.ui.screens.home.main.problem_images.disease_capture_result

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.agrican.R
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.white

object DiseaseCaptureResultDestination: NavigationDestination {
    override val route: String = "disease_capture_result"
    override val titleRes: Int = R.string.problem_images
}

@Composable
fun DiseaseCaptureResultScreen(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(greenLight)
    ) {
        DiseaseCaptureResultScreenContent()

        IconButton(
            onClick = {
                navigateUp()
            },
            modifier = Modifier
                .padding(12.dp)
                .clip(CircleShape)
                .background(greenDark)
                .size(32.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
                contentDescription = null,
                tint = white,
                modifier = Modifier.padding(9.dp)
            )
        }
    }
}

@Composable
fun DiseaseCaptureResultScreenContent(
    modifier: Modifier = Modifier
) {
}

@Preview
@Composable
fun DiseaseCaptureResultScreenPreview() {
    DiseaseCaptureResultScreenContent()
}