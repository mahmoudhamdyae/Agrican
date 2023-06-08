package com.example.agrican.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
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
        OutlinedTextField(
            readOnly = true,
            value = stringResource(id = selectedAvailabilityOption),
            onValueChange = { },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            shape = RoundedCornerShape(MaterialTheme.spacing.medium),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = gray,
                textColor = gray
            ),
            modifier = Modifier.menuAnchor()
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