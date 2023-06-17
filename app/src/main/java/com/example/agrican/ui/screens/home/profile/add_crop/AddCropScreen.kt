package com.example.agrican.ui.screens.home.profile.add_crop

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.agrican.R
import com.example.agrican.common.utils.DateUtils
import com.example.agrican.domain.model.Crop
import com.example.agrican.ui.components.CropsList
import com.example.agrican.ui.components.DateDropDown
import com.example.agrican.ui.components.ProfileHeader
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.spacing
import com.example.agrican.ui.theme.title

object AddCropDestination: NavigationDestination {
    override val route: String = "add_crop"
    override val titleRes: Int = R.string.add_crop_title
}

@Composable
fun AddCropScreen(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AddCropViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ProfileHeader(navigateUp = navigateUp, headerText = AddCropDestination.titleRes) {
        AddCropScreenContent(
            uiState = uiState,
            updateSelectedCrop = { viewModel.updateUiStates(selectedCrop = it) },
            updateDay = { viewModel.updateUiStates(day = it) },
            updateMonth = { viewModel.updateUiStates(month = it) },
            updateYear = { viewModel.updateUiStates(year = it) },
            addCrop = viewModel::addCrop,
            modifier = modifier
        )
    }
}

@Composable
fun AddCropScreenContent(
    uiState: AddCropUiState,
    updateSelectedCrop: (Crop) -> Unit,
    updateDay: (Int) -> Unit,
    updateMonth: (Int) -> Unit,
    updateYear: (Int) -> Unit,
    addCrop: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
        modifier = modifier
    ) {
        // Choose Crop Text
        Text(
            text = stringResource(id = R.string.choose_crop_label),
            color = greenDark,
            style = MaterialTheme.typography.title,
            fontSize = MaterialTheme.spacing.sp_14,
            modifier = Modifier.padding(start = MaterialTheme.spacing.small)
        )

        CropsList(crops = uiState.crops, setSelectedCrop = updateSelectedCrop)

        // Agri History
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
            modifier = Modifier.padding(MaterialTheme.spacing.small)
        ) {
            Text(
                text = stringResource(id = R.string.agri_history),
                color = greenDark,
                fontSize = MaterialTheme.spacing.sp_14,
                style = MaterialTheme.typography.title
            )
            DateDropDown(
                options = DateUtils().days,
                onSelect = { if (it != 0) updateDay(it) },
                selectedOption = uiState.day,
                modifier = Modifier.weight(1f).height(MaterialTheme.spacing.large)
            )
            DateDropDown(
                options = DateUtils().months,
                onSelect = { if (it != 0) updateMonth(it) },
                selectedOption = uiState.month,
                modifier = Modifier.weight(1f).height(MaterialTheme.spacing.large)
            )
            DateDropDown(
                options = DateUtils().years,
                onSelect = { if (it != 0) updateYear(it) },
                selectedOption = uiState.year,
                modifier = Modifier.weight(1f).height(MaterialTheme.spacing.large)
            )
        }

        // Add Crop Button
        Button(
            onClick = addCrop,
            colors = ButtonDefaults.buttonColors(containerColor = greenDark),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = stringResource(id = R.string.add_crop_button),
                fontSize = MaterialTheme.spacing.sp_15,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddCropScreenPreview() {
    AddCropScreenContent(uiState = AddCropUiState(), { }, { }, { }, { }, { })
}