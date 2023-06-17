package com.example.agrican.ui.screens.home.agricanservices.order.order_status

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.agrican.R
import com.example.agrican.domain.model.Order
import com.example.agrican.ui.components.BackButton
import com.example.agrican.ui.components.EmptyImage
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.screens.home.agricanservices.order.confirm_order.OrderConfirmDestination
import com.example.agrican.ui.theme.body
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.spacing
import com.example.agrican.ui.theme.textGray
import com.example.agrican.ui.theme.title

object OrderStatusDestination: NavigationDestination {
    override val route: String = "order_status"
    override val titleRes: Int = R.string.order_status_title
}

@Composable
fun OrderStatusScreen(
    navigateUp: () -> Unit,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: OrderViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BackButton(navigateUp = navigateUp) {
        OrderStatusScreenContent(uiState = uiState, openScreen = openScreen, modifier = modifier)
    }
}

@Composable
fun OrderStatusScreenContent(
    uiState: OrderUiState,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
        modifier = modifier.padding(MaterialTheme.spacing.medium)
    ) {
        Text(
            text = stringResource(id = R.string.order_status),
            color = greenLight,
            style = MaterialTheme.typography.title,
        )

        Divider(
            modifier = Modifier
                .height(MaterialTheme.spacing.dp_2)
                .background(gray)
        )

        OrdersList(orders = uiState.orders, openScreen = openScreen)
    }
}

@Composable
fun EmptyView(
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.empty_orders),
            style = MaterialTheme.typography.title,
        )
    }
}

@Composable
fun OrdersList(
    orders: List<Order>,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    if (orders.isEmpty()) {
        EmptyView(modifier = modifier)
    } else {
        LazyColumn(modifier = modifier) {
            items(orders.size) {
                OrdersListItem(order = orders[it], openScreen = openScreen)
            }
        }
    }
}

@Composable
fun OrdersListItem(
    order: Order,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Surface(
            shape = RoundedCornerShape(MaterialTheme.spacing.medium),
            border = BorderStroke(MaterialTheme.spacing.dp_1, gray),
            modifier = Modifier.padding(bottom = MaterialTheme.spacing.dp_24)
        ) {
            Row(modifier = Modifier.padding(MaterialTheme.spacing.medium)) {
                Column(modifier = Modifier.weight(3f)) {
                    Row {
                        // Order Name
                        Text(
                            text = order.name,
                            color = textGray,
                            style = MaterialTheme.typography.title
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Surface(
                            shape = RoundedCornerShape(MaterialTheme.spacing.medium),
                            border = BorderStroke(MaterialTheme.spacing.dp_1, gray)
                        ) {
                            // Title
                            Text(
                                text = order.t,
                                color = greenDark,
                                style = MaterialTheme.typography.body,
                                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small)
                            )
                        }

                    }

                    // Order Price
                    Text(
                        text = "السعر ${order.price} جنيهاً",
                        style = MaterialTheme.typography.body
                    )
                    // Order Description
                    Text(
                        text = order.description,
                        style = MaterialTheme.typography.body,
                    )
                }

                EmptyImage(
                    modifier = Modifier
                        .clip(RoundedCornerShape(MaterialTheme.spacing.medium))
                        .width(MaterialTheme.spacing.dp_50)
                        .height(MaterialTheme.spacing.dp_100)
                        .weight(1f)
                )
            }
        }

        // Confirm Button
        Button(
            onClick = { openScreen("${OrderConfirmDestination.route}/${order.orderId}") },
            colors = ButtonDefaults.buttonColors(containerColor = greenDark),
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text(
                text = stringResource(id = R.string.confirm_order),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderStatusPreview() {
    OrderStatusScreen(navigateUp = { }, openScreen = { })
}

@Preview(showBackground = true)
@Composable
fun OrderListItemPreview() {
    OrderStatusScreenContent(uiState = OrderUiState(), openScreen = {})
}