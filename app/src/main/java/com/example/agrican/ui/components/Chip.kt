package com.example.agrican.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.agrican.ui.theme.body
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.white
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Chip(
    text: Int,
    selected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier
) {
    FilterChip(
        selected = selected,
        onClick = onSelect,
        colors = FilterChipDefaults.elevatedFilterChipColors(
            selectedContainerColor = greenLight,
            selectedLabelColor = white
        ),
        label = {
            Text(
                text = stringResource(id = text),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body,
                fontSize = 14.sp,
                modifier = Modifier.fillMaxWidth()
            )
        },
        shape = RoundedCornerShape(32.dp),
        modifier = modifier.padding(8.dp)
    )
}