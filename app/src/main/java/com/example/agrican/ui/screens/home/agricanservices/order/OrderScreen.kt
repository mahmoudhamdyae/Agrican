package com.example.agrican.ui.screens.home.agricanservices.order

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.agrican.R
import com.example.agrican.ui.components.BackButton
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.screens.home.agricanservices.order.new_order.NewOrderScreen
import com.example.agrican.ui.screens.home.agricanservices.order.order_status.OrderStatusDestination
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight

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

    BackButton(navigateUp = navigateUp) {
        Column(modifier = modifier.fillMaxSize()) {
            // Order Status Button
            Button(
                onClick = { openScreen(OrderStatusDestination.route) },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = greenLight),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 12.dp, start = 16.dp, end = 16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.order_status),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }

            // New Order Button
            Button(
                onClick = { showNewOrder = true },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = greenDark),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp, start = 16.dp, end = 16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.new_order),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }

            // New Order Screen
            if (showNewOrder) {
                NewOrderScreen()
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OrderScreenPreview() {
    OrderScreen(openScreen = { }, navigateUp = { })
}