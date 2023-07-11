package com.example.agrican.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.agrican.R
import com.example.agrican.ui.theme.black
import com.example.agrican.ui.theme.body
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.iconGray
import com.example.agrican.ui.theme.textGray
import com.example.agrican.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDown(
    options: List<Int>,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
    textColor: Color = black,
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
                        color = textColor,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.body,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(
                        onClick = { expanded = !expanded },
                        modifier = Modifier.height(32.dp)
                    ) {
                        Icon(
                            imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                            contentDescription = null,
                            tint = textColor
                        )
                    }
                }
            },
            value = stringResource(id = selectedOption),
            onValueChange = { },
            modifier = Modifier
                .menuAnchor()
                .border(
                    border = BorderStroke(1.dp, gray),
                    shape = RoundedCornerShape(16.dp)
                )
                .fillMaxHeight()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(white).padding(top = 2.dp)
        ) {
            options.forEach { selectionOption ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onSelect(selectionOption)
                            selectedOption = selectionOption
                            expanded = false
                        }
                ) {
                    Text(
                        text = stringResource(id = selectionOption),
                        color = textColor,
                        modifier = Modifier.padding(start = 4.dp)
                    )

                    if (options.size - 1 != options.indexOf(selectionOption)) {
                        Divider(modifier = Modifier.padding(vertical = 4.dp, horizontal = 2.dp))
                    }
                }
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
                        color = textGray,
                        fontSize = 10.sp,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(
                        onClick = { expanded = !expanded },
                        modifier = Modifier.height(32.dp)
                    ) {
                        Icon(
                            imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                            contentDescription = null,
                            tint = greenDark
                        )
                    }
                }
            },
            value = if (selectedOption2 == options[0]) stringResource(id = selectedOption2)
            else selectedOption2.toString(),
            onValueChange = { },
            modifier = Modifier
                .menuAnchor()
                .border(
                    border = BorderStroke(1.dp, gray),
                    shape = RoundedCornerShape(16.dp)
                )
                .fillMaxHeight()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(white)
        ) {
            options.slice(1 until options.size).forEach { selectionOption ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onSelect(selectionOption)
                            selectedOption2 = selectionOption
                            expanded = false
                        }
                ) {
                    Text(
                        text = if (selectionOption == options[0]) stringResource(id = selectionOption)
                        else selectionOption.toString(),
                        color = textGray,
                        modifier = Modifier.padding(start = 4.dp)
                    )

                    if (options.size - 1 != options.indexOf(selectionOption)) {
                        Divider(modifier = Modifier.padding(vertical = 4.dp, horizontal = 2.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun DropDownMenuContent(
    hideMenu: () -> Unit,
    signOutAction: () -> Unit,
    openNotificationsScreen: () -> Unit,
    toggleLanguage: () -> Unit,
    modifier: Modifier= Modifier
) {
    Column(modifier = modifier.width(220.dp)) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 20.dp).padding(top = 8.dp)
        ) {
            // Settings Label
            Text(
                text = stringResource(id = R.string.settings),
                color = greenDark,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            // Notification Button
            IconButton(onClick = openNotificationsScreen) {
                Icon(
                    painter = painterResource(id = R.drawable.notifications),
                    contentDescription = stringResource(id = R.string.notifications),
                    tint = iconGray
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Menu Item
            Icon(
                painter = painterResource(id = R.drawable.menu),
                contentDescription = null,
                tint = greenDark
            )
        }

        Surface(
            shape = RoundedCornerShape(8.dp),
            shadowElevation = 8.dp,
            border = BorderStroke(1.dp, Color(0xFFDADADA)),
            modifier = Modifier.padding(12.dp)
        ) {
            Column(modifier = Modifier.background(white)) {
                // Language Item
                DropDownItem(
                    text = R.string.language,
                    onItemClick = {
                        toggleLanguage()
                        hideMenu()
                    }
                )

                Divider(modifier = Modifier
                    .height(2.dp)
                    .background(Color(0xFFDADADA)))

                // About Us Item
                DropDownItem(
                    text = R.string.about_us,
                    onItemClick = hideMenu
                )

                Divider(modifier = Modifier
                    .height(2.dp)
                    .background(Color(0xFFDADADA)))

                // Sign Out Item
                DropDownItem(
                    text = R.string.sign_out,
                    onItemClick = {
                        hideMenu()
                        signOutAction()
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BasicDropDownPreview() {
    DropDown(
        options = listOf(R.string.place, R.string.place),
        onSelect = { },
        textColor = greenDark,
        modifier = Modifier.fillMaxSize()
    )
}