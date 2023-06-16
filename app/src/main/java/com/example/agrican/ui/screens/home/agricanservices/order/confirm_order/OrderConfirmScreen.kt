package com.example.agrican.ui.screens.home.agricanservices.order.confirm_order

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.agrican.R
import com.example.agrican.ui.components.BackButton
import com.example.agrican.ui.components.SimpleTextField
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.spacing

object OrderConfirmDestination: NavigationDestination {
    override val route: String = "order_confirm"
    override val titleRes: Int = R.string.confirm_order
    const val orderIdArg = "order_id"
    val routeWithArgs = "$route/{$orderIdArg}"
    val arguments = listOf(
        navArgument(orderIdArg) { type = NavType.StringType },
    )
}

@Composable
fun OrderConfirmScreen(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: OrderConfirmViewModel = hiltViewModel()
) {
    BackButton(navigateUp = navigateUp) {

        Column(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .padding(MaterialTheme.spacing.medium)
        ) {
            Text(
                text = stringResource(id = R.string.confirm_order),
                color = greenLight,
                modifier = Modifier.padding(vertical = MaterialTheme.spacing.small)
            )

            Divider(
                modifier = Modifier
                    .height(2.dp)
                    .background(gray)
                    .padding(vertical = MaterialTheme.spacing.small)
            )

            OrderConfirmScreenContent(buy = viewModel::buy)
        }
    }
}

@Composable
fun OrderConfirmScreenContent(
    buy: (OrderRequest) -> Unit,
    modifier: Modifier = Modifier
) {
    var cardId by rememberSaveable { mutableStateOf("") }
    var expireDate by rememberSaveable { mutableStateOf("") }
    var cvc by rememberSaveable { mutableStateOf("") }

    val focusManager = LocalFocusManager.current

    var isCash by rememberSaveable { mutableStateOf(OrderWay.CASH) }

    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
        modifier = modifier.fillMaxWidth()
    ) {
        // Cash
        Surface(
            shape = RoundedCornerShape(MaterialTheme.spacing.medium),
            border = BorderStroke(1.dp, gray),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(MaterialTheme.spacing.medium)
            ) {
                RadioButton(
                    selected = isCash == OrderWay.CASH,
                    onClick = { isCash = OrderWay.CASH }
                )
                Text(
                    text = stringResource(id = R.string.cash),
                    modifier = Modifier.padding(MaterialTheme.spacing.medium)
                )
            }
        }

        // Visa
        Surface(
            shape = RoundedCornerShape(MaterialTheme.spacing.medium),
            border = BorderStroke(1.dp, gray),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
                modifier = Modifier.padding(MaterialTheme.spacing.medium)
            ) {
                RadioButton(
                    selected = isCash == OrderWay.VISA,
                    onClick = { isCash = OrderWay.VISA }
                )

                // Visa Image
                Image(
                    painter = painterResource(id = R.drawable.visa),
                    contentDescription = null,
                    modifier = Modifier.weight(1f)
                )

                // MasterCard Image
                Image(
                    painter = painterResource(id = R.drawable.mastercard),
                    contentDescription = null,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Personal Id
        Text(text = stringResource(id = R.string.card_id))
        SimpleTextField(
            value = cardId,
            onNewValue = { cardId = it },
            placeHolder = { },
            focusManager = focusManager
        )

        Row(horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)) {
            Column(
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
                modifier = Modifier.weight(1f)
            ) {
                // Expired Date
                Text(text = stringResource(id = R.string.expire_date))
                SimpleTextField(
                    value = expireDate,
                    onNewValue = { expireDate = it },
                    placeHolder = { },
                    focusManager = focusManager,
                    imeAction = ImeAction.Done
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
                modifier = Modifier.weight(1f)
            ) {
                // CW/CVC
                Text(text = stringResource(id = R.string.cvc))
                SimpleTextField(
                    value = cvc,
                    onNewValue = { cvc = it },
                    placeHolder = { },
                    focusManager = focusManager,
                    imeAction = ImeAction.Done
                )
            }
        }

        // Confirm Order Button
        Button(
            onClick = { buy(OrderRequest(
                orderWay = isCash,
                cardId = cardId,
                expireDate = expireDate,
                cvc = cvc
            )) }, colors = ButtonDefaults.buttonColors(containerColor = greenDark) ,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = stringResource(id = R.string.sum))

                    Row {
                        Text(text = "00.00")
                        Spacer(modifier = Modifier.padding(end = MaterialTheme.spacing.small))
                        Text(text = stringResource(id = R.string.pound))
                    }
                }

                Text(text = stringResource(id = R.string.complete_buying))

                Icon(painter = painterResource(id = R.drawable.cart), contentDescription = null)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderConfirmScreenContent() {
    OrderConfirmScreenContent(buy = { }, modifier = Modifier.fillMaxSize())
}