package com.example.agrican.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.agrican.R
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.spacing

@Composable
fun ProfileHeader(
    navigateUp: () -> Unit,
    headerText: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {},
) {
    BackButton(navigateUp = navigateUp, modifier = modifier) {
        Column {
            Text(
                text = stringResource(id = headerText),
                color = greenLight,
                modifier = Modifier.padding(MaterialTheme.spacing.medium)
            )

            Divider(modifier = Modifier.padding(MaterialTheme.spacing.medium))

            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileHeaderPreview() {
    ProfileHeader(navigateUp = { }, headerText = R.string.add_farm)
}