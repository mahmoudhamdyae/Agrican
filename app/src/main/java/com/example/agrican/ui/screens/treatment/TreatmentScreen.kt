package com.example.agrican.ui.screens.treatment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.agrican.R
import com.example.agrican.ui.components.CropsList
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.spacing

object TreatmentDestination: NavigationDestination {
    override val route: String = "treatment"
    override val titleRes: Int = R.string.treatment
}

@Composable
fun TreatmentScreen(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {

    var isCropSelected by rememberSaveable { mutableStateOf(false) }

    if (isCropSelected) {
        SelectedCropScreen(modifier = modifier)
    } else {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
            modifier = modifier
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.choose_crop),
                    color = greenLight,
                    modifier = Modifier.padding(start = MaterialTheme.spacing.medium)
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = {
                        navigateUp()
                    },
                    modifier = Modifier
                        .padding(MaterialTheme.spacing.medium)
                        .clip(CircleShape)
                        .background(greenDark)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }

            CropsList(modifier = Modifier.background(greenLight))

            Button(
                onClick = { isCropSelected = true },
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
    TreatmentScreen(navigateUp = { })
}