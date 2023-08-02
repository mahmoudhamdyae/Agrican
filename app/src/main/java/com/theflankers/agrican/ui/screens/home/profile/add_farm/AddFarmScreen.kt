package com.theflankers.agrican.ui.screens.home.profile.add_farm

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.theflankers.agrican.R
import com.theflankers.agrican.common.enums.SizeUnit
import com.theflankers.agrican.common.utils.DateUtils
import com.theflankers.agrican.ui.components.BackButtonTopBar
import com.theflankers.agrican.ui.components.CalenderDropDown
import com.theflankers.agrican.ui.components.DateDropDown
import com.theflankers.agrican.ui.components.DropDown
import com.theflankers.agrican.ui.components.LabelItem
import com.theflankers.agrican.ui.components.LabelWithTextField
import com.theflankers.agrican.ui.navigation.NavigationDestination
import com.theflankers.agrican.ui.theme.greenDark
import com.theflankers.agrican.ui.theme.greenLight
import com.theflankers.agrican.ui.theme.textGray
import com.theflankers.agrican.ui.theme.white

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

    BackButtonTopBar(
        title = AddFarmDestination.titleRes,
        navigateUp = navigateUp,
        modifier = modifier
    ) {
        AddFarmScreenContent(
            uiState = uiState,
            updateFarmName = { viewModel.updateUiStates(farmName = it) },
            updateFarmSize = { viewModel.updateUiStates(farmSize = it) },
            updateSizeUnit = { viewModel.updateUiStates(sizeUnit = it) },
            updateCropsType = { viewModel.updateUiStates(cropsType = it) },
            updateFarmAddress = { viewModel.updateUiStates(farmAddress = it) },
            updateDay = { viewModel.updateUiStates(day = it) },
            updateMonth = { viewModel.updateUiStates(month = it) },
            updateYear = { viewModel.updateUiStates(year = it) },
            navigateUp = navigateUp,
            addFarm = viewModel::addFarm
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
    updateFarmAddress: (String) -> Unit,
    updateDay: (Int) -> Unit,
    updateMonth: (Int) -> Unit,
    updateYear: (Int) -> Unit,
    addFarm: () -> Unit,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 8.dp)
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
                modifier = Modifier.weight(2f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            DropDown(
                options = listOf(
                    SizeUnit.SQUARE_KILOMETER.title,
                    SizeUnit.SQUARE_METER.title,
                    SizeUnit.ACRE.title,
                    SizeUnit.HECTARE.title
                ),
                onSelect = { updateSizeUnit(it) },
                textColor = textGray,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            )
        }

        // Harvest Season Row
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.height(IntrinsicSize.Max)
        ) {
            LabelItem(text = R.string.harvest_season)

            // Days
            DateDropDown(
                options = DateUtils().days(uiState.month, uiState.year),
                onSelect = updateDay,
                selectedOption = uiState.day,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            )
            // Months
            DateDropDown(
                options = DateUtils().months,
                onSelect = updateMonth,
                selectedOption = uiState.month,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            )
            // Years
            DateDropDown(
                options = DateUtils().years,
                onSelect = updateYear,
                selectedOption = uiState.year,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            )

            CalenderDropDown(
                onDayClicked = {
                    updateDay(it.dayOfMonth)
                    updateMonth(it.monthValue)
                    updateYear(it.year)
                }
            )
        }

        // Farming Date Row
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.height(IntrinsicSize.Max)
        ) {
            LabelItem(text = R.string.farming_date)

            // Days
            DateDropDown(
                options = DateUtils().days(uiState.month, uiState.year),
                onSelect = { if (it != 0) updateDay(it) },
                selectedOption = uiState.day,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            )
            // Months
            DateDropDown(
                options = DateUtils().months,
                onSelect = { if (it != 0) updateMonth(it) },
                selectedOption = uiState.month,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            )
            // Years
            DateDropDown(
                options = DateUtils().years,
                onSelect = { if (it != 0) updateYear(it) },
                selectedOption = uiState.year,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            )

            CalenderDropDown(onDayClicked = { /*TODO*/ })
        }

        // Crops Type Text Field
        LabelWithTextField(
            value = uiState.cropsType,
            onNewValue = updateCropsType,
            hint = R.string.crops_type,
            label = R.string.crops_type,
            focusManager = focusManager
        )

        // Crops Type Text Field
        Box {
            LabelWithTextField(
                value = uiState.farmAddress,
                onNewValue = updateFarmAddress,
                hint = R.string.farm_address,
                label = R.string.farm_address,
                focusManager = focusManager,
                imeAction = ImeAction.Done
            )

            // Optional Label
            Text(
                text = "",
                color = greenLight,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(Alignment.BottomStart)
                    .background(white)
            )
        }

        // Add Crop Button
        Button(
            onClick = {
                addFarm()
                navigateUp()
                      },
            colors = ButtonDefaults.buttonColors(containerColor = greenDark),
        ) {
            Text(
                text = stringResource(id = R.string.add_farm),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddFarmScreenPreview() {
    AddFarmScreenContent(uiState = AddFarmUiState(), { }, { }, { }, { }, { }, { }, { }, { }, { }, { })
}