package com.example.agrican.ui.screens.home.profile.add_farm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.agrican.R
import com.example.agrican.common.enums.SizeUnit
import com.example.agrican.common.utils.DateUtils
import com.example.agrican.ui.components.DateDropDown
import com.example.agrican.ui.components.DropDown
import com.example.agrican.ui.components.LabelItem
import com.example.agrican.ui.components.LabelWithTextField
import com.example.agrican.ui.components.ProfileHeader
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.spacing

object AddFarmDestination: NavigationDestination {
    override val route: String = "add_farm"
    override val titleRes: Int = R.string.add_farm
}

@Composable
fun AddFarmScreen(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AddFarmViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ProfileHeader(navigateUp = navigateUp, headerText = AddFarmDestination.titleRes) {
        AddFarmScreenContent(
            uiState = uiState,
            updateFarmName = { viewModel.updateUiStates(farmName = it) },
            updateFarmSize = { viewModel.updateUiStates(farmSize = it) },
            updateSizeUnit = { viewModel.updateUiStates(sizeUnit = it) },
            updateCropsType = { viewModel.updateUiStates(cropsType = it) },
            updateDay = { viewModel.updateUiStates(day = it) },
            updateMonth = { viewModel.updateUiStates(month = it) },
            updateYear = { viewModel.updateUiStates(year = it) },
            addFarm = viewModel::addFarm,
            modifier = modifier
        )
    }
}

@Composable
fun AddFarmScreenContent(
    uiState: AddFarmUiState,
    updateFarmName: (String) -> Unit,
    updateFarmSize: (String) -> Unit,
    updateSizeUnit: (Int) -> Unit,
    updateCropsType: (String) -> Unit,
    updateDay: (Int) -> Unit,
    updateMonth: (Int) -> Unit,
    updateYear: (Int) -> Unit,
    addFarm: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(MaterialTheme.spacing.medium)
    ) {
        // Farm Name Text Field
        LabelWithTextField(
            value = uiState.farmName,
            onNewValue = updateFarmName,
            hint = R.string.farm_name,
            label = R.string.farm_name,
            focusManager = focusManager
        )

        // Size Text Field
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.height(IntrinsicSize.Max)
        ) {
            LabelWithTextField(
                value = uiState.farmSize,
                onNewValue = updateFarmSize,
                hint = R.string.size,
                label = R.string.size,
                focusManager = focusManager,
                keyboardType = KeyboardType.Number,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
            DropDown(options = listOf(
                SizeUnit.SQUARE_KILOMETER.title
            ),
                onSelect = { updateSizeUnit(it) },
                modifier = Modifier
                    .width(MaterialTheme.spacing.dp_130)
                    .fillMaxHeight()
            )
        }

        // Harvest Season Row
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
            modifier = Modifier.height(IntrinsicSize.Max)
        ) {
            LabelItem(text = R.string.harvest_season)

            DateDropDown(
                options = DateUtils().days,
                onSelect = { if (it != 0) updateDay(it) },
                selectedOption = uiState.day,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            )
            DateDropDown(
                options = DateUtils().months,
                onSelect = { if (it != 0) updateMonth(it) },
                selectedOption = uiState.month,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            )
            DateDropDown(
                options = DateUtils().years,
                onSelect = { if (it != 0) updateYear(it) },
                selectedOption = uiState.year,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            )
        }

        // Crops Type Text Field
        LabelWithTextField(
            value = uiState.cropsType,
            onNewValue = updateCropsType,
            hint = R.string.crops_type,
            label = R.string.crops_type,
            focusManager = focusManager,
            imeAction = ImeAction.Done
        )

        // ADd Crop Button
        Button(
            onClick = { addFarm() },
            colors = ButtonDefaults.buttonColors(containerColor = greenDark),
        ) {
            Text(
                text = stringResource(id = R.string.add_farm),
                fontSize = MaterialTheme.spacing.sp_15,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddFarmScreenPreview() {
    AddFarmScreenContent(uiState = AddFarmUiState(), { }, { }, { }, { }, { }, { }, { }, { })
}