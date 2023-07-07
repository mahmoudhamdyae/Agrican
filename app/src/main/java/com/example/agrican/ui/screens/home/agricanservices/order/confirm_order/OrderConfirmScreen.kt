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
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.agrican.R
import com.example.agrican.ui.components.BackButtonTopBar
import com.example.agrican.ui.components.LabelWithTextField
import com.example.agrican.ui.screens.home.agricanservices.order.OrderDestination
import com.example.agrican.ui.theme.body
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.title
import com.example.agrican.ui.theme.white
import com.steliospapamichail.creditcardmasker.viewtransformations.CardNumberMask
import com.steliospapamichail.creditcardmasker.viewtransformations.ExpirationDateMask

@Composable
fun OrderConfirmScreen(
    navigateUp: () -> Unit,
    cardName: String,
    changeCardName: (String) -> Unit,
    cardId: String,
    changeCardId: (String) -> Unit,
    date: String,
    changeMonth: (String) -> Unit,
    cvc: String,
    changeCvc: (String) -> Unit,
    orderPrice: Double,
    buy: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackButtonTopBar(
        title = OrderDestination.titleRes,
        navigateUp = navigateUp,
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
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
                date = date,
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
    date: String,
    changeMonth: (String) -> Unit,
    cvc: String,
    changeCvc: (String) -> Unit,
    buy: () -> Unit,
    orderPrice: Double,
    modifier: Modifier = Modifier
) {
    var isCash: Boolean? by rememberSaveable { mutableStateOf(null) }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        // Cash
        Surface(
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, if (isCash == true) greenDark else gray),
            color = white,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp)
            ) {
                RadioButton(
                    selected = isCash == true,
                    onClick = { isCash = true }
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
            border = BorderStroke(1.dp, if (isCash == false) greenDark else gray),
            color = white,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                RadioButton(
                    selected = isCash == false,
                    onClick = { isCash = false }
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

        if (isCash == false) {
            TextFields(
                cardName = cardName,
                changeCardName = changeCardName,
                cardId = cardId,
                changeCardId = changeCardId,
                date = date,
                changeMonth = changeMonth,
                cvc = cvc,
                changeCvc = changeCvc,
                modifier = modifier
            )
        }

        // Confirm Order Button
        if (isCash != null) {
            ConfirmOrderButton(buy, orderPrice)
        }
    }

    Spacer(modifier = Modifier.height(100.dp))
}

@Composable
fun ConfirmOrderButton(
    buy: () -> Unit,
    orderPrice: Double,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = buy,
        colors = ButtonDefaults.buttonColors(containerColor = greenDark) ,
        modifier = modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
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
                text = stringResource(id = R.string.pay),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Icon(painter = painterResource(id = R.drawable.cart), contentDescription = null)
        }
    }
}

@Composable
fun TextFields(
    cardName: String,
    changeCardName: (String) -> Unit,
    cardId: String,
    changeCardId: (String) -> Unit,
    date: String,
    changeMonth: (String) -> Unit,
    cvc: String,
    changeCvc: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        // Card Name
        LabelWithTextField(
            value = cardName,
            onNewValue = changeCardName,
            hint = R.string.card_name,
            label = R.string.card_name,
            focusManager = focusManager
        )

        // Personal Id
        LabelWithTextField(
            value = cardId,
            onNewValue = { changeCardId(it.take(16)) },
            hint = R.string.card_id,
            label = R.string.card_id,
            focusManager = focusManager,
            visualTransformation = CardNumberMask("-"),
        )

        Row(modifier = Modifier.height(35.dp)) {
            // Expired Date
            LabelWithTextField(
                value = date,
                onNewValue = { changeMonth(it.take(4)) },
                hint = R.string.expire_date_hint,
                label = R.string.expire_date,
                focusManager = focusManager,
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Go,
                visualTransformation = ExpirationDateMask(),
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            // CW/CVC
            LabelWithTextField(
                value = cvc,
                onNewValue = { changeCvc(it.take(3)) },
                hint = null,
                label = R.string.cvc,
                focusManager = focusManager,
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number,
                modifier = Modifier.weight(1f)
            )
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
        date = "",
        changeMonth = { },
        cvc = "",
        changeCvc = { },
        buy = { },
        orderPrice = 100.0
    )
}

@Preview
@Composable
fun TextFieldsPreview() {
    TextFields(
        cardName = "",
        changeCardName = { },
        cardId = "",
        changeCardId = { },
        date = "",
        changeMonth = { },
        cvc = "",
        changeCvc = { }
    )
}