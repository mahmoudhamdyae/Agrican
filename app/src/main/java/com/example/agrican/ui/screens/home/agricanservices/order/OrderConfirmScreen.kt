package com.example.agrican.ui.screens.home.agricanservices.order

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.agrican.R
import com.example.agrican.ui.components.BackButton
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.gray
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
    BackButton(navigateUp = navigateUp, modifier = modifier) {

        Column(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
            modifier = Modifier.padding(
                horizontal = MaterialTheme.spacing.large,
                vertical = MaterialTheme.spacing.medium
            )
        ) {
            Text(
                text = stringResource(id = R.string.confirm_order),
                color = greenLight,
                modifier = Modifier.padding(start = MaterialTheme.spacing.medium)
            )

            Divider(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.medium)
                    .height(2.dp)
                    .background(gray)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderConfirmScreenContent() {
    OrderConfirmScreen(navigateUp = { })
}