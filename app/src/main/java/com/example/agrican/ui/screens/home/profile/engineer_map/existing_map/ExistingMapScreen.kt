package com.example.agrican.ui.screens.home.profile.engineer_map.existing_map

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.agrican.R
import com.example.agrican.domain.model.Crop
import com.example.agrican.ui.components.BackButton
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.screens.home.profile.engineer_map.MapScreen
import com.example.agrican.ui.screens.home.profile.engineer_map.add_problem.AddProblemDestination
import com.example.agrican.ui.screens.home.profile.engineer_map.add_progress.AddProgressDestination
import com.example.agrican.ui.theme.black
import com.example.agrican.ui.theme.body
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.textGray
import com.example.agrican.ui.theme.title
import com.example.agrican.ui.theme.white

object ExistingMapDestination: NavigationDestination {
    override val route: String = "existing_map"
    override val titleRes: Int = R.string.engineer_map
}

@Composable
fun ExistingMapScreen(
    navigateUp: () -> Unit,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    BackButton(
        navigateUp = navigateUp,
        modifier = modifier
    ) {
        ExistingMapScreenContent(
            crop = Crop(name = "الأرز", date = "30/05/2023"),
            openScreen = openScreen
        )
    }
}

@Composable
fun ExistingMapScreenContent(
    crop: Crop,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isDelAction by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
            .background(white)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                .background(greenLight)
        ) {

            Text(
                text = stringResource(id = R.string.engineer_map),
                color = white,
                modifier = Modifier.padding(top = 16.dp)
            )

            Divider(modifier = Modifier.padding(horizontal = 16.dp))

            CropSurface(crop = crop)

            AddProblemSurface(
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
                    text = stringResource(id = R.string.current_problem),
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
                        text = stringResource(id = R.string.del_problem),
                        fontSize = 12.sp,
                        color = white,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                }
            }
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxHeight()
        ) {
            MapScreen(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .fillMaxWidth()
                    .height(170.dp)
                    .background(black)
            )

            ExpandableItem(
                isDelAction = isDelAction,
                openScreen = openScreen
            )

            ExpandableItem(
                isDelAction = isDelAction,
                openScreen = openScreen
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
fun AddProblemSurface(
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shadowElevation = 8.dp,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .fillMaxWidth()
            .clickable { openScreen(AddProblemDestination.route) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {
            Column {
                // Add Problem Label
                Text(
                    text = stringResource(id = R.string.engineer_map_add_problem),
                    style = MaterialTheme.typography.title,
                    fontSize = 16.sp
                )

                Text(
                    text = stringResource(id = R.string.engineer_map_add_problem_description),
                    color = greenDark,
                    style = MaterialTheme.typography.body,
                    fontSize = 10.sp
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Add Problem Icon
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
    isDelAction: Boolean,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var visible by rememberSaveable { mutableStateOf(false) }

    Column(modifier = modifier) {
        Surface(
            shadowElevation = 16.dp,
            shape = RoundedCornerShape(16.dp),
            color = greenDark,
            modifier = Modifier.clickable { visible = !visible }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.height(IntrinsicSize.Max)
            ) {
                Text(
                    text = stringResource(id = R.string.add_progress),
                    color = white,
                    modifier = Modifier
                        .background(greenLight)
                        .padding(16.dp)
                        .clickable { openScreen(AddProgressDestination.route) }
                )

                Column {
                    Text(
                        text = "تبقع الأوراق",
                        color = white,
                        style = MaterialTheme.typography.body,
                        fontSize = 14.sp,
                    )

                    Text(
                        text = "المنطقة 2",
                        color = white,
                        style = MaterialTheme.typography.body,
                        fontSize = 7.sp,
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                if (isDelAction) {
                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(1.dp)
                            .clip(RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp))
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
                        onClick = { visible = !visible },
                        modifier = Modifier.height(32.dp)
                    ) {
                        Icon(
                            imageVector = if (visible) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                            contentDescription = null,
                            tint = white
                        )
                    }
                }
            }
        }

        AnimatedVisibility(visible = visible) {
            Problem()
        }
    }
}

@Composable
fun Problem(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(vertical = 8.dp, horizontal = 8.dp)
    ) {
        Row {
            Text(
                text = stringResource(id = R.string.problem_kind),
                color = textGray
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "تبقع الأوراق",
                color = textGray,
                modifier = Modifier.padding(end = 32.dp)
            )
        }

        Divider()

        Row {
            Text(
                text = stringResource(id = R.string.date),
                color = textGray
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "09-06-2023",
                color = textGray,
                modifier = Modifier.padding(end = 32.dp)
            )
        }

        Divider()

        Text(
            text = stringResource(id = R.string.description),
            color = textGray
        )
        Text(
            text = "تبقع بدرجة خفيفة فى المنطقة",
            color = textGray
        )

        Surface(
            border = BorderStroke(1.dp, greenDark),
            shape = RoundedCornerShape(16.dp)
        ) {
            Row(modifier = Modifier.height(IntrinsicSize.Max)) {
                Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)) {
                    Text(
                        text = "1-6-2023",
                        color = greenLight
                    )
                    Text(
                        text = "تم علاج المشكلة بشكل أولى",
                        color = Color(0xFF999898)
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxHeight()
                        .background(greenDark)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = white,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ExistingSMapScreenPreview() {
    ExistingMapScreenContent(
        crop = Crop(name = "الأرز", date = "30/05/2023"),
        openScreen = { }
    )
}

@Preview
@Composable
fun ProblemPreview() {
    Problem()
}