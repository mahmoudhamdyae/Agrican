package com.example.agrican.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.agrican.domain.model.Farm
import com.example.agrican.ui.theme.body
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.white

@Composable
fun FarmsList(
    farms: List<Farm>,
    delAction: Boolean,
    onDelFarmClick: (String) -> Unit,
    onFarmClick: (Farm) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(modifier = modifier) {
        items(items = farms, key = { it.farmId }) {
            FarmsListItem(
                farm = it,
                delAction = delAction,
                onDelFarmClick = onDelFarmClick,
                modifier = Modifier.clickable { onFarmClick(it) }
            )
        }
    }
}

@Composable
fun FarmsListItem(
    farm: Farm,
    modifier: Modifier = Modifier,
    delAction: Boolean = false,
    onDelFarmClick: (String) -> Unit = { }
) {

    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(4.dp)
                .width(60.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(24.dp),
                shadowElevation = 12.dp,
                modifier = Modifier.size(60.dp)
            ) {
            }
            Text(
                text = farm.name,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body,
                fontSize = 11.sp
            )
        }

        // Delete Button
        if (delAction) {
            IconButton(
                onClick = { onDelFarmClick(farm.farmId) },
                colors = IconButtonDefaults.iconButtonColors(containerColor = greenDark),
                modifier = Modifier
                    .padding(4.dp)
                    .clip(CircleShape)
                    .size(16.dp)
                    .align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = white,
                    modifier = Modifier.size(14.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun FarmListItemPreview() {
    FarmsListItem(
        farm = Farm(name = "المزرعة الأولى"),
        delAction = true
    )
}