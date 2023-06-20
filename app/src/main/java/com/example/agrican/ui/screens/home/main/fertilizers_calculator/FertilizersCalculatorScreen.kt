package com.example.agrican.ui.screens.home.main.fertilizers_calculator

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.agrican.R
import com.example.agrican.common.enums.MeasureUnit
import com.example.agrican.domain.model.Crop
import com.example.agrican.ui.components.Chip
import com.example.agrican.ui.components.CropsList
import com.example.agrican.ui.components.EmptyImage
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.body
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.title
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object FertilizersCalculatorDestination: NavigationDestination {
    override val route: String = "fertilizers_calculator"
    override val titleRes: Int = R.string.fertilizers_calculator
}

@Composable
fun FertilizersCalculatorScreen(
    modifier: Modifier = Modifier,
    viewModel: FertilizersCalculatorViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    FertilizersCalculatorScreenContent(
        uiState = uiState,
        onSelectCrop = viewModel::onSelectCrop,
        increaseSize = viewModel::increaseSize,
        decreaseSize = viewModel::decreaseSize,
        calculateFertilizers = viewModel::calculateFertilizers,
        modifier = modifier
    )
}

@Composable
fun FertilizersCalculatorScreenContent(
    uiState: FertilizersCalculatorUiState,
    onSelectCrop: (Crop) -> Unit,
    increaseSize: () -> Unit,
    decreaseSize: () -> Unit,
    calculateFertilizers: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selected by rememberSaveable { mutableStateOf(1) }
    val units = listOf(
        MeasureUnit.ACRE,
        MeasureUnit.HECTARE,
    )

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        // Choose Crop Label
        item {
            Text(
                text = stringResource(id = R.string.choose_crop_label),
                style = MaterialTheme.typography.title,
                fontSize = 16.sp,
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 16.dp
                )
            )
        }

        // Crops List
        item {
            CropsList(crops = uiState.crops, setSelectedCrop = onSelectCrop)
        }

        // Measuring Unit Label
        item {
            Text(
                text = stringResource(id = R.string.measuring_unit),
                style = MaterialTheme.typography.title,
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                repeat(units.size) {
                    Chip(
                        text = units[it].title,
                        selected = it == selected,
                        onSelect = { selected = it },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        // Land Size Label
        item {
            Text(
                text = stringResource(id = R.string.land_size),
                style = MaterialTheme.typography.title,
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
        item {
            LandSize(
                size = uiState.landSize,
                increaseSize = increaseSize,
                decreaseSize = decreaseSize,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        // Calculate Button
        item {
            Box(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = calculateFertilizers,
                    colors = ButtonDefaults.buttonColors(containerColor = greenDark),
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text(
                        text = stringResource(id = R.string.calculate_fertilizer),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }

        items(3) {
            FertilizerListItem(modifier = Modifier.padding(8.dp))
        }

        // Space fo Bottom Navigation Bar
        item {
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

@Composable
fun FertilizerListItem(
    modifier: Modifier = Modifier
) {
    Surface(
        shadowElevation = 16.dp,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
        ) {
            EmptyImage(modifier = Modifier
                .weight(1f)
                .fillMaxHeight())
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .weight(3f)
                    .padding(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Text(
                            text = "سماد رقم 1",
                            style = MaterialTheme.typography.title,
                            fontSize = 16.sp
                        )
                        Text(
                            text = "لمدة سنة",
                            color = greenDark,
                            style = MaterialTheme.typography.body,
                            fontSize = 12.sp
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    // Know More Button
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(containerColor = greenDark),
                    ) {
                        Text(
                            text = stringResource(id = R.string.know_more),
                            fontSize = 12.sp
                        )
                    }
                }

                // Fertilizer Description
                Text(
                    text = "هذه هى الكمية المطلوب بنائها و يتم تسميد الأرض باستخدام المنتجات المخصصة لذلك هذه هى الكمية المطلوب بنائها و يتم تسميد الأرض باستخدام المنتجات المخصصة لذلك",
                    style = MaterialTheme.typography.body,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }
    }
}

@Composable
fun LandSize(
    size: Int,
    increaseSize: () -> Unit,
    decreaseSize: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        border = BorderStroke(1.dp, gray),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
    ) {
        Row {
            // Decrease Button
            Button(
                onClick = decreaseSize,
                colors = ButtonDefaults.buttonColors(containerColor = greenLight),
            ) {
                Text(text = "-")
            }

            // Unit Label
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(id = R.string.hectare),
                    color = greenLight,
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )

                Surface(
                    border = BorderStroke(1.dp, gray),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = size.toString(),
                            color = greenLight,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                }
            }

            // Increase Button
            Button(
                onClick = increaseSize,
                colors = ButtonDefaults.buttonColors(containerColor = greenLight),
            ) {
                Text(text = "+")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FertilizersCalculatorScreenPreview() {
    FertilizersCalculatorScreenContent(uiState = FertilizersCalculatorUiState(), { }, { }, { }, { })
}