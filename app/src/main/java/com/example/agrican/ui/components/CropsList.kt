package com.example.agrican.ui.components

import androidx.compose.foundation.clickable
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
import com.example.agrican.R
import com.example.agrican.domain.model.Crop
import com.example.agrican.ui.theme.body
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CropsList(
    crops: List<Crop>,
    setSelectedCrop: (Crop) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(modifier = modifier) {
        items(crops.size) {
            CropsListItem(
                crop = crops[it],
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { setSelectedCrop(crops[it]) }
            )
        }
    }
}

@Composable
fun CropsListItem(
    crop: Crop,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(32.dp),
        shadowElevation = 16.dp,
        modifier = modifier.size(75.dp)
    ) {
        Box {
            Icon(
                painter = painterResource(id = R.drawable.ic_visibility_on),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
            Text(
                text = crop.name,
                style = MaterialTheme.typography.body,
                fontSize = 10.sp,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CropsListPreview() {
    val crops = listOf(
        Crop(name = "الأرز"),
        Crop(name = "الأرز"),
        Crop(name = "الأرز"),
        Crop(name = "الأرز"),
        Crop(name = "الأرز"),
        Crop(name = "الأرز"),
    )
    CropsList(crops = crops, setSelectedCrop = { })
}