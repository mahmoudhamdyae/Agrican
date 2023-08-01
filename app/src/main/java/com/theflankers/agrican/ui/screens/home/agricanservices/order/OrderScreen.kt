package com.theflankers.agrican.ui.screens.home.agricanservices.order

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.theflankers.agrican.R
import com.theflankers.agrican.ui.components.BackButtonTopBar
import com.theflankers.agrican.ui.navigation.NavigationDestination
import com.theflankers.agrican.ui.screens.home.agricanservices.order.new_order.NewOrderScreen
import com.theflankers.agrican.ui.screens.home.agricanservices.order.order_status.OrderStatusDestination
import com.theflankers.agrican.ui.theme.greenDark

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

    BackButtonTopBar(
        title = OrderDestination.titleRes,
        navigateUp = navigateUp,
        modifier = modifier,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().padding(top = 16.dp)
        ) {
            // Order Status Button
            Button(
                onClick = { openScreen(OrderStatusDestination.route) },
                shape = RoundedCornerShape(32.dp),
                colors = ButtonDefaults.buttonColors(containerColor = greenDark),
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .height(45.dp)
                    .width(225.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.order_status),
                    fontSize = 14.sp
                )
            }

            // New Order Button
            Button(
                onClick = { showNewOrder = true },
                shape = RoundedCornerShape(32.dp),
                colors = ButtonDefaults.buttonColors(containerColor = greenDark),
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .height(45.dp)
                    .width(225.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.new_order),
                    fontSize = 14.sp
                )
            }

            // New Order Screen
            if (showNewOrder) {
                NewOrderScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderScreenPreview() {
    OrderScreen(openScreen = { }, navigateUp = { })
}