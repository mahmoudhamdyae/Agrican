package com.example.agrican.ui.screens.home.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.agrican.R
import com.example.agrican.domain.model.User
import com.example.agrican.domain.model.UserType
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.screens.home.profile.add_crop.AddCropDestination
import com.example.agrican.ui.screens.home.profile.add_farm.AddFarmDestination
import com.example.agrican.ui.screens.home.profile.cost.CostDestination
import com.example.agrican.ui.screens.home.profile.engineer_map.EngineerMapDestination
import com.example.agrican.ui.screens.home.profile.observe_crop.ObserveCropDestination
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.spacing

object ProfileDestination: NavigationDestination {
    override val route: String = "profile"
    override val titleRes: Int = R.string.app_name
}

@Composable
fun ProfileScreen(
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val currentUser = User(
        userName = "مستخدم جديد",
        phoneNumber = "",
        email = "",
        userType = UserType.ENGINEER,
        image = "",
        userId = "1"
    )
    ProfileScreenContent(
        user = currentUser,
        openScreen = openScreen,
        modifier = modifier
    )
}

@Composable
fun ProfileScreenContent(
    user: User,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {

        item {
            UserHeaderAndItems(user = user, openScreen = openScreen)
        }

        if (user.userType != UserType.FARMER) {
            item {
                Text(
                    text = stringResource(id = R.string.farms_label),
                    color = greenDark,
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
                )
            }

            item {
                Divider(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.large))
            }
            item { FarmsList() }
        }

        item {
            Text(
                text = stringResource(id = R.string.my_crops),
                color = greenDark,
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
            )
        }
        item {
            Divider(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.large))
        }

        cropsList(openScreen = openScreen)
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

                Row(modifier = Modifier.fillMaxWidth()) {
                    if (user.userType != UserType.FARMER) {
                        AddItem(
                            title = R.string.add_farm,
                            description = R.string.add_farm_description,
                            onIconClick = { openScreen(AddFarmDestination.route) },
                            modifier = Modifier.weight(1f)
                        )
                    }

                    AddItem(
                        title = R.string.add_crop,
                        description = R.string.add_crop_description,
                        onIconClick = { openScreen(AddCropDestination.route) },
                        modifier = Modifier.weight(1f)
                    )
                }

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
            modifier = modifier.padding(MaterialTheme.spacing.large)
        ) {

            Surface(
                shape = CircleShape,
                shadowElevation = MaterialTheme.spacing.small
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_visibility_on),
                    contentDescription = null,
                    modifier = Modifier.size(75.dp)
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
                modifier = Modifier.padding(MaterialTheme.spacing.small)
            ) {
                Text(text = user.userName, color = greenLight)
                Surface(
                    shape = RoundedCornerShape(MaterialTheme.spacing.medium),
                    color = greenDark
                ) {
                    Text(
                        text = stringResource(id = user.userType.title),
                        color = Color.White,
                        modifier = Modifier.padding(MaterialTheme.spacing.small)
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(modifier = Modifier.width(IntrinsicSize.Max)) {
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.modify_data),
                        color = greenLight
                    )
                }

                OutlinedButton(
                    onClick = { openScreen(CostDestination.route) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.cost),
                        color = greenLight
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
                Text(
                    text = stringResource(id = title),
                    color = greenDark,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(id = description),
                    color = greenDark,
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
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun FarmsList(
    modifier: Modifier = Modifier
) {
    LazyRow(modifier = modifier) {
        items(10) {
            FarmsListItem(farmName = "المزرعة الأولى")
        }
    }
}

@Composable
fun FarmsListItem(
    farmName: String,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier
        .padding(MaterialTheme.spacing.small)
        .width(50.dp)) {
        Surface(
            shape = RoundedCornerShape(MaterialTheme.spacing.medium),
            shadowElevation = MaterialTheme.spacing.medium,
            modifier = Modifier.size(50.dp)
        ) {
        }
        Text(text = farmName, textAlign = TextAlign.Center)
    }
}

fun LazyListScope.cropsList(
    openScreen: (String) -> Unit
) {
    items(2) {
        CropsListItem(cropName = "نبات الصبار", onClick = { openScreen(ObserveCropDestination.route) })
    }
}

@Composable
fun CropsListItem(
    cropName: String,
    onClick: () -> Unit,
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
            Text(text = cropName, color = Color.White)

            Spacer(modifier = Modifier.weight(1f))

            Column(horizontalAlignment = Alignment.End) {
                IconButton(onClick = onClick) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(MaterialTheme.spacing.medium)
                    )
                }

                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(containerColor = greenDark),
                ) {
                    Text(text = stringResource(id = R.string.observe_crop))
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Farm Users")
@Composable
fun ProfileScreenFarmPreview() {
    ProfileScreenContent(user = fakeUser.copy(userType = UserType.FARM), openScreen = { })
}

@Preview(showBackground = true, name = "Farmer Users")
@Composable
fun ProfileScreenFarmerPreview() {
    ProfileScreenContent(user = fakeUser.copy(userType = UserType.FARMER), openScreen = { })
}

@Preview(showBackground = true, showSystemUi = true, name = "Engineer Users")
@Composable
fun ProfileScreenEngineerPreview() {
    ProfileScreenContent(user = fakeUser, openScreen = { })
}

val fakeUser = User(
    userName = "مستخدم جديد",
    phoneNumber = "",
    email = "",
    userType = UserType.ENGINEER,
    image = "",
    userId = "1"
)