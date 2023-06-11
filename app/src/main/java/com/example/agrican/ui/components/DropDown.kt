package com.example.agrican.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.agrican.R
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDown(
    availabilityOptions: Array<Int>,
    modifier: Modifier = Modifier,
    isGray: Boolean = false,
) {

    var expanded by remember { mutableStateOf(false) }
    var selectedAvailabilityOption by remember { mutableStateOf(availabilityOptions[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
        modifier = modifier
    ) {
        BasicTextField(
            readOnly = true,
            singleLine = true,
            decorationBox = {
                Row(verticalAlignment = Alignment.CenterVertically,) {
                    Text(
                        text = stringResource(id = selectedAvailabilityOption),
                        color = if (isGray) gray else Color.Black,
                        modifier = Modifier.padding(start = MaterialTheme.spacing.small)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                }
            },
            value = stringResource(id = selectedAvailabilityOption),
            onValueChange = { },
            modifier = Modifier
                .menuAnchor()
                .border(
                    border = BorderStroke(1.dp, gray),
                    shape = RoundedCornerShape(MaterialTheme.spacing.medium)
                )
                .height(30.dp)
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            availabilityOptions.forEach { selectionOptions ->
                DropdownMenuItem(
                    text = { Text(
                        text = stringResource(id = selectionOptions),
                        color = if (isGray) gray else Color.Black
                        ) },
                    onClick = {
                        selectedAvailabilityOption = selectionOptions
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DropDownPreview() {
    DropDown(availabilityOptions = arrayOf(R.string.place), modifier = Modifier.fillMaxWidth())
}