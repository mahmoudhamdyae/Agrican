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
import androidx.compose.material3.MaterialTheme
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.agrican.R
import com.example.agrican.ui.components.DropDown
import com.example.agrican.ui.components.LabelItem
import com.example.agrican.ui.components.LabelWithTextField
import com.example.agrican.ui.components.NotesField
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.spacing

@Composable
fun NewOrderScreen(
    modifier: Modifier = Modifier,
    viewModel: NewOrderViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    var productName by rememberSaveable { mutableStateOf("") }
    var quantity by rememberSaveable { mutableStateOf("") }
    var receivingAddress by rememberSaveable { mutableStateOf("") }
    var notes by rememberSaveable { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        modifier = modifier
            .padding(MaterialTheme.spacing.small)
            .verticalScroll(rememberScrollState())
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)) {
            LabelItem(text = R.string.product_kind)
            Spacer(modifier = Modifier.weight(1f))
            DropDown(availabilityOptions = arrayOf(
                R.string.insecticide
            ),
                onSelect = { uiState.productType = context.getString(it) },
                modifier = Modifier.width(130.dp).fillMaxHeight()
            )
            Spacer(modifier = Modifier.weight(1f))
        }

        LabelWithTextField(
            value = productName,
            onNewValue = { productName = it },
            hint = R.string.insecticide_name,
            label = R.string.product_name,
            focusManager = focusManager,
        )

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
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
            DropDown(availabilityOptions = arrayOf(
                R.string.kilogram
            ),
                onSelect = { uiState.quantityUnit = context.getString(it) },
                modifier = Modifier.width(130.dp).fillMaxHeight())
        }

        LabelWithTextField(
            value = receivingAddress,
            onNewValue = { receivingAddress = it },
            hint = R.string.receiving_address,
            focusManager = focusManager,
            label = R.string.receiving_address,
            imeAction = ImeAction.Done
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
        ) {
            DropDown(availabilityOptions = arrayOf(
                R.string.place
            ),
                onSelect = { uiState.place = context.getString(it) },
                modifier = Modifier.weight(1f)
            )
            DropDown(availabilityOptions = arrayOf(
                R.string.city
            ),
                onSelect = { uiState.city = context.getString(it) },
                modifier = Modifier.weight(1f)
            )
            DropDown(availabilityOptions = arrayOf(
                R.string.governorate
            ),
                onSelect = { uiState.governorate = context.getString(it) },
                modifier = Modifier.weight(1f)
            )
        }

        LabelItem(text = R.string.notes)
        NotesField(
            value = notes,
            onNewValue = { notes = it },
            focusManager = focusManager,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )

        Button(
            onClick = { viewModel.order(productName, quantity, receivingAddress, notes) },
            colors = ButtonDefaults.buttonColors(
                containerColor = greenDark
            )
        ) {
            Text(
                text = stringResource(id = R.string.order_button),
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
            )
        }
    }
}