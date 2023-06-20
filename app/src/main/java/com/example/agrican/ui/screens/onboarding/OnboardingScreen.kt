package com.example.agrican.ui.screens.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.agrican.R
import com.example.agrican.ui.components.Background
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.screens.auth.login.LoginDestination
import com.example.agrican.ui.theme.black
import com.example.agrican.ui.theme.gray
import com.example.agrican.ui.theme.greenDark
import com.example.agrican.ui.theme.greenLight
import com.example.agrican.ui.theme.title
import com.example.agrican.ui.theme.white
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

object OnboardingDestination: NavigationDestination {
    override val route: String = "onboarding"
    override val titleRes: Int = R.string.app_name
}

class OnBoardingItem(
    @DrawableRes val image: Int,
    @StringRes val text: Int,
) {
    companion object{
        fun getData(): List<OnBoardingItem>{
            return listOf(
                OnBoardingItem(R.drawable.onboarding1, R.string.onboarding_text_1),
                OnBoardingItem(R.drawable.onboarding2, R.string.onboarding_text_2),
                OnBoardingItem(R.drawable.onboarding3, R.string.onboarding_text_3)
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(
    openAndClear: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val items = OnBoardingItem.getData()
    val scope = rememberCoroutineScope()
    val pageState = rememberPagerState()

    Background(body1 = {
        Box(
            contentAlignment = if (pageState.currentPage == 0) Alignment.TopCenter else Alignment.Center,
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
        ) {
            HorizontalPager(
                count = items.size,
                state = pageState,
            ) { page ->
                OnBoardingItem(item = items[page])
            }
            Indicators(
                size = OnBoardingItem.getData().size,
                index = pageState.currentPage,
                modifier = Modifier
                    .padding(32.dp)
                    .align(Alignment.BottomCenter)
            )
        }
    }, body2 = {
        // Next Button
        Button(
            onClick = {
                if (pageState.currentPage == items.size - 1) {
                    openAndClear(LoginDestination.route)
                } else {
                    scope.launch { pageState.animateScrollToPage(pageState.currentPage + 1) }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = white
            )
        ) {
            Text(
                text = stringResource(id = R.string.onboarding_button),
                color = greenLight,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }, modifier = modifier)
}

@Composable
fun OnBoardingItem(
    item: OnBoardingItem,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = modifier
    ) {
        // Onboarding Image
        Image(
            painter = painterResource(id = item.image),
            contentDescription = null
        )
        // Onboarding Text
        Text(
            text = stringResource(id = item.text),
            textAlign = TextAlign.Center,
            color = black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.title,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun Indicators(
    size: Int,
    index: Int,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        repeat(size) {
            Indicator(isSelected = it == index)
        }
    }
}

@Composable
fun Indicator(isSelected: Boolean) {
    val width = animateDpAsState(
        targetValue = 24.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )

    Box(
        modifier = Modifier
            .height(8.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                color = if (isSelected) greenDark else gray
            )
    )
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    OnboardingScreen(
        openAndClear = { }
    )
}