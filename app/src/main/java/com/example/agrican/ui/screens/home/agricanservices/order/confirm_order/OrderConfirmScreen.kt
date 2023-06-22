package com.example.agrican.ui.screens.home.agricanservices.order.confirm_order

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.agrican.R
import com.example.agrican.ui.components.BackButton
import com.example.agrican.ui.components.SimpleTextField
import com.example.agrican.ui.theme.body
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.title

@Composable
fun OrderConfirmScreen(
    navigateUp: () -> Unit,
    cardName: String,
    changeCardName: (String) -> Unit,
    cardId: String,
    changeCardId: (String) -> Unit,
    year: String,
    changeYear: (String) -> Unit,
    month: String,
    changeMonth: (String) -> Unit,
    cvc: String,
    changeCvc: (String) -> Unit,
    orderPrice: Double,
    buy: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackButton(navigateUp = navigateUp) {

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
                .padding(bottom = 60.dp)
        ) {
            Text(
                text = stringResource(id = R.string.confirm_order),
                color = greenLight,
                style = MaterialTheme.typography.title,
                fontSize = 15.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Divider(
                modifier = Modifier
                    .height(2.dp)
                    .background(gray)
                    .padding(vertical = 8.dp)
            )

            OrderConfirmScreenContent(
                cardName = cardName,
                changeCardName = changeCardName,
                cardId = cardId,
                changeCardId = changeCardId,
                year = year,
                changeYear = changeYear,
                month = month,
                changeMonth = changeMonth,
                cvc = cvc,
                changeCvc = changeCvc,
                buy = buy,
                orderPrice = orderPrice
            )
        }
    }
}

@Composable
fun OrderConfirmScreenContent(
    cardName: String,
    changeCardName: (String) -> Unit,
    cardId: String,
    changeCardId: (String) -> Unit,
    year: String,
    changeYear: (String) -> Unit,
    month: String,
    changeMonth: (String) -> Unit,
    cvc: String,
    changeCvc: (String) -> Unit,
    buy: () -> Unit,
    orderPrice: Double,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    var isCash by rememberSaveable { mutableStateOf(OrderWay.CASH) }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        // Cash
        Surface(
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, if (isCash == OrderWay.CASH) greenDark else gray),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp)
            ) {
                RadioButton(
                    selected = isCash == OrderWay.CASH,
                    onClick = { isCash = OrderWay.CASH }
                )
                Text(
                    text = stringResource(id = R.string.cash),
                    color = Color(0xff5a5a5a),
                    style = MaterialTheme.typography.body,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        // Visa
        Surface(
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, if (isCash == OrderWay.VISA) greenDark else gray),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(16.dp)
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

        // Card Name
        Text(
            text = stringResource(id = R.string.card_name),
            color = Color(0xff5a5a5a),
            fontSize = 12.sp,
            style = MaterialTheme.typography.body,
        )
        SimpleTextField(
            value = cardName,
            onNewValue = changeCardName,
            placeHolder = { },
            focusManager = focusManager
        )

        // Personal Id
        Text(
            text = stringResource(id = R.string.card_number),
            color = Color(0xff5a5a5a),
            fontSize = 12.sp,
            style = MaterialTheme.typography.body,
        )
        SimpleTextField(
            value = cardId,
            onNewValue = {
                if (cvc.length < 17) { changeCardId(it) }
            },
            placeHolder = { },
            focusManager = focusManager
        )

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                // Expired Date
                Text(
                    text = stringResource(id = R.string.Expiration),
                    color = Color(0xff5a5a5a),
                    style = MaterialTheme.typography.body,
                    fontSize = 12.sp
                )

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    // Year
                    SimpleTextField(
                        value = year,
                        onNewValue = {
                            if (cvc.length < 3) { changeYear(it) }
                        },
                        placeHolder = {
                            Text(text = "YY")
                        },
                        focusManager = focusManager,
                        imeAction = ImeAction.Done,
                        modifier = Modifier.weight(1f)
                    )

                    // Month
                    SimpleTextField(
                        value = month,
                        onNewValue = {
                            if (cvc.length < 3) { changeMonth(it) }
                        },
                        placeHolder = {
                            Text(text = "MM")
                        },
                        focusManager = focusManager,
                        imeAction = ImeAction.Done,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                // CW/CVC
                Text(
                    text = stringResource(id = R.string.cvv),
                    color = Color(0xff5a5a5a),
                    style = MaterialTheme.typography.body,
                    fontSize = 14.sp
                )
                SimpleTextField(
                    value = cvc,
                    onNewValue = {
                        if (cvc.length < 4) { changeCvc(it) }
                    },
                    placeHolder = { },
                    focusManager = focusManager,
                    imeAction = ImeAction.Done
                )
            }
        }

        // Confirm Order Button
        Button(
            onClick = buy,
            colors = ButtonDefaults.buttonColors(containerColor = greenDark) ,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = stringResource(id = R.string.sum),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                    )

                    Row {
                        Text(
                            text = orderPrice.toString(),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Spacer(modifier = Modifier.padding(end = 8.dp))
                        Text(
                            text = stringResource(id = R.string.pound),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }

                Text(
                    text = stringResource(id = R.string.pay_button),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Icon(painter = painterResource(id = R.drawable.cart), contentDescription = null)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderConfirmScreenContent() {
    OrderConfirmScreenContent(
        cardName = "",
        changeCardName = { },
        cardId = "",
        changeCardId = { },
        year = "",
        changeYear = { },
        month = "",
        changeMonth = { },
        cvc = "",
        changeCvc = { },
        buy = { },
        orderPrice = 100.0
    )
}