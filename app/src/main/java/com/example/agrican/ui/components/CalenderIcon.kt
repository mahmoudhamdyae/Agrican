package com.example.agrican.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.agrican.R
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.white

@Composable
fun CalenderIcon(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(containerColor = greenLight),
        modifier = modifier.clip(CircleShape).size(36.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.calender),
            contentDescription = null,
            tint = white,
            modifier = Modifier.size(16.dp)
        )
    }
}