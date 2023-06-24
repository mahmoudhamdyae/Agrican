package com.example.agrican.ui.screens.home.agricanservices.order.confirm_order

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
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
import com.example.agrican.ui.components.BackButton
import com.example.agrican.ui.components.SimpleTextField
import com.example.agrican.ui.screens.home.TopBar
import com.example.agrican.ui.theme.body
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.textGray
import com.example.agrican.ui.theme.title
import com.example.agrican.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
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
    openAndClear: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.order),
                        color = textGray,
                        style = MaterialTheme.typography.title
                    )
                },
                openAndClear = openAndClear,
            )
        }
    ) { contentPadding ->
        BackButton(
            navigateUp = navigateUp,
            modifier = modifier.padding(contentPadding)
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

    var isCash by rememberSaveable { mutableStateOf(true) }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        // Cash
        Surface(
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, if (isCash) greenDark else gray),
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
                    selected = isCash,
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
            border = BorderStroke(1.dp, if (!isCash) greenDark else gray),
            color = white,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                RadioButton(
                    selected = !isCash,
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
            focusManager = focusManager,
            contentAlignment = Alignment.CenterStart
        )

        // Personal Id
        Text(
            text = stringResource(id = R.string.card_id),
            color = Color(0xff5a5a5a),
            fontSize = 12.sp,
            style = MaterialTheme.typography.body,
        )
        SimpleTextField(
            value = cardId,
            onNewValue = {changeCardId(it.take(16)) },
            placeHolder = { },
            focusManager = focusManager,
            imeAction = ImeAction.Go,
            keyboardType = KeyboardType.Number,
            contentAlignment = Alignment.CenterStart
        )

        Row {
            // Expired Date Label
            Text(
                text = stringResource(id = R.string.expire_date),
                color = Color(0xff5a5a5a),
                style = MaterialTheme.typography.body,
                fontSize = 12.sp,
                modifier = Modifier.weight(1f)
            )

            // CW/CVC Label
            Text(
                text = stringResource(id = R.string.cvc),
                color = Color(0xff5a5a5a),
                style = MaterialTheme.typography.body,
                fontSize = 12.sp,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 4.dp)
            )
        }

        Row(modifier = Modifier.height(35.dp)) {
            // Month Text Field
            SimpleTextField(
                value = month,
                onNewValue = { changeMonth(it.take(2)) },
                placeHolder = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentHeight(),
                        contentAlignment = Alignment.Center
                    ) { Text(text = "MM", color = gray) } },
                focusManager = focusManager,
                imeAction = ImeAction.Go,
                keyboardType = KeyboardType.Number,
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Year Text Field
            SimpleTextField(
                value = year,
                onNewValue = { changeYear(it.take(2)) },
                placeHolder = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentHeight(),
                        contentAlignment = Alignment.Center
                    ) { Text(text = "YY", color = gray) }
                },
                focusManager = focusManager,
                imeAction = ImeAction.Go,
                keyboardType = KeyboardType.Number,
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .weight(1f)
            )

            // CVC Text Field
            SimpleTextField(
                value = cvc,
                onNewValue = { changeCvc(it.take(3)) },
                placeHolder = { },
                focusManager = focusManager,
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number,
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier.weight(2f)
            )
        }

        // Confirm Order Button
        Button(
            onClick = buy,
            colors = ButtonDefaults.buttonColors(containerColor = greenDark) ,
            modifier = Modifier
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

    Spacer(modifier = Modifier.height(100.dp))
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