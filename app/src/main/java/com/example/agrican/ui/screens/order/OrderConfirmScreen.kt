package com.example.agrican.ui.screens.order

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.agrican.R
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.spacing

object OrderConfirmDestination: NavigationDestination {
    override val route: String = "order_confirm"
    override val titleRes: Int = R.string.confirm_order
}

@Composable
fun OrderConfirmScreen(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.confirm_order),
                color = greenLight,
                modifier = Modifier.padding(start = MaterialTheme.spacing.medium)
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = {
                    navigateUp()
                },
                modifier = Modifier
                    .padding(MaterialTheme.spacing.medium)
                    .clip(CircleShape)
                    .background(greenDark)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }

        Divider(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.large)
                .height(2.dp)
                .background(gray)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OrderConfirmScreenContent() {
    OrderConfirmScreen(navigateUp = { })
}