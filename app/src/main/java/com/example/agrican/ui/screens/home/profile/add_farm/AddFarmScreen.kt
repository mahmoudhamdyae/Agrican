package com.example.agrican.ui.screens.home.profile.add_farm

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.agrican.R
import com.example.agrican.common.enums.SizeUnit
import com.example.agrican.common.utils.DateUtils
import com.example.agrican.ui.components.BackButtonTopBar
import com.example.agrican.ui.components.DateDropDown
import com.example.agrican.ui.components.DropDown
import com.example.agrican.ui.components.LabelItem
import com.example.agrican.ui.components.LabelWithTextField
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.textGray
import com.example.agrican.ui.theme.white

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
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            DropDown(
                options = listOf(
                    SizeUnit.SQUARE_KILOMETER.title
                ),
                onSelect = { updateSizeUnit(it) },
                textColor = textGray,
                modifier = Modifier
                    .width(130.dp)
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
                options = DateUtils().days,
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

            CalenderIcon()
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
                options = DateUtils().days,
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

            CalenderIcon()
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
                value = uiState.cropsType,
                onNewValue = updateCropsType,
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
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun CalenderIcon(
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = { /*TODO*/ },
        colors = IconButtonDefaults.iconButtonColors(containerColor = greenLight),
        modifier = modifier.clip(CircleShape).size(36.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.calender),
            contentDescription = null,
            tint = white,
            modifier = Modifier.size(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AddFarmScreenPreview() {
    AddFarmScreenContent(uiState = AddFarmUiState(), { }, { }, { }, { }, { }, { }, { }, { }, { })
}