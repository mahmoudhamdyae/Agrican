package com.example.agrican.ui.screens.home.profile.cost

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.agrican.R
import com.example.agrican.ui.components.BackButtonTopBar
import com.example.agrican.ui.components.SimpleTextField
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.body
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.greenLight

object CostDestination: NavigationDestination {
    override val route: String = "cost"
    override val titleRes: Int = R.string.cost
}

@Composable
fun CostScreen(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    var insuranceCode by rememberSaveable { mutableStateOf("") }
    var engineerMapCode by rememberSaveable { mutableStateOf("") }
    var fertilizersCalculatorCode by rememberSaveable { mutableStateOf("") }
    var askAnExpertCode by rememberSaveable { mutableStateOf("") }
    var cropManagerCode by rememberSaveable { mutableStateOf("") }

    BackButtonTopBar(
        title = CostDestination.titleRes,
        navigateUp = navigateUp,
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
                .padding(bottom = 60.dp)
        ) {
            // Main Table Row
            Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    text = stringResource(id = R.string.services),
                    style = MaterialTheme.typography.body,
                    fontSize = 15.sp,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = stringResource(id = R.string.cost_in_month),
                    style = MaterialTheme.typography.body,
                    fontSize = 15.sp,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = stringResource(id = R.string.discount_code),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.body,
                    fontSize = 15.sp,
                    modifier = Modifier.weight(1f)
                )
            }

            Divider(color = gray)

            // Insurance Row
            CostRow(
                mainLabel = R.string.insurance,
                costMonthly = 00.00,
                code = insuranceCode,
                onCodeChanged = { insuranceCode = it }
            )

            // Engineer Map Row
            CostRow(
                mainLabel = R.string.engineer_map,
                costMonthly = 00.00,
                code = engineerMapCode,
                onCodeChanged = { engineerMapCode = it }
            )

            // Fertilizers Calculator Row
            CostRow(
                mainLabel = R.string.fertilizers_calculator,
                costMonthly = 00.00,
                code = fertilizersCalculatorCode,
                onCodeChanged = { fertilizersCalculatorCode = it }
            )

            // Ask An Expert Row
            CostRow(
                mainLabel = R.string.ask_expert,
                costMonthly = 00.00,
                code = askAnExpertCode,
                onCodeChanged = { askAnExpertCode = it }
            )

            // Crop Manager Row
            CostRow(
                mainLabel = R.string.crop_manager,
                costMonthly = 00.00,
                code = cropManagerCode,
                onCodeChanged = { cropManagerCode = it }
            )

            Divider(modifier = Modifier.height(2.dp), color = gray)

            // Total Sum Row
            Row(modifier = modifier.padding(horizontal = 16.dp)) {
                Text(
                    text = stringResource(id = R.string.sum),
                    style = MaterialTheme.typography.body,
                    fontSize = 17.sp
                )

                Spacer(modifier = Modifier.weight(1f))

                // Price
                Text(
                    text = "00.00",
                    style = MaterialTheme.typography.body,
                    fontSize = 17.sp
                )
                Spacer(modifier = Modifier.padding(end = 8.dp))
                // Pound Label
                Text(
                    text = stringResource(id = R.string.pound),
                    style = MaterialTheme.typography.body,
                    fontSize = 17.sp,
                    modifier = Modifier.padding(end = 16.dp)
                )
            }
        }
    }
}

@Composable
fun CostRow(
    mainLabel: Int,
    costMonthly: Double,
    code: String,
    onCodeChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(vertical = 8.dp)
    ) {
        // Services Text
        Text(
            text = stringResource(id = mainLabel),
            style = MaterialTheme.typography.body,
            fontSize = 12.sp,
            modifier = Modifier.weight(1f)
        )

        // Cost in Month
        Text(
            text = costMonthly.toString(),
            style = MaterialTheme.typography.body,
            textAlign = TextAlign.Center,
            fontSize = 15.sp,
            modifier = Modifier.weight(1f)
        )

        // Discount Code Text Field
        SimpleTextField(
            value = code,
            onNewValue = onCodeChanged,
            placeHolder = { Text(
                text = stringResource(R.string.add_code),
                color = gray,
                textAlign = TextAlign.Center
            ) },
            imeAction = ImeAction.Done,
            borderColor = greenLight,
            modifier = Modifier
                .weight(1f)
                .height(32.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CostScreenPreview() {
    CostScreen(navigateUp = { })
}