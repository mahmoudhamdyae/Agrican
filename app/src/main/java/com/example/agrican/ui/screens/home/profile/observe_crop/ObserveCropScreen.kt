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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.agrican.R
import com.example.agrican.ui.components.Days
import com.example.agrican.ui.components.ProfileHeader
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.screens.home.profile.add_farm.AddFarmDestination
import com.example.agrican.ui.screens.home.profile.add_task.AddTaskDestination
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.spacing

object ObserveCropDestination: NavigationDestination {
    override val route: String = "observe_crop"
    override val titleRes: Int = R.string.observe_crop
}

@Composable
fun ObserveCropScreen(
    navigateUp: () -> Unit,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    ProfileHeader(navigateUp = navigateUp, headerText = AddFarmDestination.titleRes) {
        ObserveCropScreenContent(openScreen = openScreen, modifier = modifier)
    }
}

@Composable
fun ObserveCropScreenContent(
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
        modifier = modifier.padding(MaterialTheme.spacing.medium)
    ) {
        Surface(
            shadowElevation = MaterialTheme.spacing.small,
            shape = RoundedCornerShape(MaterialTheme.spacing.medium),
            color = greenLight,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(MaterialTheme.spacing.small)) {
                Text(text = "نبات الصبار", color = Color.White)

                Row(modifier = Modifier.align(Alignment.End)) {
                    Text(
                        text = stringResource(id = R.string.agri_history),
                        color = Color.White,
                        modifier = Modifier.padding(end = MaterialTheme.spacing.small)
                    )
                    Text(text = "30/05/2023", color = Color.White)
                }
            }
        }
        
        Surface(
            shadowElevation = MaterialTheme.spacing.small,
            shape = RoundedCornerShape(MaterialTheme.spacing.medium),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(MaterialTheme.spacing.small)) {
                Text(
                    text = stringResource(id = R.string.add_task),
                    color = greenDark,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = stringResource(id = R.string.add_task_description),
                    color = greenDark,
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
                        tint = Color.White
                    )
                }
            }
        }

        Text(text = stringResource(id = R.string.tasks), color = greenDark)
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
                Text(text = stringResource(id = label), color = Color.White)

                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    onClick = { visible = !visible },
                    modifier = Modifier.height(MaterialTheme.spacing.large)
                ) {
                    Icon(
                        imageVector = if (visible) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = null,
                        tint = Color.White
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
    ObserveCropScreenContent(openScreen = { })
}