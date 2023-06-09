package com.example.agrican.ui.screens.treatment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.agrican.R
import com.example.agrican.ui.components.BackButton
import com.example.agrican.ui.components.CropsList
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.spacing

object TreatmentDestination : NavigationDestination {
    override val route: String = "treatment"
    override val titleRes: Int = R.string.treatment
}

@Composable
fun TreatmentScreen(
    navigateUp: () -> Unit,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    BackButton(navigateUp = navigateUp) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
            modifier = modifier
        ) {
            Text(
                text = stringResource(id = R.string.choose_crop),
                color = greenLight,
                modifier = Modifier.padding(start = MaterialTheme.spacing.extraLarge)
            )

            CropsList(modifier = Modifier.background(greenLight))

            Button(
                onClick = { openScreen(SelectedCropDestination.route) },
                colors = ButtonDefaults.buttonColors(containerColor = greenDark),
            ) {
                Text(
                    text = stringResource(id = R.string.continue_button),
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TreatmentScreenPreview() {
    TreatmentScreen(navigateUp = { }, openScreen = { })
}