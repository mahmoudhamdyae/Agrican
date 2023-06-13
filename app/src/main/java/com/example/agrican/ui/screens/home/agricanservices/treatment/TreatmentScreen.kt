package com.example.agrican.ui.screens.home.agricanservices.treatment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.agrican.R
import com.example.agrican.domain.model.Crop
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
    modifier: Modifier = Modifier,
    viewModel: TreatmentViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = null) {
        viewModel.getCrops()
    }

    BackButton(navigateUp = navigateUp) {
        TreatmentScreenContent(
            uiState = uiState,
            onSelectCrop = viewModel::onSelectCrop,
            openScreen = openScreen,
            modifier = modifier
        )
    }
}

@Composable
fun TreatmentScreenContent(
    uiState: TreatmentUiState,
    onSelectCrop: (Crop) -> Unit,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.choose_crop),
            color = greenLight,
            modifier = Modifier.padding(start = MaterialTheme.spacing.medium)
        )

        CropsList(
            crops = uiState.crops,
            setSelectedCrop = onSelectCrop,
            modifier = Modifier.background(greenLight)
        )

        Button(
            onClick = { openScreen(SelectedCropDestination.route) },
            colors = ButtonDefaults.buttonColors(containerColor = greenDark),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = stringResource(id = R.string.continue_button),
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TreatmentScreenPreview() {
    TreatmentScreenContent(uiState = TreatmentUiState(), onSelectCrop = { }, openScreen = { })
}