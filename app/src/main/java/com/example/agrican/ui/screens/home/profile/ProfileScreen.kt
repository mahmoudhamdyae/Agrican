package com.example.agrican.ui.screens.home.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.agrican.R
import com.example.agrican.domain.model.Crop
import com.example.agrican.domain.model.Farm
import com.example.agrican.domain.model.User
import com.example.agrican.domain.model.UserType
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.screens.home.profile.add_crop.AddCropDestination
import com.example.agrican.ui.screens.home.profile.add_farm.AddFarmDestination
import com.example.agrican.ui.screens.home.profile.cost.CostDestination
import com.example.agrican.ui.screens.home.profile.engineer_map.EngineerMapDestination
import com.example.agrican.ui.screens.home.profile.observe_crop.ObserveCropDestination
import com.example.agrican.ui.theme.body
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.spacing
import com.example.agrican.ui.theme.title
import com.example.agrican.ui.theme.white

object ProfileDestination: NavigationDestination {
    override val route: String = "profile"
    override val titleRes: Int = R.string.app_name
}

@Composable
fun ProfileScreen(
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ProfileScreenContent(
        uiState = uiState,
        openScreen = openScreen,
        modifier = modifier
    )
}

@Composable
fun ProfileScreenContent(
    uiState: ProfileUiState,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {

        item {
            UserHeaderAndItems(user = uiState.currentUser, openScreen = openScreen)
        }

        if (uiState.currentUser.userType != UserType.FARMER) {
            // Farms Label
            item {
                Text(
                    text = stringResource(id = R.string.farms_label),
                    style = MaterialTheme.typography.title,
                    fontSize = MaterialTheme.spacing.sp_16,
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
                )
            }

            item {
                Divider(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.large))
            }

            // Farms List
            item { FarmsList(farms = uiState.farms) }
        }

        // Crops Label
        item {
            Text(
                text = stringResource(id = R.string.my_crops),
                style = MaterialTheme.typography.title,
                fontSize = MaterialTheme.spacing.sp_16,
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
            )
        }
        item {
            Divider(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.large))
        }

        cropsList(crops = uiState.crops, openScreen = openScreen)
    }
}

