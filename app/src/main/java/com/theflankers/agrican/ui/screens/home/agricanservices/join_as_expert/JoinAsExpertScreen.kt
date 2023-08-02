package com.theflankers.agrican.ui.screens.home.agricanservices.join_as_expert

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.theflankers.agrican.R
import com.theflankers.agrican.common.ext.encodeImage
import com.theflankers.agrican.ui.components.BackButtonTopBar
import com.theflankers.agrican.ui.navigation.NavigationDestination
import com.theflankers.agrican.ui.theme.body
import com.theflankers.agrican.ui.theme.greenDark
import com.theflankers.agrican.ui.theme.greenLight

object JoinAsExpertDestination : NavigationDestination {
    override val route: String = "join_as_expert"
    override val titleRes: Int = R.string.join_as_expert_title
}

@Composable
fun JoinAsExpertScreen(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: JoinAsExpertViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    var fullName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var phoneNumber by rememberSaveable { mutableStateOf("") }
    var image: String? by rememberSaveable { mutableStateOf(null) }

    val imagePicker =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            image = uri.encodeImage(context)
        }

    val focusManager = LocalFocusManager.current

    BackButtonTopBar(
        title = JoinAsExpertDestination.titleRes,
        navigateUp = navigateUp,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 32.dp
                )
                .padding(bottom = 60.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Header
            Text(
                text = stringResource(id = R.string.join_as_expert_label),
                textAlign = TextAlign.Center,
                color = greenLight,
                style = MaterialTheme.typography.body,
                fontSize = 16.sp,
                modifier = Modifier.padding(
                    horizontal = 8.dp,
                    vertical = 16.dp
                )
            )

            // Full Name Text Field
            Text(
                text = stringResource(id = R.string.full_name),
                style = MaterialTheme.typography.body,
                fontSize = 14.sp
            )
            BasicTextField(
                value = fullName,
                onValueChange = { fullName = it },
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(10.dp)
                    ) {
                        innerTextField()
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .border(
                        border = BorderStroke(1.dp, greenLight),
                        shape = RoundedCornerShape(16.dp)
                    )
            )

            // Email TextField
            Text(
                text = stringResource(id = R.string.e_mail),
                style = MaterialTheme.typography.body,
                fontSize = 14.sp
            )
            BasicTextField(
                value = email,
                onValueChange = { email = it },
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(10.dp)
                    ) {
                        innerTextField()
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .border(
                        border = BorderStroke(1.dp, greenLight),
                        shape = RoundedCornerShape(16.dp)
                    )
            )

            // Phone Number Text Field
            Text(
                text = stringResource(id = R.string.phone_number),
                style = MaterialTheme.typography.body,
                fontSize = 14.sp
            )
            BasicTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(10.dp)
                    ) {
                        innerTextField()
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .border(
                        border = BorderStroke(1.dp, greenLight),
                        shape = RoundedCornerShape(16.dp)
                    )
            )

            // Image to Upload
            Text(
                text = stringResource(id = R.string.image_of_certificate),
                style = MaterialTheme.typography.body,
                fontSize = 14.sp
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Add Image Button
                OutlinedButton(
                    onClick = {
                        imagePicker.launch(
                            PickVisualMediaRequest(
                                mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                            )
                        )
                    },
                    border = BorderStroke(1.dp, greenLight)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.add_image),
                            color = greenLight,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )

                        Icon(
                            painter = painterResource(id = R.drawable.take_picture),
                            contentDescription = null,
                        )
                    }
                }

                // Send Information
                Button(
                    onClick = {
                        viewModel.send(
                            fullName,
                            email,
                            phoneNumber,
                            image,
                            navigateUp
                        )
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = greenDark),
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier
                        .padding(32.dp)
                        .height(38.dp)
                        .width(225.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.send),
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}