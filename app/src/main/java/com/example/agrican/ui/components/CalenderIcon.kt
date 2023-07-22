package com.example.agrican.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.agrican.R
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.white

@Composable
fun CalenderIcon(
    modifier: Modifier = Modifier
) {
    Surface(
        color = greenLight,
        modifier = modifier
            .size(36.dp)
            .clip(CircleShape)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.calender),
            contentDescription = null,
            tint = white,
            modifier = Modifier
                .padding(6.dp)
                .size(16.dp)
        )
    }
}

@Preview
@Composable
fun CalenderIconPreview() {
    CalenderIcon()
}