package com.example.agrican.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.agrican.R
import com.example.agrican.ui.screens.home.main.MainDestination
import com.example.agrican.ui.theme.greenDark

@Composable
fun ReturnToMainButton(
    openAndClear: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { openAndClear(MainDestination.route) },
        colors = ButtonDefaults.buttonColors(containerColor = greenDark),
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.return_to_main_button),
                fontSize = 14.sp
            )

            Icon(
                painter = painterResource(id = R.drawable.main),
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}