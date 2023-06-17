package com.example.agrican.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import com.example.agrican.R
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.spacing
import com.example.agrican.ui.theme.white

@Composable
fun BackButton(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {},
) {
    Box(modifier = modifier.fillMaxSize()) {

        content()

        IconButton(
            onClick = {
                navigateUp()
            },
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium)
                .clip(CircleShape)
                .background(greenDark)
                .align(Alignment.TopEnd)
                .size(MaterialTheme.spacing.large)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
                contentDescription = null,
                tint = white
            )
        }
    }
}