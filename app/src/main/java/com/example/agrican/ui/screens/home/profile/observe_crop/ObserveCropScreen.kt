package com.example.agrican.ui.screens.home.profile.observe_crop

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.agrican.R
import com.example.agrican.domain.model.Crop
import com.example.agrican.ui.components.Days
import com.example.agrican.ui.components.ProfileHeader
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.screens.home.profile.add_task.AddTaskDestination
import com.example.agrican.ui.theme.body
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.spacing
import com.example.agrican.ui.theme.title
import com.example.agrican.ui.theme.white

object ObserveCropDestination: NavigationDestination {
    override val route: String = "observe_crop"
    override val titleRes: Int = R.string.observe_crop
    const val cropIdArg = "crop_id"
    val routeWithArgs = "$route/{$cropIdArg}"
    val arguments = listOf(
        navArgument(cropIdArg) { type = NavType.StringType },
    )
}

@Composable
fun ObserveCropScreen(
    navigateUp: () -> Unit,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ObserveCropViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ProfileHeader(navigateUp = navigateUp, headerText = ObserveCropDestination.titleRes) {
        ObserveCropScreenContent(crop = uiState.crop, openScreen = openScreen, modifier = modifier)
    }
}

@Composable
fun ObserveCropScreenContent(
    crop: Crop,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
        modifier = modifier.padding(MaterialTheme.spacing.medium)
    ) {
        CropSurface(crop = crop)

        AddTakSurface(openScreen = openScreen)

        Text(
            text = stringResource(id = R.string.tasks),
            style = MaterialTheme.typography.title,
            fontSize = MaterialTheme.spacing.sp_16
        )
        Divider()

        ExpandableItem(
            label = R.string.watering_crop,
            selectedDays = listOf(),
            onDayAdded = { /*TODO*/ }
        )

        ExpandableItem(
            label = R.string.composting,
            selectedDays = listOf(),
            onDayAdded = { /*TODO*/ }
        )

    }
}

@Composable
fun CropSurface(
    crop: Crop,
    modifier: Modifier = Modifier
) {
    Surface(
        shadowElevation = MaterialTheme.spacing.small,
        shape = RoundedCornerShape(MaterialTheme.spacing.medium),
        color = greenLight,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(MaterialTheme.spacing.small)) {
            Text(
                text = crop.name,
                color = white,
                style = MaterialTheme.typography.title,
                fontSize = MaterialTheme.spacing.sp_16
            )

            Row(modifier = Modifier.align(Alignment.End)) {
                Text(
                    text = stringResource(id = R.string.agri_history),
                    color = white,
                    style = MaterialTheme.typography.body,
                    fontSize = MaterialTheme.spacing.sp_14,
                    modifier = Modifier.padding(end = MaterialTheme.spacing.small)
                )
                Text(
                    text = crop.date,
                    color = white,
                    style = MaterialTheme.typography.body,
                    fontSize = MaterialTheme.spacing.sp_14
                )
            }
        }
    }
}

@Composable
fun AddTakSurface(
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shadowElevation = MaterialTheme.spacing.small,
        shape = RoundedCornerShape(MaterialTheme.spacing.medium),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(MaterialTheme.spacing.small)) {
            // Add Task Label
            Text(
                text = stringResource(id = R.string.add_task),
                style = MaterialTheme.typography.title,
                fontSize = MaterialTheme.spacing.sp_16
            )

            Text(
                text = stringResource(id = R.string.add_task_description),
                color = greenDark,
                style = MaterialTheme.typography.body,
                fontSize = MaterialTheme.spacing.sp_10
            )

            IconButton(
                onClick = { openScreen(AddTaskDestination.route) },
                modifier = Modifier
                    .clip(CircleShape)
                    .background(greenDark)
                    .align(Alignment.End)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = white
                )
            }
        }
    }
}

@Composable
fun ExpandableItem(
    label: Int,
    selectedDays: List<Int>,
    onDayAdded: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var visible by rememberSaveable { mutableStateOf(false) }

    Column(modifier = modifier) {
        Surface(
            shadowElevation = MaterialTheme.spacing.small,
            shape = RoundedCornerShape(MaterialTheme.spacing.medium),
            color = greenDark
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
                modifier = Modifier.padding(MaterialTheme.spacing.medium)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_visibility_on),
                    contentDescription = null,
                    modifier = Modifier.clip(CircleShape)
                )
                Text(
                    text = stringResource(id = label),
                    color = white,
                    style = MaterialTheme.typography.body,
                    fontSize = MaterialTheme.spacing.sp_14
                )

                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    onClick = { visible = !visible },
                    modifier = Modifier.height(MaterialTheme.spacing.large)
                ) {
                    Icon(
                        imageVector = if (visible) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = null,
                        tint = white
                    )
                }
            }
        }

        AnimatedVisibility(visible = visible) {
            Days(selectedDays = selectedDays, onDayClicked = onDayAdded)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ObserveCropScreenPreview() {
    ObserveCropScreenContent(crop = Crop(name = "الأرز"), openScreen = { })
}