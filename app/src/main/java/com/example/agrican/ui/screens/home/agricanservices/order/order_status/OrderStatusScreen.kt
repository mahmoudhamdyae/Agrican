package com.example.agrican.ui.screens.home.agricanservices.order.order_status

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.agrican.R
import com.example.agrican.domain.model.Order
import com.example.agrican.ui.components.BackButtonTopBar
import com.example.agrican.ui.components.EmptyImage
import com.example.agrican.ui.components.LoadingAnimation
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.body
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.textGray
import com.example.agrican.ui.theme.title
import com.paymob.acceptsdk.IntentConstants
import com.paymob.acceptsdk.PayResponseKeys
import com.paymob.acceptsdk.SaveCardResponseKeys
import com.paymob.acceptsdk.ToastMaker

object OrderStatusDestination: NavigationDestination {
    override val route: String = "order_status"
    override val titleRes: Int = R.string.order_status_title
}

@Composable
fun OrderStatusScreen(
    navigateUp: () -> Unit,
    navigateToLoginScreen: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: OrderViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BackButtonTopBar(
        title = OrderStatusDestination.titleRes,
        navigateUp = navigateUp,
        modifier = modifier
    ) {
        OrderStatusScreenContent(
            uiState = uiState,
            confirmOrder = viewModel::getTokenAndConfirmOrder,
            navigateToLoginScreen = navigateToLoginScreen
        )
    }
}

@Composable
fun OrderStatusScreenContent(
    uiState: OrderUiState,
    confirmOrder: (Context, Order, (Intent) -> Unit) -> Unit,
    navigateToLoginScreen: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.available_offers),
            color = greenLight,
            style = MaterialTheme.typography.title,
            fontSize = 15.sp,
        )

        Divider(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .height(2.dp)
                .background(gray)
        )

        if (uiState.orders != null) {
            OrdersList(
                orders = uiState.orders,
                confirmOrder = confirmOrder,
                navigateToLoginScreen = navigateToLoginScreen
            )
        }
    }
    if (uiState.isLoading) {
        LoadingAnimation()
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
            fontSize = 14.sp
        )
    }
}

@Composable
fun OrdersList(
    orders: List<Order>,
    confirmOrder: (Context, Order, (Intent) -> Unit) -> Unit,
    navigateToLoginScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (orders.isEmpty()) {
        EmptyView(modifier = modifier)
    } else {
        LazyColumn(modifier = modifier) {
            items(orders.size) {
                OrdersListItem(
                    order = orders[it],
                    confirmOrder = confirmOrder,
                    navigateToLoginScreen = navigateToLoginScreen
                )
            }

            // Spacer for Bottom Navigation Bar
            item {
                Spacer(modifier = Modifier.height(60.dp))
            }
        }
    }
}

