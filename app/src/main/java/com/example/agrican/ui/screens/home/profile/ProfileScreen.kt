package com.example.agrican.ui.screens.home.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.agrican.R
import com.example.agrican.domain.model.Crop
import com.example.agrican.domain.model.Farm
import com.example.agrican.domain.model.User
import com.example.agrican.domain.model.UserType
import com.example.agrican.ui.components.FarmsList
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.screens.home.profile.add_crop.AddCropDestination
import com.example.agrican.ui.screens.home.profile.add_farm.AddFarmDestination
import com.example.agrican.ui.screens.home.profile.cost.CostDestination
import com.example.agrican.ui.screens.home.profile.engineer_map.EngineerMapDestination
import com.example.agrican.ui.screens.home.profile.observe_crop.ObserveCropDestination
import com.example.agrican.ui.theme.body
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.iconGray
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
    var selectedFarm: Farm? by remember { mutableStateOf(null) }

    var delFarm by remember { mutableStateOf(false) }
    var delCrop by remember { mutableStateOf(false) }

    LazyColumn(modifier = modifier) {

        item {
            UserHeaderAndItems(user = uiState.currentUser, openScreen = openScreen)
        }

        if (uiState.currentUser.userType != UserType.FARMER) {
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Farms Label
                    Text(
                        text = stringResource(id = R.string.farms_label),
                        style = MaterialTheme.typography.title,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(horizontal = 18.dp)
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    // Delete Farm Button
                    DeleteButton(
                        text = R.string.del_farm,
                        delAction = {
                            delFarm = true
                            selectedFarm = null
                                    },
                        modifier = Modifier.padding(horizontal = 18.dp)
                    )
                }
            }

            // Farms List
            item {
                FarmsList(
                    farms = uiState.farms,
                    delAction = delFarm,
                    onDelFarmClick = {
                        delFarm = !delFarm
                        selectedFarm = null
                                     },
                    onFarmClick = { selectedFarm = it },
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }

        if (selectedFarm != null) {
            item {
                Divider(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp, vertical = 12.dp))
            }

            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Crops Label
                    Text(
                        text = stringResource(id = R.string.farms_crops),
                        style = MaterialTheme.typography.title,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(horizontal = 18.dp)
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    // Delete Crop Button
                    DeleteButton(
                        text = R.string.del_crop,
                        delAction = { delCrop = true },
                        modifier = Modifier.padding(horizontal = 18.dp)
                    )
                }
            }

            cropsList(
                crops = uiState.crops,
                delAction = delCrop,
                onDelCropClick = { delCrop = !delCrop },
                openScreen = openScreen
            )
        }

        // Space fo Bottom Navigation Bar
        item {
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}

@Composable
fun DeleteButton(
    text: Int,
    delAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = greenLight,
        modifier = modifier.clickable { delAction() }
    ) {
        Text(
            text = stringResource(id = text),
            color = white,
            fontSize = 10.sp,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 12.dp)
        )
    }
}

@Composable
fun UserHeaderAndItems(
    user: User,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.height(IntrinsicSize.Min)) {
        Surface(
            shape = RoundedCornerShape(
                bottomEnd = 13.dp,
                bottomStart = 13.dp,
            ),
            color = greenLight,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp)
        ) {

            Column(modifier = Modifier.padding(bottom = 16.dp)) {
                UserHeader(user = user, openScreen = openScreen)

                Divider(modifier = Modifier
                    .height(1.dp)
                    .padding(horizontal = 18.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Max)
                        .padding(top = 18.dp, start = 18.dp, end = 18.dp)
                ) {

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
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .padding(top = 12.dp)
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
    Box(modifier = modifier
        .fillMaxWidth()
        .height(IntrinsicSize.Min)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .background(greenLight)
                .padding(16.dp)
        ) {

            // Profile Image
            Surface(
                shape = CircleShape,
                shadowElevation = 8.dp
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = null,
                    tint = iconGray,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .padding(12.dp)
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(12.dp)
            ) {
                // Account Type
                Text(
                    text = stringResource(id = user.userType.title),
                    color = white,
                    style = MaterialTheme.typography.body,
                    fontSize = 14.sp
                )

                // User Name
                Text(
                    text = user.userName,
                    style = MaterialTheme.typography.title,
                    color = white,
                    fontSize = 15.sp
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Modify Data Button
            Column(horizontalAlignment = Alignment.End) {
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(containerColor = greenDark)
                ) {
                    Text(
                        text = stringResource(id = R.string.modify_data),
                        color = white,
                        style = MaterialTheme.typography.body,
                        fontSize = 11.sp
                    )
                }

                // Cost Button
                Button(
                    onClick = { openScreen(CostDestination.route) },
                    colors = ButtonDefaults.buttonColors(containerColor = greenDark)
                ) {
                    Text(
                        text = stringResource(id = R.string.cost),
                        color = white,
                        style = MaterialTheme.typography.body,
                        fontSize = 11.sp
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
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 8.dp,
        modifier = modifier.clickable { onIconClick() }
    ) {
        Box(
            modifier = Modifier.padding(10.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                // Add Title
                Text(
                    text = stringResource(id = title),
                    style = MaterialTheme.typography.title,
                    fontSize = 16.sp
                )
                // Add Description
                Text(
                    text = stringResource(id = description),
                    color = greenDark,
                    style = MaterialTheme.typography.body,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(end = 32.dp)
                )
            }

            IconButton(
                onClick = onIconClick,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(32.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = greenLight
                )
            }
        }
    }
}

fun LazyListScope.cropsList(
    crops: List<Crop>,
    delAction: Boolean,
    onDelCropClick: (String) -> Unit,
    openScreen: (String) -> Unit
) {
    items(items = crops, key = { it.cropId }) {
        CropsListItem(
            crop = it,
            onObserveClick = { openScreen(ObserveCropDestination.route) },
            onDelCropClick = onDelCropClick,
            delAction = delAction
        )
    }
}

@Composable
fun CropsListItem(
    crop: Crop,
    delAction: Boolean,
    onObserveClick: (String) -> Unit,
    onDelCropClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = greenLight,
        shadowElevation = 16.dp,
        shape = RoundedCornerShape(13.dp),
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(45.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Observe Crop Button
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .clickable { onObserveClick(crop.cropId) }
                    .fillMaxHeight()
                    .background(greenDark)
            ) {
                Text(
                    text = stringResource(id = R.string.observe_crop),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = white,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }

            // Crop Name
            Text(
                text = crop.name,
                color = white,
                style = MaterialTheme.typography.title,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 12.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            // Remove Button
            if (delAction) {
                IconButton(
                    onClick =  { onDelCropClick(crop.cropId) },
                    modifier = Modifier
                        .height(43.dp)
                        .padding(end = 1.dp)
                        .clip(RoundedCornerShape(topEnd = 13.dp, bottomEnd = 13.dp))
                        .background(white)
                        .fillMaxHeight()
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = greenDark,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Engineer Users")
@Composable
fun ProfileScreenEngineerPreview() {
    ProfileScreenContent(uiState = fakeUiState, openScreen = { })
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

@Preview
@Composable
fun CropItemPreview() {
    CropsListItem(
        crop = Crop(name = "نبات الصبار"),
        delAction = true,
        onObserveClick = { },
        onDelCropClick = { }
    )
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