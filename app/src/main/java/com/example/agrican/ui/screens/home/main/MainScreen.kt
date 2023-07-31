package com.example.agrican.ui.screens.home.main

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Looper
import android.provider.Settings
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.agrican.R
import com.example.agrican.domain.model.News
import com.example.agrican.domain.model.weather.Weather
import com.example.agrican.domain.model.weather.WeatherInfo
import com.example.agrican.domain.model.weather.toJson
import com.example.agrican.ui.components.EmptyImage
import com.example.agrican.ui.components.TopBar
import com.example.agrican.ui.components.shimmerEffect
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.screens.home.main.ask_expert.AskExpertDestination
import com.example.agrican.ui.screens.home.main.fertilizers_calculator.FertilizersCalculatorDestination
import com.example.agrican.ui.screens.home.main.problem_images.ProblemImagesDestination
import com.example.agrican.ui.screens.home.main.weather.WeatherDestination
import com.example.agrican.ui.theme.body
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.title
import com.example.agrican.ui.theme.white
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.launch

object MainDestination: NavigationDestination {
    override val route: String = "main"
    override val titleRes: Int = R.string.default_age_title
}

@Composable
fun MainScreen(
    openScreen: (String) -> Unit,
    navigateFromSignOut: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val getWeather by remember { mutableStateOf(true) }

    val context = LocalContext.current
    val activityResultLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            // Handle Permission
            if (isGranted) {
                // Permission is granted
                getLastLocation(
                    context = context,
                    getWeather = viewModel::getWeather
                )
            } else {
                // Permission is denied
                Toast.makeText(context, R.string.permission_denied_toast, Toast.LENGTH_SHORT).show()
            }
        }

    LaunchedEffect(key1 = getWeather) {
        grantPermission(activityResultLauncher)
    }

    val lifecycleEvent = rememberLifecycleEvent()
    LaunchedEffect(lifecycleEvent) {
        if (lifecycleEvent == Lifecycle.Event.ON_RESUME) {
            if (isPermissionGranted(context) && isGpsEnabled(context)) {
                getLastLocation(context = context, getWeather = viewModel::getWeather)
            }
        }
    }

    TopBar(
        title = {
            Image(
                painter = painterResource(id = R.drawable.agrican_logo),
                contentDescription = null
            )
        },
        openScreen = openScreen,
        navigateFromSignOut = navigateFromSignOut
    ) {
        MainScreenContent(
            uiState = uiState,
            openScreen = openScreen,
            modifier = modifier
        )
    }
}

@Composable
fun rememberLifecycleEvent(lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current): Lifecycle.Event {
    var state by remember { mutableStateOf(Lifecycle.Event.ON_ANY) }
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            state = event
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    return state
}

