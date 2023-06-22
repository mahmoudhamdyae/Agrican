package com.example.agrican.ui.screens.home.agricanservices.order.new_order

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.agrican.R
import com.example.agrican.common.enums.Quantity
import com.example.agrican.ui.components.DropDown
import com.example.agrican.ui.components.LabelItem
import com.example.agrican.ui.components.LabelWithTextField
import com.example.agrican.ui.components.NotesField
import com.example.agrican.ui.theme.greenDark

@Composable
fun NewOrderScreen(
    modifier: Modifier = Modifier,
    viewModel: NewOrderViewModel = hiltViewModel()
) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    var productName by rememberSaveable { mutableStateOf("") }
    var quantity by rememberSaveable { mutableStateOf("") }
    var receivingAddress by rememberSaveable { mutableStateOf("") }
    var notes by rememberSaveable { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .padding(bottom = 60.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Product Kind Row
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
        ) {
            LabelItem(text = R.string.product_kind)
            Spacer(modifier = Modifier.weight(1f))
            DropDown(options = listOf(
                R.string.insecticide
            ),
                onSelect = { viewModel.updateUiState(productType = it) },
                modifier = Modifier
                    .width(130.dp)
                    .fillMaxHeight()
            )
            Spacer(modifier = Modifier.weight(1f))
        }

        // Product Name Row
        LabelWithTextField(
            value = productName,
            onNewValue = { productName = it },
            hint = R.string.insecticide_name,
            label = R.string.product_name,
            focusManager = focusManager,
        )

        // Quantity Row
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.height(IntrinsicSize.Max)
        ) {
            LabelWithTextField(
                value = quantity,
                onNewValue = { quantity = it },
                hint = R.string.quantity,
                label = R.string.quantity,
                focusManager = focusManager,
                keyboardType = KeyboardType.Number,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            DropDown(options = listOf(
                Quantity.KILOGRAM.title
            ),
                onSelect = { viewModel.updateUiState(quantityUnit = it) },
                modifier = Modifier
                    .width(130.dp)
                    .fillMaxHeight())
        }

        // Receiving Address Row
        LabelWithTextField(
            value = receivingAddress,
            onNewValue = { receivingAddress = it },
            hint = R.string.receiving_address,
            focusManager = focusManager,
            label = R.string.receiving_address,
            imeAction = ImeAction.Done
        )

        // Place Row
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.height(32.dp)
        ) {
            // Place Drop Down
            DropDown(options = listOf(
                R.string.place
            ),
                onSelect = { viewModel.updateUiState(place = context.getString(it)) },
                modifier = Modifier.weight(1f)
            )
            // City Drop Down
            DropDown(options = listOf(
                R.string.city
            ),
                onSelect = { viewModel.updateUiState(city = context.getString(it)) },
                modifier = Modifier.weight(1f)
            )
            // Governorate Drop Down
            DropDown(options = listOf(
                R.string.governorate
            ),
                onSelect = { viewModel.updateUiState(governorate = context.getString(it)) },
                modifier = Modifier.weight(1f)
            )
        }

        // Notes Text Field
        LabelItem(text = R.string.notes)
        NotesField(
            value = notes,
            onNewValue = { notes = it },
            focusManager = focusManager,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )

        // Order Button
        Button(
            onClick = { viewModel.order(productName, quantity, receivingAddress, notes) },
            colors = ButtonDefaults.buttonColors(
                containerColor = greenDark
            )
        ) {
            Text(
                text = stringResource(id = R.string.order_button),
                fontSize = 15.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        // Spacer for bottom navigation bar
        Spacer(modifier = Modifier.height(30.dp))
    }
}