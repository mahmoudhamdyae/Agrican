package com.theflankers.agrican.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.theflankers.agrican.ui.theme.body
import com.theflankers.agrican.ui.theme.gray
import com.theflankers.agrican.ui.theme.greenLight
import com.theflankers.agrican.ui.theme.textGray
import com.theflankers.agrican.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Chip(
    text: Int,
    selected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier,
    textColor: Color = textGray,
    borderColor: Color = gray
) {
    FilterChip(
        selected = selected,
        onClick = onSelect,
        colors = FilterChipDefaults.elevatedFilterChipColors(
            selectedContainerColor = greenLight,
            selectedLabelColor = white
        ),
        border = FilterChipDefaults.filterChipBorder(borderColor = borderColor),
        label = {
            Text(
                text = stringResource(id = text),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body,
                fontSize = 14.sp,
                color = textColor,
                modifier = Modifier.fillMaxWidth()
            )
        },
        shape = RoundedCornerShape(32.dp),
        modifier = modifier
    )
}