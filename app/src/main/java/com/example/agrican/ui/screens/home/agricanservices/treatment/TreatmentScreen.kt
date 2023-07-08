package com.example.agrican.ui.screens.home.agricanservices.treatment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.agrican.R
import com.example.agrican.domain.model.Crop
import com.example.agrican.ui.components.BackButtonTopBar
import com.example.agrican.ui.components.CropsList
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.title

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

    BackButtonTopBar(
        title = TreatmentDestination.titleRes,
        navigateUp = navigateUp,
        modifier = modifier
    ) {
        TreatmentScreenContent(
            uiState = uiState,
            onSelectCrop = viewModel::onSelectCrop,
            openScreen = openScreen
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
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = 60.dp)
    ) {
        // Choose Crop
        Text(
            text = stringResource(id = R.string.choose_crop),
            color = greenLight,
            style = MaterialTheme.typography.title,
            fontSize = 16.sp,
            modifier = Modifier.padding(16.dp)
        )

        CropsList(
            isLoading = uiState.isLoading,
            crops = uiState.crops,
            setSelectedCrop = onSelectCrop,
            modifier = Modifier.fillMaxWidth()
        )

        // Continue Button
        Button(
            onClick = { openScreen(SelectedCropDestination.route) },
            colors = ButtonDefaults.buttonColors(containerColor = greenDark),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = stringResource(id = R.string.continue_button),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 64.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TreatmentScreenPreview() {
    TreatmentScreenContent(uiState = TreatmentUiState(), onSelectCrop = { }, openScreen = { })
}