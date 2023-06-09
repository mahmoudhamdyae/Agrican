package com.example.agrican.ui.screens.order

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.agrican.R
import com.example.agrican.ui.components.BackButton
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.spacing

object OrderStatusDestination: NavigationDestination {
    override val route: String = "order_status"
    override val titleRes: Int = R.string.order_status_title
}

@Composable
fun OrderStatusScreen(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    val orders = listOf<Int>()
    BackButton(navigateUp = navigateUp) {
        Column(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
            modifier = modifier.padding(MaterialTheme.spacing.medium)
        ) {
            Text(
                text = stringResource(id = R.string.order_status),
                color = greenLight,
            )

            Divider(
                modifier = Modifier
                    .height(2.dp)
                    .background(gray)
            )

            OrdersList(orders = orders)
        }
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
            color = greenDark,
        )
    }
}

@Composable
fun OrdersList(
    orders: List<Int>,
    modifier: Modifier = Modifier
) {
    if (orders.isEmpty()) {
        EmptyView(modifier = modifier)
    } else {
        LazyColumn(modifier = modifier) {
            items(3) {
                OrdersListItem()
            }
        }
    }
}

@Composable
fun OrdersListItem(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Surface(
            shape = RoundedCornerShape(MaterialTheme.spacing.medium),
            border = BorderStroke(1.dp, gray),
            modifier = Modifier.padding(bottom = 24.dp)
        ) {
            Row(modifier = Modifier.padding(MaterialTheme.spacing.medium)) {
                Column(modifier = Modifier.weight(3f)) {
                    Row {
                        Text(text = "سماد عالى الفسفور")
                        Spacer(modifier = Modifier.weight(1f))
                        Surface(
                            shape = RoundedCornerShape(MaterialTheme.spacing.medium),
                            border = BorderStroke(1.dp, gray)
                        ) {
                            Text(
                                text = "تقاوي",
                                color = greenDark,
                                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small)
                            )
                        }

                    }

                    Text(text = "السعر 500 جنيهاً")
                    Text(text = "سماد عضوى لتغذية النباتات سماد عضوى لتغذية النباتات سماد عضوى لتغذية النباتات")
                }

                Image(
                    painter = painterResource(id = R.drawable.ic_sunny),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(RoundedCornerShape(MaterialTheme.spacing.medium))
                        .width(50.dp)
                        .height(100.dp)
                        .weight(1f)
                )
            }
        }

        Button(
            onClick = { /*TODO*/ },
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
    OrderStatusScreen(navigateUp = { })
}

@Preview(showBackground = true)
@Composable
fun OrderListItemPreview() {
    OrdersListItem(modifier = Modifier.padding(50.dp))
}