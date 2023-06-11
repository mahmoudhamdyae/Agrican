package com.example.agrican.ui.screens.home.profile.cost

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.agrican.R
import com.example.agrican.ui.components.SimpleTextField
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.spacing

object CostDestination: NavigationDestination {
    override val route: String = "cost"
    override val titleRes: Int = R.string.cost
}

@Composable
fun CostScreen(
    modifier: Modifier = Modifier
) {
    var insuranceCode by rememberSaveable { mutableStateOf("") }
    var engineerMapCode by rememberSaveable { mutableStateOf("") }
    var fertilizersCalculatorCode by rememberSaveable { mutableStateOf("") }
    var askAnExpertCode by rememberSaveable { mutableStateOf("") }
    var cropManagerCode by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier.padding(MaterialTheme.spacing.small)
    ) {
        Row(modifier = Modifier.padding(MaterialTheme.spacing.medium)) {
            Text(text = stringResource(id = R.string.services), modifier = Modifier.weight(1f))
            Text(text = stringResource(id = R.string.cost_in_month), modifier = Modifier.weight(1f))
            Text(text = stringResource(id = R.string.discount_code), modifier = Modifier.weight(1f))
        }

        Divider()

        CostRow(
            mainLabel = R.string.insurance,
            costMonthly = 00.00,
            code = insuranceCode,
            onCodeChanged = { insuranceCode = it }
        )
        CostRow(
            mainLabel = R.string.engineer_map,
            costMonthly = 00.00,
            code = engineerMapCode,
            onCodeChanged = { engineerMapCode = it }
        )
        CostRow(
            mainLabel = R.string.fertilizers_calculator,
            costMonthly = 00.00,
            code = fertilizersCalculatorCode,
            onCodeChanged = { fertilizersCalculatorCode = it }
        )
        CostRow(
            mainLabel = R.string.ask_expert,
            costMonthly = 00.00,
            code = askAnExpertCode,
            onCodeChanged = { askAnExpertCode = it }
        )
        CostRow(
            mainLabel = R.string.crop_manager,
            costMonthly = 00.00,
            code = cropManagerCode,
            onCodeChanged = { cropManagerCode = it }
        )

        Divider(modifier = Modifier.padding(MaterialTheme.spacing.medium), color = Color.Black)

        Row(modifier = modifier.padding(MaterialTheme.spacing.medium)) {
            Text(text = stringResource(id = R.string.sum), modifier = Modifier.weight(1f))

            Row(modifier = Modifier.weight(1f)) {
                Text(text = "00.00")
                Spacer(modifier = Modifier.padding(end = MaterialTheme.spacing.small))
                Text(text = stringResource(id = R.string.pound))
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
        modifier = modifier
    ) {
        Text(text = stringResource(id = mainLabel), modifier = Modifier.weight(1f))
        
        Text(text = costMonthly.toString(), modifier = Modifier.weight(1f))

        SimpleTextField(
            value = code,
            onNewValue = onCodeChanged,
            placeHolder = { Text(
                text = stringResource(R.string.add_code),
                color = gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(MaterialTheme.spacing.small)
            ) },
            imeAction = ImeAction.Done,
            borderColor = greenLight,
            modifier = Modifier
                .weight(1f)
                .padding(vertical = MaterialTheme.spacing.medium)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CostScreenPreview() {
    CostScreen()
}