package com.theflankers.agrican.ui.screens.home.profile.observe_crop

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.theflankers.agrican.R
import com.theflankers.agrican.domain.model.Crop
import com.theflankers.agrican.ui.components.BackButton
import com.theflankers.agrican.ui.components.Days
import com.theflankers.agrican.ui.navigation.NavigationDestination
import com.theflankers.agrican.ui.screens.home.profile.add_task.AddTaskDestination
import com.theflankers.agrican.ui.theme.body
import com.theflankers.agrican.ui.theme.greenDark
import com.theflankers.agrican.ui.theme.greenLight
import com.theflankers.agrican.ui.theme.title
import com.theflankers.agrican.ui.theme.white
import java.time.LocalDate

object ObserveCropDestination: NavigationDestination {
    override val route: String = "observe_crop"
    override val titleRes: Int = R.string.observe_crop
}

@Composable
fun ObserveCropScreen(
    navigateUp: () -> Unit,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ObserveCropViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BackButton(
        navigateUp = navigateUp,
        modifier = modifier
    ) {
        ObserveCropScreenContent(
            crop = uiState.crop,
            openScreen = openScreen
        )
    }
}

@Composable
fun ObserveCropScreenContent(
    crop: Crop,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    var isDelAction by remember { mutableStateOf(false) }
    
    Column(
        modifier = modifier
            .fillMaxHeight()
            .background(white)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .clip(RoundedCornerShape(bottomStart = 13.dp, bottomEnd = 13.dp))
                .background(greenLight)
        ) {

            Text(
                text = stringResource(id = R.string.observe_crop),
                color = white,
                modifier = Modifier.padding(top = 16.dp)
            )

            Divider(modifier = Modifier.padding(horizontal = 16.dp))

            CropSurface(crop = crop)

            AddTakSurface(
                openScreen = openScreen,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(greenDark)
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.tasks),
                    style = MaterialTheme.typography.title,
                    fontSize = 16.sp,
                    color = white,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Spacer(modifier = Modifier.weight(1f))

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = greenLight,
                    modifier = Modifier.clickable { isDelAction = !isDelAction }
                ) {
                    Text(
                        text = stringResource(id = R.string.del_task),
                        fontSize = 12.sp,
                        color = white,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                }
            }
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            ExpandableItem(
                label = R.string.watering_crop,
                selectedDays = listOf(),
                onDayAdded = { /*TODO*/ },
                isDelAction = isDelAction
            )

            ExpandableItem(
                label = R.string.composting,
                selectedDays = listOf(),
                onDayAdded = { /*TODO*/ },
                isDelAction = isDelAction
            )

            ExpandableItem(
                label = R.string.pick,
                selectedDays = listOf(),
                onDayAdded = { /*TODO*/ },
                isDelAction = isDelAction
            )
        }

        Spacer(modifier = Modifier.height(90.dp))
    }
}

@Composable
fun CropSurface(
    crop: Crop,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Text(
            text = crop.name,
            color = white,
            style = MaterialTheme.typography.title,
            fontSize = 16.sp
        )

        Row {
            Text(
                text = stringResource(id = R.string.agri_history),
                color = white,
                style = MaterialTheme.typography.body,
                fontSize = 14.sp,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = crop.date,
                color = white,
                style = MaterialTheme.typography.body,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun AddTakSurface(
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shadowElevation = 8.dp,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .fillMaxWidth()
            .clickable { openScreen(AddTaskDestination.route) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {
            Column {
                // Add Task Label
                Text(
                    text = stringResource(id = R.string.add_task),
                    style = MaterialTheme.typography.title,
                    fontSize = 16.sp
                )

                Text(
                    text = stringResource(id = R.string.add_task_description),
                    color = greenDark,
                    style = MaterialTheme.typography.body,
                    fontSize = 10.sp
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Add Task Icon
            Icon(
                painter = painterResource(id = R.drawable.add_problem_or_task),
                contentDescription = null,
                tint = greenDark,
                modifier = Modifier.align(Alignment.Bottom).size(24.dp)
            )
        }
    }
}

@Composable
fun ExpandableItem(
    label: Int,
    selectedDays: List<LocalDate>,
    onDayAdded: (LocalDate) -> Unit,
    isDelAction: Boolean,
    modifier: Modifier = Modifier
) {
    var isVisible by remember{ mutableStateOf(false) }

    Column(modifier = modifier) {
        Surface(
            shadowElevation = 16.dp,
            shape = RoundedCornerShape(13.dp),
            color = greenLight,
            modifier = Modifier
                .clickable { isVisible = !isVisible }
                .height(45.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                IcoView(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .padding(vertical = 4.dp)
                )

                Text(
                    text = stringResource(id = label),
                    color = white,
                    style = MaterialTheme.typography.body,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.weight(1f))

                if (isDelAction) {
                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .padding(1.dp)
                            .clip(RoundedCornerShape(topEnd = 13.dp, bottomEnd = 13.dp))
                            .height(43.dp)
                            .background(white)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                            tint = greenDark,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                } else {
                    IconButton(
                        onClick = { isVisible = !isVisible },
                        modifier = Modifier.height(32.dp)
                    ) {
                        Icon(
                            imageVector = if (isVisible) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                            contentDescription = null,
                            tint = white
                        )
                    }
                }
            }
        }

        AnimatedVisibility(visible = isVisible) {
            Days(selectedDays = selectedDays, onDayClicked = onDayAdded)
        }
    }
}

@Composable
fun IcoView(
    modifier: Modifier = Modifier
) {
    Text(
        text = "ico",
        color = white,
        fontSize = 15.sp,
        modifier = modifier.padding(8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun ObserveCropScreenPreview() {
    ObserveCropScreenContent(crop = Crop(name = "الأرز", date = "30/05/2023"), openScreen = { })
}