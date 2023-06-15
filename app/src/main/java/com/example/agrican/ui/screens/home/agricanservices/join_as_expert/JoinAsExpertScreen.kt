package com.example.agrican.ui.screens.home.agricanservices.join_as_expert

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.agrican.R
import com.example.agrican.common.ext.encodeImage
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.spacing

object JoinAsExpertDestination: NavigationDestination {
    override val route: String = "join_as_expert"
    override val titleRes: Int = R.string.join_as_expert
}

@OptIn(ExperimentalMaterial3Api::class)
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

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(
                start = MaterialTheme.spacing.medium,
                end = MaterialTheme.spacing.medium,
                bottom = MaterialTheme.spacing.large
            ),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
    ) {
        // Header
        Surface(
            shadowElevation = MaterialTheme.spacing.medium,
            shape = RoundedCornerShape(
                bottomStart = MaterialTheme.spacing.medium,
                bottomEnd = MaterialTheme.spacing.medium
            ),
            color = greenDark
        ) {
            Box {
                Text(
                    text = stringResource(id = R.string.join_as_expert_label),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = Modifier.padding(
                        horizontal = MaterialTheme.spacing.medium,
                        vertical = MaterialTheme.spacing.extraLarge
                    )
                )

                Box(
                    modifier = Modifier
                        .padding(MaterialTheme.spacing.medium)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.background)
                        .align(Alignment.BottomStart)
                        .size(MaterialTheme.spacing.extraSmall)
                )

                Box(
                    modifier = Modifier
                        .padding(MaterialTheme.spacing.medium)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.background)
                        .align(Alignment.BottomEnd)
                        .size(MaterialTheme.spacing.extraSmall)
                )
            }
        }

        // Full Name Text Field
        Text(text = stringResource(id = R.string.full_name))
        OutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            shape = RoundedCornerShape(MaterialTheme.spacing.medium),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = greenLight,
                textColor = greenLight
            ),
            modifier = Modifier.fillMaxWidth()
        )

        // Email TextField
        Text(text = stringResource(id = R.string.e_mail))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            shape = RoundedCornerShape(MaterialTheme.spacing.medium),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = greenLight,
                textColor = greenLight
            ),
            modifier = Modifier.fillMaxWidth()
        )

        // Phone Number Text Field
        Text(text = stringResource(id = R.string.phone_number))
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            ),
            shape = RoundedCornerShape(MaterialTheme.spacing.medium),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = greenLight,
                textColor = greenLight
            ),
            modifier = Modifier.fillMaxWidth()
        )

        // Image to Upload
        Text(text = stringResource(id = R.string.image_of_certificate))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Add Image Button
            OutlinedButton(
                onClick = {
                    imagePicker.launch(
                        PickVisualMediaRequest(
                            mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly)
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
                        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.take_picture),
                        contentDescription = null
                    )
                }
            }

            // Send Information
            Button(
                onClick = { viewModel.send(fullName, email, phoneNumber, image, navigateUp) },
                colors = ButtonDefaults.buttonColors(containerColor = greenDark),
                modifier = Modifier.padding(MaterialTheme.spacing.large)
            ) {
                Text(
                    text = stringResource(id = R.string.send),
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.large)
                )
            }
        }
    }
}