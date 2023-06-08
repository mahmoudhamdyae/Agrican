package com.example.agrican.ui.screens.order

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.tooling.preview.Preview
import com.example.agrican.R
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.spacing

object OrderDestination: NavigationDestination {
    override val route: String = "order"
    override val titleRes: Int = R.string.order
}

@Composable
fun OrderScreen(
    openScreen: (String) -> Unit,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showNewOrder: Boolean by rememberSaveable { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxSize()) {
        Box {
            Button(
                onClick = { openScreen(OrderStatusDestination.route) },
                colors = ButtonDefaults.buttonColors(containerColor = greenLight),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(MaterialTheme.spacing.extraSmall))
                    .padding(MaterialTheme.spacing.small)
            ) {
                Text(
                    text = stringResource(id = R.string.order_status),
                    modifier = Modifier.padding(MaterialTheme.spacing.medium)
                )
            }

            IconButton(
                onClick = { navigateUp() },
                modifier = Modifier
                    .padding(MaterialTheme.spacing.medium)
                    .clip(CircleShape)
                    .background(greenDark)
                    .align(Alignment.CenterEnd)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }

        Button(
            onClick = { showNewOrder = true },
            colors = ButtonDefaults.buttonColors(containerColor = greenDark),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(MaterialTheme.spacing.extraSmall))
                .padding(MaterialTheme.spacing.small)
        ) {
            Text(
                text = stringResource(id = R.string.new_order),
                modifier = Modifier.padding(MaterialTheme.spacing.medium)
            )
        }
        if (showNewOrder) {
            NewOrder()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OrderScreenPreview() {
    OrderScreen(openScreen = { }, navigateUp = { })
}