package com.example.agrican.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
    options: List<Int>,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
    isGray: Boolean = false,
) {

    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(options[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
        modifier = modifier.fillMaxHeight()
    ) {
        BasicTextField(
            readOnly = true,
            singleLine = true,
            decorationBox = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(id = selectedOption),
                        color = if (isGray) gray else Color.Black,
                        modifier = Modifier.padding(start = MaterialTheme.spacing.small)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                }
            },
            value = stringResource(id = selectedOption),
            onValueChange = { },
            modifier = Modifier
                .menuAnchor()
                .border(
                    border = BorderStroke(1.dp, gray),
                    shape = RoundedCornerShape(MaterialTheme.spacing.medium)
                )
                .fillMaxHeight()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(
                        text = stringResource(id = selectionOption),
                        color = if (isGray) gray else Color.Black
                        ) },
                    onClick = {
                        onSelect(selectionOption)
                        selectedOption = selectionOption
                        expanded = false
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateDropDown(
    options: List<Int>,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
    selectedOption: Int = options[0]
) {

    var expanded by remember { mutableStateOf(false) }
    var selectedOption2 by remember { mutableStateOf(selectedOption) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
        modifier = modifier.fillMaxHeight()
    ) {
        BasicTextField(
            readOnly = true,
            singleLine = true,
            decorationBox = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = if (selectedOption2 == options[0]) stringResource(id = selectedOption2)
                            else selectedOption2.toString(),
                        color = Color.Black,
                        modifier = Modifier.padding(start = MaterialTheme.spacing.small)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                }
            },
            value = if (selectedOption2 == options[0]) stringResource(id = selectedOption2)
            else selectedOption2.toString(),
            onValueChange = { },
            modifier = Modifier
                .menuAnchor()
                .border(
                    border = BorderStroke(1.dp, gray),
                    shape = RoundedCornerShape(MaterialTheme.spacing.medium)
                )
                .fillMaxHeight()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(
                        text = if (selectionOption == options[0]) stringResource(id = selectionOption)
                        else selectionOption.toString(),
                        color = Color.Black
                    ) },
                    onClick = {
                        onSelect(selectionOption)
                        selectedOption2 = selectionOption
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BasicDropDownPreview() {
    DropDown(options = listOf(R.string.place), onSelect = { }, modifier = Modifier.fillMaxSize())
}