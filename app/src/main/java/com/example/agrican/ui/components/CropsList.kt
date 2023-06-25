package com.example.agrican.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.agrican.R
import com.example.agrican.domain.model.Crop
import com.example.agrican.ui.theme.body
import com.example.agrican.ui.theme.gray

@Composable
fun CropsList(
    isLoading: Boolean,
    crops: List<Crop>?,
    setSelectedCrop: (Crop) -> Unit,
    modifier: Modifier = Modifier
) {
    if (isLoading && crops == null) {
        LazyRow(modifier = modifier) {
            items(10) {
                CropsListItemLoading(modifier = Modifier.padding(8.dp))
            }
        }
    } else {
        LazyRow(modifier = modifier) {
            items(crops!!.size) {
                CropsListItem(
                    crop = crops[it],
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable { setSelectedCrop(crops[it]) }
                )
            }
        }
    }
}

@Composable
fun CropsListItemLoading(
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(24.dp),
        shadowElevation = 16.dp,
        modifier = modifier
            .height(75.dp)
            .width(65.dp)
    ) {
        Box {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .padding(bottom = 8.dp)
                    .clip(CircleShape)
                    .shimmerEffect()
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(12.dp)
                    .padding(bottom = 4.dp)
                    .padding(horizontal = 16.dp)
                    .shimmerEffect()
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
        shape = RoundedCornerShape(24.dp),
        shadowElevation = 16.dp,
        modifier = modifier
            .height(75.dp)
            .width(65.dp)
    ) {
        Box {
            Icon(
                painter = painterResource(id = R.drawable.ic_sunny),
                contentDescription = null,
                tint = gray,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp)
            )
            Text(
                text = crop.name,
                style = MaterialTheme.typography.body,
                fontSize = 10.sp,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 4.dp)
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
    CropsList(isLoading = false, crops = crops, setSelectedCrop = { })
}

@Preview(showBackground = true)
@Composable
fun CropsListLoadingPreview() {
    CropsList(isLoading = true, crops = null, setSelectedCrop = { })
}