@Composable
fun UserHeaderAndItems(
    user: User,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.height(IntrinsicSize.Min)) {
        Box(modifier = Modifier.fillMaxSize()) {
            Surface(
                shadowElevation = MaterialTheme.spacing.medium,
                shape = RoundedCornerShape(
                    bottomEnd = MaterialTheme.spacing.extraLarge,
                    bottomStart = MaterialTheme.spacing.extraLarge,
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = MaterialTheme.spacing.large)
            ) { }
            Column(modifier = Modifier.height(IntrinsicSize.Min)) {
                UserHeader(user = user, openScreen = openScreen)

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max)) {

                    // Add Farm Surface
                    if (user.userType != UserType.FARMER) {
                        AddItem(
                            title = R.string.add_farm,
                            description = R.string.add_farm_description,
                            onIconClick = { openScreen(AddFarmDestination.route) },
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                        )
                    }

                    // Add Crop Surface
                    AddItem(
                        title = R.string.add_crop,
                        description = R.string.add_crop_description,
                        onIconClick = { openScreen(AddCropDestination.route) },
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                    )
                }

                // Engineer Map Surface
                if (user.userType == UserType.ENGINEER) {
                    AddItem(
                        title = R.string.engineer_map,
                        description = R.string.engineer_map_description,
                        onIconClick = { openScreen(EngineerMapDestination.route) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun UserHeader(
    user: User,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shadowElevation = MaterialTheme.spacing.medium,
        modifier = modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.padding(MaterialTheme.spacing.medium)
        ) {

            // Profile Image
            Surface(
                shape = CircleShape,
                shadowElevation = MaterialTheme.spacing.small,
                modifier = Modifier.size(MaterialTheme.spacing.dp_100).padding(MaterialTheme.spacing.small)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.default_image),
                    contentDescription = null,
                    tint = gray,
                    modifier = Modifier
                        .padding(
                            start = MaterialTheme.spacing.small,
                            end = MaterialTheme.spacing.small,
                            bottom = MaterialTheme.spacing.small
                        )
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
                modifier = Modifier.padding(MaterialTheme.spacing.small)
            ) {
                // User Name
                Text(
                    text = user.userName,
                    style = MaterialTheme.typography.title,
                    fontSize = MaterialTheme.spacing.sp_15
                )

                Surface(
                    shape = RoundedCornerShape(MaterialTheme.spacing.medium),
                    color = greenDark
                ) {
                    // Account Type
                    Text(
                        text = stringResource(id = user.userType.title),
                        color = white,
                        style = MaterialTheme.typography.body,
                        fontSize = MaterialTheme.spacing.sp_14,
                        modifier = Modifier.padding(MaterialTheme.spacing.small)
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(modifier = Modifier.width(IntrinsicSize.Max)) {
                OutlinedButton(onClick = { /*TODO*/ },) {
                    Text(
                        text = stringResource(id = R.string.modify_data),
                        color = greenDark,
                        style = MaterialTheme.typography.body,
                        fontSize = MaterialTheme.spacing.sp_11
                    )
                }

                OutlinedButton(
                    onClick = { openScreen(CostDestination.route) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.cost),
                        color = greenDark,
                        style = MaterialTheme.typography.body,
                        fontSize = MaterialTheme.spacing.sp_11
                    )
                }
            }
        }
    }
}

@Composable
fun AddItem(
    title: Int,
    description: Int,
    onIconClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(MaterialTheme.spacing.medium),
        shadowElevation = MaterialTheme.spacing.medium,
        modifier = modifier.padding(MaterialTheme.spacing.medium)
    ) {
        Box(
            modifier = Modifier.padding(MaterialTheme.spacing.small)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)) {
                // Add Title
                Text(
                    text = stringResource(id = title),
                    style = MaterialTheme.typography.title,
                    fontSize = MaterialTheme.spacing.sp_16
                )
                // Add Description
                Text(
                    text = stringResource(id = description),
                    color = greenDark,
                    style = MaterialTheme.typography.body,
                    fontSize = MaterialTheme.spacing.sp_10,
                    modifier = Modifier.padding(end = MaterialTheme.spacing.large)
                )
            }

            IconButton(
                onClick = onIconClick,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .clip(CircleShape)
                    .background(greenDark)
                    .size(MaterialTheme.spacing.large)
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
fun FarmsList(
    farms: List<Farm>,
    modifier: Modifier = Modifier
) {
    LazyRow(modifier = modifier) {
        items(farms.size) {
            FarmsListItem(farm = farms[it])
        }
    }
}

@Composable
fun FarmsListItem(
    farm: Farm,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier
        .padding(MaterialTheme.spacing.small)
        .width(MaterialTheme.spacing.dp_50)) {
        Surface(
            shape = RoundedCornerShape(MaterialTheme.spacing.medium),
            shadowElevation = MaterialTheme.spacing.medium,
            modifier = Modifier.size(MaterialTheme.spacing.dp_50)
        ) {
        }
        Text(
            text = farm.name,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body,
            fontSize = MaterialTheme.spacing.sp_11
        )
    }
}

fun LazyListScope.cropsList(
    crops: List<Crop>,
    openScreen: (String) -> Unit
) {
    items(crops.size) {
        CropsListItem(crop = crops[it], onClick = { openScreen("${ObserveCropDestination.route}/$it") })
    }
}

@Composable
fun CropsListItem(
    crop: Crop,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = greenLight,
        shape = RoundedCornerShape(MaterialTheme.spacing.medium),
        modifier = modifier.padding(MaterialTheme.spacing.medium)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.small)
        ) {
            // Crop Name
            Text(
                text = crop.name,
                color = white,
                style = MaterialTheme.typography.title,
                fontSize = MaterialTheme.spacing.sp_16,
                modifier = Modifier.padding(MaterialTheme.spacing.small)
            )

            Spacer(modifier = Modifier.weight(1f))

            Column(horizontalAlignment = Alignment.End) {
                // Remove Button
                IconButton(onClick =  { /*TODO*/ } ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = white,
                        modifier = Modifier.size(MaterialTheme.spacing.medium)
                    )
                }

                // Observe Crop Button
                Button(
                    onClick = { onClick(crop.cropId) },
                    colors = ButtonDefaults.buttonColors(containerColor = greenDark),
                ) {
                    Text(
                        text = stringResource(id = R.string.observe_crop),
                        fontSize = MaterialTheme.spacing.sp_10,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Farm Users")
@Composable
fun ProfileScreenFarmPreview() {
    ProfileScreenContent(uiState = fakeUiState.copy(currentUser = fakeUiState.currentUser.copy(userType = UserType.FARM)), openScreen = { })
}

@Preview(showBackground = true, name = "Farmer Users")
@Composable
fun ProfileScreenFarmerPreview() {
    ProfileScreenContent(uiState = fakeUiState.copy(currentUser = fakeUiState.currentUser.copy(userType = UserType.FARMER)), openScreen = { })
}

@Preview(showBackground = true, name = "Engineer Users")
@Composable
fun ProfileScreenEngineerPreview() {
    ProfileScreenContent(uiState = fakeUiState, openScreen = { })
}

val fakeUiState = ProfileUiState(
    currentUser = User(
        userName = "مستخدم جديد",
        phoneNumber = "",
        email = "",
        userType = UserType.ENGINEER,
        image = "",
        userId = "1"
    ),
    farms = listOf(
        Farm(name = "المزرعة الأولى"),
        Farm(name = "المزرعة الثانية"),
        Farm(name = "المزرعة الثالثة"),
        Farm(name = "المزرعة الرابعة"),
        Farm(name = "المزرعة الخامسة"),
        Farm(name = "المزرعة السادسة"),
        Farm(name = "المزرعة السابعة"),
    ),
    crops = listOf(
        Crop(name = "الأرز"),
        Crop(name = "نبات الصبار"),
        Crop(name = "نبات الياسمين"),
        Crop(name = "الأرز"),
        Crop(name = "الأرز"),
        Crop(name = "الأرز"),
    )
)