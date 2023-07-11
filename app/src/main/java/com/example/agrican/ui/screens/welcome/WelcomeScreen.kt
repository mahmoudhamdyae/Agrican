package com.example.agrican.ui.screens.welcome

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.LocaleListCompat
import com.example.agrican.R
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.screens.onboarding.OnboardingDestination
import com.example.agrican.ui.theme.body
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.white

object WelcomeDestination: NavigationDestination {
    override val route: String = "welcome"
    override val titleRes: Int = R.string.app_name
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun WelcomeScreen(
    openAndClear: (String) -> Unit,
    modifier: Modifier = Modifier
) {
//    var visible by remember { mutableStateOf(true) }
//    val density = LocalDensity.current
//
//    val configuration = LocalConfiguration.current
//    val screenHeight = configuration.screenHeightDp.dp
//    val screenWidth = configuration.screenWidthDp.dp
//
//    AnimatedVisibility(
//        visible = visible,
//        enter = slideInVertically(
//            animationSpec =
//            spring(
//                stiffness = Spring.StiffnessMediumLow,
//                visibilityThreshold = IntOffset.VisibilityThreshold
//            ),
//            initialOffsetY = { fullHeight: Int ->
//                fullHeight / 2
//            }
//        ) + scaleIn(
//            initialScale = screenWidth.toPx()
//        )
//    ) {
//        Box(modifier = modifier.fillMaxSize()) {
//            Image(
//                painter = painterResource(id = R.drawable.splash),
//                contentDescription = null,
//                modifier = Modifier
//                    .padding(top = 64.dp)
//                    .width(120.dp)
//                    .align(Alignment.TopCenter)
//            )
//        }
////        Box(modifier = modifier.fillMaxSize()) {
////            Image(
////                painter = painterResource(id = R.drawable.splash),
////                contentDescription = null,
////                modifier = Modifier
////                    .fillMaxWidth()
////                    .align(Alignment.Center)
////            )
////        }
//    }
//
//
//
//
//
//    Box(
//        modifier = modifier.fillMaxSize()
//    ) {
//        Button(
//            onClick = { visible = !visible },
//            modifier = Modifier
//                .fillMaxWidth()
//                .align(Alignment.Center)
//        ) {
//            Text(text = "hahahahahahahahahah")
//        }
//        }


    WelcomeScreenContent(
        openAndClear = openAndClear,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreenContent(
    openAndClear: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isEnglish: Boolean? by rememberSaveable {
        mutableStateOf(null)
    }
    Box(modifier = modifier.fillMaxSize()) {

        BackGroundImage(modifier = Modifier.align(Alignment.BottomCenter))

        Image(
            painter = painterResource(id = R.drawable.splash),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 64.dp)
                .width(120.dp)
                .align(Alignment.TopCenter)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(24.dp)
        ) {
            // Welcome Text
            Text(
                text = "Welcome to Agrican",
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                style = MaterialTheme.typography.body
            )
            Text(
                text = "أهلا بك فى أجريكان",
                fontSize = 25.sp,
                fontWeight = FontWeight.ExtraBold,
                style = MaterialTheme.typography.body,
                modifier = Modifier.padding(8.dp)
            )

            Row {
                // English Button
                FilterChip(
                    selected = isEnglish == true,
                    onClick = { isEnglish = true },
                    border = FilterChipDefaults.filterChipBorder(borderColor = gray),
                    colors = FilterChipDefaults.elevatedFilterChipColors(
                        selectedContainerColor = greenLight
                    ),
                    label = {
                        Text(
                            text = "English",
                            textAlign = TextAlign.Center,
                            color = if (isEnglish == true) white else greenLight,
                            fontSize = 16.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        )
                    },
                    shape = RoundedCornerShape(32.dp),
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp)
                )

                // Arabic Button
                FilterChip(
                    selected = isEnglish == false,
                    onClick = { isEnglish = false },
                    border = FilterChipDefaults.filterChipBorder(borderColor = gray),
                    colors = FilterChipDefaults.elevatedFilterChipColors(
                        selectedContainerColor = greenLight
                    ),
                    label = {
                        Text(
                            text = "العربية",
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp,
                            color = if (isEnglish == false) white else greenLight,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        )
                    },
                    shape = RoundedCornerShape(32.dp),
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp)
                )
            }

            // Continue Button
            if (isEnglish != null) {
                Row {
                    Spacer(modifier = Modifier.weight(1f))

                    IconButton(
                        onClick = {
                            val language = if (isEnglish == true) "en" else "ar"
                            AppCompatDelegate.setApplicationLocales(
                                LocaleListCompat.forLanguageTags(language)
                            )
                            openAndClear(OnboardingDestination.route)
                        },
                        colors = IconButtonDefaults.iconButtonColors(containerColor = greenDark),
                        modifier = Modifier
                            .padding(16.dp)
                            .clip(CircleShape)
                            .size(32.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_forward_24),
                            contentDescription = null,
                            tint = white
                        )
                    }
                }
            } else {
                Box(modifier = Modifier.height(64.dp))
            }
        }
    }
}

@Composable
fun BackGroundImage(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.height(IntrinsicSize.Max)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.welcome_screen_background),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }

        Row(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Spacer(modifier = Modifier.weight(3f))
            Image(
                painter = painterResource(id = R.drawable.welcome_screen_tree),
                contentDescription = null
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreenContent(
        openAndClear = { }
    )
}