@Composable
fun MainScreenContent(
    uiState: MainUiState,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    var isNews by rememberSaveable { mutableStateOf(true) }

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = 80.dp)
    ) {
        WeatherBoxLoading(
            uiState = uiState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
                .clickable {
                    if (!uiState.isWeatherLoading) {
                        val currentWeatherData = uiState.weather?.currentWeatherData

                        val weather = Weather(
                            degree = currentWeatherData!!.temperatureCelsius,
                            wind = currentWeatherData.windSpeed,
                            windGusts = currentWeatherData.windGusts,
                            windDirection = currentWeatherData.windDirection,
                            weatherDesc = currentWeatherData.weatherType.weatherDesc,
                            airQuality = uiState.weather.airQuality,
                            iconRes = currentWeatherData.weatherType.iconRes
                        )

                        openScreen("${WeatherDestination.route}/${weather.toJson()}")
                    }
                }
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            // Latest News Button
            Button(
                onClick = { isNews = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isNews) greenDark else white
                ),
                border = BorderStroke(1.dp, if (isNews) greenDark else gray),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(id = R.string.last_news),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isNews) white else greenDark
                )
            }

            // Latest Offers Button
            Button(
                onClick = { isNews = false },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isNews) white else greenDark
                ),
                border = BorderStroke(1.dp, if (isNews) gray else greenDark),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(id = R.string.last_offers),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isNews) greenDark else white,
                )
            }
        }

        LatestNewsList(
            uiState = uiState,
            isNews = isNews,
            modifier = Modifier.padding(top = 12.dp)
        )

        // Problem Images Card
        BottomCard(
            title = R.string.problem_images,
            description = R.string.problem_images_description,
            icon = R.drawable.problem_images,
            onItemClick = { openScreen(ProblemImagesDestination.route) },
            body = { ProblemImagesRow() },
            modifier = Modifier
                .padding(horizontal = 18.dp, vertical = 8.dp)
                .fillMaxWidth()

        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(horizontal = 18.dp, vertical = 8.dp)
        ) {
            // Fertilizers Calculator Card
            BottomCard(
                title = R.string.fertilizers_calculator,
                description = R.string.fertilizers_calculator_description,
                icon = R.drawable.fertilizers_calculator,
                onItemClick = { openScreen(FertilizersCalculatorDestination.route) },
                modifier = Modifier.weight(1f)
            )

            // Ask An Expert Card
            BottomCard(
                title = R.string.ask_expert,
                description = R.string.ask_expert_description,
                icon = R.drawable.ask_an_expert,
                onItemClick = { openScreen(AskExpertDestination.route) },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun WeatherBoxLoading(
    uiState: MainUiState,
    modifier: Modifier = Modifier
) {
    if (uiState.isWeatherLoading) {
        Surface(
            border = BorderStroke(1.dp, gray),
            shape = RoundedCornerShape(16.dp),
            modifier = modifier
        ) {
            Box(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
                Column {

                    Text(
                        text = "الطقس",
                        style = MaterialTheme.typography.title,
                        fontSize = 16.sp,
                    )

                    Column(
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.padding(vertical = 8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .height(15.dp)
                                .fillMaxWidth()
                                .shimmerEffect()
                        )
                        Box(
                            modifier = Modifier
                                .height(15.dp)
                                .fillMaxWidth()
                                .shimmerEffect()
                        )
                        Box(
                            modifier = Modifier
                                .height(15.dp)
                                .fillMaxWidth()
                                .shimmerEffect()
                        )
                    }
                }
            }
        }
    } else {
        WeatherBox(weather = uiState.weather, modifier = modifier)
    }
}

@Composable
fun WeatherBox(
    weather: WeatherInfo?,
    modifier: Modifier = Modifier
) {
    Surface(
        border = BorderStroke(1.dp, gray),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text(
                text = "جو مناسب لرى نبات العنب",
                style = MaterialTheme.typography.body,
                fontSize = 11.sp,
                modifier = Modifier.align(Alignment.TopEnd)
            )
            Column {

                Text(
                    text = "الطقس",
                    style = MaterialTheme.typography.title,
                    fontSize = 16.sp,
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Column {
                        Text(
                            text = "جودة الهواء",
                            style = MaterialTheme.typography.body,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "الرياح", style = MaterialTheme.typography.body,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "هبات الرياح", style = MaterialTheme.typography.body,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Column(
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        Text(
                            text = stringResource(id = weather?.airQuality!!),
                            style = MaterialTheme.typography.body,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = stringResource(id = weather.currentWeatherData?.windDirection!!) +
                                    " " +
                                    stringResource(
                                        id = R.string.wind_unit,
                                        weather.currentWeatherData.windSpeed.toString()
                                    ),
                            style = MaterialTheme.typography.body,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = stringResource(
                                id = R.string.wind_unit,
                                weather.currentWeatherData.windGusts.toString()
                            ),
                            style = MaterialTheme.typography.body,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.align(Alignment.BottomEnd)
            ) {
                // Weather description
                Text(
                    text = stringResource(id = weather?.currentWeatherData?.weatherType?.weatherDesc!!),
                    color = greenDark,
                    style = MaterialTheme.typography.body,
                    fontSize = 11.sp,
                    modifier = Modifier
                        .align(Alignment.Bottom)
                        .padding(bottom = 10.dp)
                )
                // Weather degree
                Text(
                    text = "${weather.currentWeatherData.temperatureCelsius.toInt()}°",
                    color = greenDark,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold
                )
                // Weather icon
                Icon(
                    painter = painterResource(id = weather.currentWeatherData.weatherType.iconRes),
                    contentDescription = null,
                    tint = greenDark,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}

@Composable
fun LatestNewsList(
    uiState: MainUiState,
    isNews: Boolean,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    val scrollState = rememberLazyListState()

    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .height(100.dp)
                .background(greenLight)
        )

        LazyRow(state = scrollState) {
            if (uiState.isNewsLoading) {
                items(10) {
                    LatestNewsListItemLoading(
                        modifier = Modifier
                            .padding(8.dp)
                            .height(120.dp)
                            .width(160.dp)
                    )
                }
            } else {
                items(uiState.news.size) {
                    LatestNewsListItem(
                        news = if (isNews) uiState.news[it] else uiState.offers[it],
                        modifier = Modifier
                            .padding(8.dp)
                            .height(120.dp)
                            .width(160.dp)
                    )
                }
            }
        }

        // Go To First Button
        Surface(
            shadowElevation = 8.dp,
            shape = CircleShape,
            modifier = Modifier
                .padding(start = 16.dp)
                .align(Alignment.CenterStart)
                .clickable {
                    val firstVisibleItemIndex = scrollState.firstVisibleItemIndex
                    scope.launch {
                        if (firstVisibleItemIndex > 2) {
                            scrollState.animateScrollToItem(firstVisibleItemIndex - 3)
                        } else {
                            scrollState.animateScrollToItem(0)
                        }
                    }
                }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
                contentDescription = null,
                tint = greenLight,
                modifier = Modifier.padding(8.dp)
            )
        }

        // Go To Last Button
        Surface(
            shadowElevation = 8.dp,
            shape = CircleShape,
            modifier = Modifier
                .padding(end = 16.dp)
                .align(Alignment.CenterEnd)
                .clickable { scope.launch { scrollState.animateScrollToItem(uiState.news.size) } }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
                contentDescription = null,
                tint = greenLight,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun LatestNewsListItemLoading(
    modifier: Modifier = Modifier
) {
    Surface(
        shadowElevation = 16.dp,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxSize()
        ) {
            // Item Image
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .shimmerEffect()
            )

            // Item Title
            Box(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 14.dp)
                    .height(10.dp)
                    .fillMaxWidth()
                    .shimmerEffect()
            )
        }
    }
}

@Composable
fun LatestNewsListItem(
    news: News,
    modifier: Modifier = Modifier
) {
    Surface(
        shadowElevation = 16.dp,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxSize()
        ) {
            // Item Image
            EmptyImage(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            )

            // Item Title
            Text(
                text = news.title,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                color = greenDark,
                maxLines = 1,
                style = MaterialTheme.typography.body,
                fontSize = 11.sp,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 14.dp)
            )
        }
    }
}

@Composable
fun BottomCard(
    title: Int,
    description: Int,
    icon: Int,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier,
    body: @Composable () -> Unit = { }
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 8.dp,
        modifier = modifier.clickable { onItemClick() }
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            // Card Title
            Text(
                text = stringResource(id = title),
                style = MaterialTheme.typography.title,
                fontSize = 16.sp
            )

            // Card Description
            Text(
                text = stringResource(id = description),
                style = MaterialTheme.typography.body,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
            )

            body()

            Row {
                Spacer(modifier = Modifier.weight(1f))

                // Card Icon
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    tint = greenDark,
                    modifier = Modifier
                        .padding(start = 6.dp)
                        .size(24.dp)
                )
            }
        }
    }
}

@Composable
fun ProblemImagesRow(
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.problem_images1),
            contentDescription = null,
            tint = greenLight,
            modifier = Modifier.size(60.dp)
            )
        // Arrow Icon
        Icon(
            painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
            contentDescription = null,
            tint = gray,
            modifier = Modifier.padding(12.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.problem_images2),
            contentDescription = null,
            tint = greenLight,
            modifier = Modifier.size(60.dp)
        )
        // Arrow Icon
        Icon(
            painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
            contentDescription = null,
            tint = gray,
            modifier = Modifier.padding(12.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.problem_images3),
            contentDescription = null,
            tint = greenLight,
            modifier = Modifier.size(60.dp)
        )
    }
}

