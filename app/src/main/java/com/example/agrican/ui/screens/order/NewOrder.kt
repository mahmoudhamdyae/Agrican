package com.example.agrican.ui.screens.order

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.agrican.R
import com.example.agrican.ui.components.DropDown
import com.example.agrican.ui.components.NotesField
import com.example.agrican.ui.components.OrderField
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.spacing

@Composable
fun NewOrder(
    modifier: Modifier = Modifier
) {

    var productName by rememberSaveable { mutableStateOf("") }
    var quantity by rememberSaveable { mutableStateOf("") }
    var receivingAddress by rememberSaveable { mutableStateOf("") }
    var notes by rememberSaveable { mutableStateOf("") }

    val focusManager = LocalFocusManager.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        modifier = modifier.padding(MaterialTheme.spacing.small)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            LabelItem(
                text = R.string.product_kind,
                modifier = Modifier.height(60.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            DropDown(availabilityOptions = arrayOf(
                R.string.insecticide
            ), modifier = Modifier
                .height(60.dp)
                .width(130.dp)
                .height(60.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
        }

        FieldWithItem(
            value = productName,
            onNewValue = { productName = it },
            hint = R.string.insecticide_name,
            label = R.string.product_name,
            focusManager = focusManager,
        )

        Box {
            FieldWithItem(
                value = quantity,
                onNewValue = { quantity = it },
                hint = R.string.quantity,
                label = R.string.quantity,
                focusManager = focusManager,
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
            DropDown(availabilityOptions = arrayOf(
                R.string.kilogram
            ), modifier = Modifier
                .align(Alignment.CenterEnd)
                .width(130.dp))
        }

        FieldWithItem(
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
            ), modifier = Modifier
                .width(130.dp)
                .height(60.dp))
            DropDown(availabilityOptions = arrayOf(
                R.string.city
            ), modifier = Modifier
                .width(130.dp)
                .height(60.dp))
            DropDown(availabilityOptions = arrayOf(
                R.string.governorate
            ), modifier = Modifier
                .width(130.dp)
                .height(60.dp))
        }

        LabelItem(text = R.string.notes, modifier = Modifier.height(60.dp))
        NotesField(
            value = notes,
            onNewValue = { notes = it },
            focusManager = focusManager,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )

        Button(
            onClick = { /*TODO*/ },
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

@Composable
fun FieldWithItem(
    value: String,
    onNewValue: (String) -> Unit,
    hint: Int,
    label: Int,
    focusManager: FocusManager,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Next
) {
    Box(
        modifier = modifier.height(60.dp)
    ) {
        OrderField(
            value = value,
            onNewValue = onNewValue,
            hint = hint,
            focusManager = focusManager,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 100.dp),
            imeAction = imeAction
        )
        LabelItem(text = label)
    }
}

@Composable
fun LabelItem(
    text: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(MaterialTheme.spacing.medium),
        border = BorderStroke(1.dp, greenDark),
        modifier = modifier
            .width(130.dp)
            .fillMaxHeight()
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = stringResource(id = text),
                textAlign = TextAlign.Center,
                color = greenDark,
                modifier = Modifier
                    .padding(
                        horizontal = MaterialTheme.spacing.medium,
                        vertical = MaterialTheme.spacing.small
                    )
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NewOrderPreview() {
    NewOrder()
}