package com.example.agrican.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
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
import com.example.agrican.ui.theme.title
import com.example.agrican.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiseaseHeader(
    image: Int?,
    diseaseName: String,
    buttonText: Int,
    onSelect: (Int)-> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = gray,
        shape = RoundedCornerShape(
            bottomStart = 32.dp,
            bottomEnd = 32.dp,
        ),
        modifier = modifier
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(225.dp)) {
            if (image == null) {
                EmptyImage(modifier = Modifier.fillMaxSize())
            } else {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Row(
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            ) {
                // Disease Name
                Text(
                    text = diseaseName,
                    color = white,
                    style = MaterialTheme.typography.body,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                )

                // Search for more Button
                var expanded by remember { mutableStateOf(false) }
                val options = listOf(buttonText)
                var selectedOption by remember { mutableStateOf(options[0]) }

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    modifier = modifier
                        .align(Alignment.Bottom)
                ) {
                Surface(
                    shadowElevation = 8.dp,
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier.clickable { expanded != expanded }
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = stringResource(id = buttonText),
                            color = black,
                            style = MaterialTheme.typography.body,
                            fontSize = 14.sp,
                            modifier = Modifier
                                .padding(start = 12.dp, top = 6.dp, bottom = 6.dp)
                        )

                        IconButton(
                            onClick = {/* expanded = !expanded*/ },
                            modifier = Modifier.height(32.dp)
                        ) {
                            Icon(
                                imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                                contentDescription = null,
                                tint = greenDark
                            )
                        }
                    }
                }

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
                                    color = greenDark,
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
        }
    }
}

@Composable
fun MainLabel(
    text: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = text),
        color = greenDark,
        style = MaterialTheme.typography.title,
        fontSize = 14.sp,
        modifier = modifier
    )
}

@Composable
fun DescriptionLabel(
    texts: List<String>,
    modifier: Modifier = Modifier
) {
    texts.forEach {
        Row(modifier = modifier) {
            if (texts.size > 1) {
                Box(
                    modifier = Modifier
                        .padding(end = 8.dp, top = 12.dp)
                        .clip(CircleShape)
                        .background(black)
                        .size(4.dp)
                )
            }
            Text(
                text = it,
                color = black,
                style = MaterialTheme.typography.body,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview
@Composable
fun DiseaseHeaderPreview() {
    DiseaseHeader(
        image = null,
        diseaseName = "تبقع الأوراق السيركسبورى أو (التيكا) فى الفول السودانى",
        buttonText = R.string.search_for_another_disease,
        onSelect = { }
    )
}