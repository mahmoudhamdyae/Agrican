package com.example.agrican.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.agrican.R
import com.example.agrican.ui.theme.spacing

@Composable
fun CropsList(
    modifier: Modifier = Modifier
) {
    LazyRow(modifier = modifier) {
        items(9) {
            CropsListItem(modifier = Modifier.padding(MaterialTheme.spacing.small))
        }
    }
}

@Composable
fun CropsListItem(
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(MaterialTheme.spacing.large),
        shadowElevation = MaterialTheme.spacing.medium,
        modifier = modifier.size(75.dp)
    ) {
        Box {
            Icon(
                painter = painterResource(id = R.drawable.ic_visibility_on),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
            Text(text = "الأرز", modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CropsListPreview() {
    CropsList()
}