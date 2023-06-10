package com.example.agrican.ui.screens.home.profile.add_crop

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.agrican.R
import com.example.agrican.ui.navigation.NavigationDestination

object AddCropDestination: NavigationDestination {
    override val route: String = "add_crop"
    override val titleRes: Int = R.string.add_crop
}

@Composable
fun AddCropScreen(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
}

@Preview(showBackground = true)
@Composable
fun AddCropScreenPreview() {
    AddCropScreen(navigateUp = { })
}