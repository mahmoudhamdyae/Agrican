package com.example.agrican.ui.screens.home.profile.add_crop

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.agrican.R
import com.example.agrican.common.utils.DateUtils
import com.example.agrican.domain.model.Crop
import com.example.agrican.domain.model.Farm
import com.example.agrican.ui.components.BackButtonTopBar
import com.example.agrican.ui.components.CropsList
import com.example.agrican.ui.components.DateDropDown
import com.example.agrican.ui.components.FarmsList
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.greenDark
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

    BackButtonTopBar(
        title = AddCropDestination.titleRes,
        navigateUp = navigateUp,
        modifier = modifier
    ) {
        AddCropScreenContent(
            uiState = uiState,
            updateSelectedCrop = { viewModel.updateUiStates(selectedCrop = it) },
            updateDay = { viewModel.updateUiStates(day = it) },
            updateMonth = { viewModel.updateUiStates(month = it) },
            updateYear = { viewModel.updateUiStates(year = it) },
            addCrop = viewModel::addCrop,
            navigateUp = navigateUp
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
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        // Choose Farm Label
        Text(
            text = stringResource(id = R.string.choose_farm),
            color = greenDark,
            style = MaterialTheme.typography.title,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 16.dp)
        )

        // Farms List
        FarmsList(
            farms = listOf(
                Farm(name = "المزرعة الأولى"),
                Farm(name = "المزرعة الثانية"),
                Farm(name = "المزرعة الثالثة"),
                Farm(name = "المزرعة الرابعة"),
                Farm(name = "المزرعة الخامسة"),
                Farm(name = "المزرعة السادسة"),
                Farm(name = "المزرعة السابعة"),
            ),
            delAction = false,
            onDelFarmClick = { },
            onFarmClick = { }
        )

        // Choose Crop Text
        Text(
            text = stringResource(id = R.string.choose_crop_label),
            color = greenDark,
            style = MaterialTheme.typography.title,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 16.dp)
        )

        CropsList(
            isLoading = uiState.isLoading,
            crops = uiState.crops,
            setSelectedCrop = updateSelectedCrop
        )

        // Agri History
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.agri_history),
                color = greenDark,
                fontSize = 14.sp,
                style = MaterialTheme.typography.title
            )
            DateDropDown(
                options = DateUtils().days,
                onSelect = { if (it != 0) updateDay(it) },
                selectedOption = uiState.day,
                modifier = Modifier
                    .weight(1f)
                    .height(32.dp)
            )
            DateDropDown(
                options = DateUtils().months,
                onSelect = { if (it != 0) updateMonth(it) },
                selectedOption = uiState.month,
                modifier = Modifier
                    .weight(1f)
                    .height(32.dp)
            )
            DateDropDown(
                options = DateUtils().years,
                onSelect = { if (it != 0) updateYear(it) },
                selectedOption = uiState.year,
                modifier = Modifier
                    .weight(1f)
                    .height(32.dp)
            )
        }

        // Add Crop Button
        Button(
            onClick = {
                addCrop()
                navigateUp()
            },
            colors = ButtonDefaults.buttonColors(containerColor = greenDark),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = stringResource(id = R.string.add_crop_button),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddCropScreenPreview() {
    AddCropScreenContent(uiState = AddCropUiState(), { }, { }, { }, { }, { }, { })
}