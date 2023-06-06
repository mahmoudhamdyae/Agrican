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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.agrican.R
import com.example.agrican.ui.components.Background
import com.example.agrican.ui.navigation.NavigationDestination
import com.example.agrican.ui.screens.auth.login.LoginDestination
import com.example.agrican.ui.theme.spacing
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
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
                OnBoardingItem(R.drawable.ic_launcher_foreground, R.string.onboarding_text_1),
                OnBoardingItem(R.drawable.ic_launcher_foreground, R.string.onboarding_text_2),
                OnBoardingItem(R.drawable.ic_launcher_foreground, R.string.onboarding_text_3)
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
        Box(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
            HorizontalPager(
                count = items.size,
                state = pageState,
                modifier = Modifier.fillMaxSize().background(Color.White)
            ) { page ->
                OnBoardingItem(item = items[page])
            }
            Indicators(
                size = OnBoardingItem.getData().size,
                index = pageState.currentPage,
                modifier = Modifier
                    .padding(MaterialTheme.spacing.large)
                    .align(Alignment.BottomCenter)
            )
        }
    }, body2 = {
        Button(
            onClick = {
                if (pageState.currentPage == items.size - 1) {
                    openAndClear(LoginDestination.route)
                } else {
                    scope.launch { pageState.animateScrollToPage(pageState.currentPage + 1) }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            )
        ) {
            Text(
                text = stringResource(id = R.string.onboarding_button),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
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
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = item.image),
            contentDescription = null,
        )
        Text(
            text = stringResource(id = item.text),
            textAlign = TextAlign.Center
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
        horizontalArrangement = Arrangement.spacedBy(12.dp),
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
        targetValue = 25.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )

    Box(
        modifier = Modifier
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                color = if (isSelected) MaterialTheme.colorScheme.primary else Color(0XFFF8E2E7)
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