fun isPermissionGranted(context: Context): Boolean {
    return ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
}

fun isGpsEnabled(context: Context): Boolean {
    val locationManager =
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    return locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
            locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
}

private fun grantPermission(
    activityResultLauncher: ManagedActivityResultLauncher<String, Boolean>
) {
    activityResultLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
}

private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
@SuppressLint("MissingPermission")
private fun getLastLocation(
    context: Context,
    getWeather: (Double, Double) -> Unit,
    activityResultLauncher: ManagedActivityResultLauncher<String, Boolean>? = null
) {
    fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    if (isPermissionGranted(context)) {
        if (isGpsEnabled(context)) {
            fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                val location: Location? = task.result
                if (location == null) {
                    requestNewLocationData(context, getWeather)
                } else {
                    getWeather(location.latitude, location.longitude)
                }
            }
        } else {
            Toast.makeText(context, R.string.turn_on_location_toast, Toast.LENGTH_LONG).show()
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            context.startActivity(intent)
        }
    } else {
        if (activityResultLauncher != null) {
            grantPermission(activityResultLauncher)
        }
    }
}

@SuppressLint("MissingPermission")
@Suppress("DEPRECATION")
private fun requestNewLocationData(
    context: Context,
    writeLocationToPreferences: (Double, Double) -> Unit
) {
    val mLocationRequest = LocationRequest()
    mLocationRequest.apply {
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        interval = 0
        fastestInterval = 0
        numUpdates = 1
    }

    fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    fusedLocationProviderClient.requestLocationUpdates(
        mLocationRequest, object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                val mLastLocation: Location = locationResult.lastLocation!!
                writeLocationToPreferences(mLastLocation.latitude, mLastLocation.longitude)
            }
        },
        Looper.myLooper()
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenContentLoadingPreview() {
    MainScreenContent(
        uiState = MainUiState(
            weather = null,
            news = fakeNews
        ),
        openScreen = { }
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenContentPreview() {
    MainScreenContent(
        uiState = MainUiState(
            weather = null,
            news = fakeNews,
            isWeatherLoading = false,
            isNewsLoading = false
        ),
        openScreen = { }
    )
}

val fakeNews = listOf(
    News(title = "ابتكار طرق رى جديدة"),
    News(title = "ابتكار طرق رى جديدة"),
    News(title = "ابتكار طرق رى جديدة"),
)