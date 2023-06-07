package com.example.agrican.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.agrican.ui.theme.spacing

@Composable
fun Background(
    body1: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    body2: @Composable (() -> Unit)? = null,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .weight(if (body2 != null) 4f else 2f)
                .clip(
                    RoundedCornerShape(
                        bottomStart = MaterialTheme.spacing.extraLarge,
                        bottomEnd = MaterialTheme.spacing.extraLarge
                    )
                )
                .background(MaterialTheme.colorScheme.background)
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.large)
        ) {
            body1()
        }
        Spacer(modifier = Modifier.weight(1f))
        if (body2 != null) {
            body2()
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}