@Composable
fun OrdersListItem(
    order: Order,
    confirmOrder: (Context, Order, (Intent) -> Unit) -> Unit,
    navigateToLoginScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, gray),
            modifier = Modifier.padding(bottom = 24.dp)
        ) {
            Row(modifier = Modifier.height(IntrinsicSize.Max)) {
                Column(modifier = Modifier
                    .padding(16.dp)
                    .weight(2f)) {
                    Row {
                        // Order Name
                        Text(
                            text = order.name,
                            color = textGray,
                            style = MaterialTheme.typography.title,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.SemiBold,
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            border = BorderStroke(1.dp, gray),
                            modifier = Modifier.padding(top = 8.dp)
                        ) {
                            // Title
                            Text(
                                text = order.t,
                                color = greenDark,
                                style = MaterialTheme.typography.body,
                                fontSize = 10.sp,
                                modifier = Modifier.padding(horizontal = 4.dp)
                            )
                        }

                    }

                    // Order Price
                    Text(
                        text = "السعر ${order.price} جنيهاً",
                        style = MaterialTheme.typography.body,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                    )
                    // Order Description
                    Text(
                        text = order.description,
                        style = MaterialTheme.typography.body,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }

                EmptyImage(
                    modifier = Modifier
                        .padding(10.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .width(50.dp)
                        .fillMaxHeight()
                        .weight(1f)
                )
            }
        }

        val context = LocalContext.current
        val paymentLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult(),
            onResult = { result ->

                val extras = result.data?.extras
                when (result.resultCode) {
                    IntentConstants.USER_CANCELED -> {
                        // User canceled and did no payment request was fired
                        ToastMaker.displayShortToast(context as Activity, "User canceled!!")
                    }
                    IntentConstants.MISSING_ARGUMENT -> {
                        // You forgot to pass an important key-value pair in the intent's extras
                        ToastMaker.displayShortToast(
                            context as Activity,
                            "Missing Argument == " + extras!!.getString(IntentConstants.MISSING_ARGUMENT_VALUE)
                        )
                    }
                    IntentConstants.TRANSACTION_ERROR -> {
                        // An error occurred while handling an APIs response
                        ToastMaker.displayShortToast(
                            context as Activity,
                            "Reason == " + extras!!.getString(IntentConstants.TRANSACTION_ERROR_REASON)
                        )
                    }
                    IntentConstants.TRANSACTION_REJECTED -> {
                        // User attempted to pay but their transaction was rejected
                        Toast.makeText(context, "REJECTED", Toast.LENGTH_SHORT).show()
                        // Use the static keys declared in PayResponseKeys to extract the fields you want
                        ToastMaker.displayShortToast(context as Activity, extras!!.getString(PayResponseKeys.DATA_MESSAGE))
                    }
                    IntentConstants.TRANSACTION_REJECTED_PARSING_ISSUE -> {
                        // User attempted to pay but their transaction was rejected. An error occurred while reading the returned JSON
                        ToastMaker.displayShortToast(
                            context as Activity,
                            extras!!.getString(IntentConstants.RAW_PAY_RESPONSE)
                        )
                    }
                    IntentConstants.TRANSACTION_SUCCESSFUL -> {
                        // User finished their payment successfully
                        Toast.makeText(context, "SUCCESS", Toast.LENGTH_SHORT).show()

                        // Use the static keys declared in PayResponseKeys to extract the fields you want
                        ToastMaker.displayShortToast(context as Activity, extras!!.getString(PayResponseKeys.DATA_MESSAGE))
                    }
                    IntentConstants.TRANSACTION_SUCCESSFUL_PARSING_ISSUE -> {
                        // User finished their payment successfully. An error occurred while reading the returned JSON.
                        ToastMaker.displayShortToast(context as Activity, "TRANSACTION_SUCCESSFUL - Parsing Issue")
                    }
                    IntentConstants.TRANSACTION_SUCCESSFUL_CARD_SAVED -> {
                        // User finished their payment successfully and card was saved.
                        Toast.makeText(context as Activity, "SUCCESS CARD SAVE", Toast.LENGTH_SHORT).show()

                        // Use the static keys declared in PayResponseKeys to extract the fields you want
                        // Use the static keys declared in SaveCardResponseKeys to extract the fields you want
                        ToastMaker.displayShortToast(
                            context,
                            "Token == " + extras!!.getString(SaveCardResponseKeys.TOKEN)
                        )
                    }
                    IntentConstants.USER_CANCELED_3D_SECURE_VERIFICATION -> {
                        ToastMaker.displayShortToast(context as Activity, "User canceled 3-d secure verification!!")

                        // Note that a payment process was attempted. You can extract the original returned values
                        // Use the static keys declared in PayResponseKeys to extract the fields you want
                        ToastMaker.displayShortToast(context, extras!!.getString(PayResponseKeys.PENDING))
                    }
                    IntentConstants.USER_CANCELED_3D_SECURE_VERIFICATION_PARSING_ISSUE -> {
                        ToastMaker.displayShortToast(
                            context as Activity,
                            "User canceled 3-d secure verification - Parsing Issue!!"
                        )

                        // Note that a payment process was attempted.
                        // User finished their payment successfully. An error occurred while reading the returned JSON.
                        ToastMaker.displayShortToast(
                            context,
                            extras!!.getString(IntentConstants.RAW_PAY_RESPONSE)
                        )
                    }
                    99 -> {
                        if (extras!!.getBoolean("openAndClearKey")) {
                            navigateToLoginScreen()
                        }
                    }
                }
            }
        )

        // Confirm Button
        Button(
            onClick = { confirmOrder(context, order) {
                paymentLauncher.launch(it)
            } },
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
    OrdersList(
        orders = listOf(
            Order(
                name = "سماد عالى الفسفور",
                t = "تقاوي",
                price = 500.0,
                description = "سماد عضوى لتغذية النباتات سماد عضوى لتغذية النباتات سماد عضوى لتغذية النباتات"
            )
        ),
        confirmOrder = { _, _, _ -> },
        navigateToLoginScreen = { }
    )
}

@Preview(showBackground = true)
@Composable
fun OrderListItemPreview() {
    OrderStatusScreenContent(
        uiState = OrderUiState(),
        confirmOrder = { _, _, _ -> },
        navigateToLoginScreen = { }
    )
}