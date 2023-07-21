package com.example.agrican.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.agrican.R
import com.example.agrican.domain.model.Crop
import com.example.agrican.ui.theme.body
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.greenDark

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
    } else if (crops != null) {
        Box(contentAlignment = Alignment.Center) {

            var selectedCrop: Crop? by rememberSaveable { mutableStateOf(null) }

            Box(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .clickable {
                        if (selectedCrop != null) selectedCrop = null
                    }
            ) {
                LazyRow(modifier = modifier) {
                    items(items = crops, key = { it.cropId }) {
                        CropsListItem(
                            crop = it,
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable {
                                    selectedCrop = if (selectedCrop == null) {
                                        it
                                    } else {
                                        null
                                    }
                                    setSelectedCrop(it)
                                }
                        )
                    }
                    items(items = crops, key = { it.cropId }) {
                        CropsListItem(
                            crop = it,
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable {
                                    selectedCrop = if (selectedCrop == null) {
                                        it
                                    } else {
                                        null
                                    }
                                    setSelectedCrop(it)
                                }
                        )
                    }
                }

                if (selectedCrop != null) {
                    Box(
                        modifier= Modifier
                            .height(90.dp)
                            .fillMaxWidth()
                            .background(Color(0x7CC8E7C8))
                    )
                }
            }

            selectedCrop?.let {
                SelectedCrop(
                    crop = it,
                    modifier = Modifier.align(Alignment.CenterEnd)
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
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 4.dp)
                    .padding(horizontal = 4.dp)
            )
        }
    }
}

@Composable
fun SelectedCrop(
    crop: Crop,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Surface {
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier
                    .width(90.dp)
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                // Selected Crop Label
                Text(
                    text = stringResource(id = R.string.selected_crop),
                    textAlign = TextAlign.Center,
                    color = greenDark,
                    fontSize = 10.sp
                )
                CropsListItem(crop = crop)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CropsListPreview() {
    val crops = listOf(
        Crop(name = "نبات الياسمين"),
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