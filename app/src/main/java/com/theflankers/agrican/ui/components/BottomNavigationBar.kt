package com.theflankers.agrican.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.theflankers.agrican.R
import com.theflankers.agrican.common.utils.toPx
import com.theflankers.agrican.ui.screens.home.BottomNavItem
import com.theflankers.agrican.ui.theme.greenDark
import com.theflankers.agrican.ui.theme.iconGray

@Composable
fun BottomNavigationBar(
    selectedItem: Int,
    setSelectedItem: (Int) -> Unit,
    bottomNavItems: List<BottomNavItem>,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.height(95.dp)) {

        // Profile Item
        Surface(
            shape = CircleShape,
            shadowElevation = 32.dp,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .clickable { setSelectedItem(1) }
        ) {
            Icon(
                painter = painterResource(id = bottomNavItems[1].icon),
                contentDescription = null,
                tint = if (selectedItem == 1) greenDark else iconGray,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .padding(10.dp)
            )
        }

        // Main and Services Items
        Surface(
            shape = NavigationBarCustomShape(
                radius1 = 42.dp.toPx(),
                radius2 = 6.dp.toPx()
            ),
            shadowElevation = 32.dp,
            modifier = Modifier
                .height(62.dp)
                .align(Alignment.BottomCenter)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize()
            ) {
                // Agrican Services Item
                BottomNavigationItem(
                    text = bottomNavItems[2].name,
                    icon = bottomNavItems[2].icon,
                    color = if (selectedItem == 2) greenDark else iconGray,
                    modifier = Modifier
                        .weight(1f)
                        .clickable { setSelectedItem(2) }
                )

                // Profile Text
                Text(
                    text = stringResource(id = bottomNavItems[1].name),
                    color = if (selectedItem == 1) greenDark else iconGray,
                    textAlign = TextAlign.Center,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .clickable { setSelectedItem(1) }
                        .padding(bottom = 4.dp)
                        .weight(1f)
                        .align(Alignment.Bottom)
                )

                // Main Item
                BottomNavigationItem(
                    text = bottomNavItems[0].name,
                    icon = bottomNavItems[0].icon,
                    color = if (selectedItem == 0) greenDark else iconGray,
                    modifier = Modifier
                        .weight(1f)
                        .clickable { setSelectedItem(0) }
                )
            }
        }
    }
}

@Composable
fun BottomNavigationItem(
    text: Int,
    icon: Int,
    color: Color,
    modifier: Modifier = Modifier
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = color
        )
        Text(
            text = stringResource(id = text),
            color = color,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    val bottomNavItems = listOf(
        BottomNavItem(name = R.string.navigation_main, icon = R.drawable.main),
        BottomNavItem(name = R.string.navigation_profile, icon = R.drawable.default_image),
        BottomNavItem(name = R.string.navigation_agrican_services, icon = R.drawable.services)
    )
    BottomNavigationBar(selectedItem = 0, setSelectedItem = { }, bottomNavItems = bottomNavItems)
}

class NavigationBarCustomShape(
    private val radius1: Float,
    private val radius2: Float
): Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            // Draw your custom path here
            path = drawPath(size = size, radius1 = radius1, radius2 = radius2)
        )
    }

    private fun drawPath(
        size: Size,
        radius1: Float,
        radius2: Float
    ): Path {
        return Path().apply {
            reset()
            val r1 = radius1
            val r2 = radius2
            val w = size.width
            lineTo(x = w / 2 - r1 - r2, y = 0f)
            
            // Right Small Circle
            arcTo(
                rect = Rect(
                    left = w / 2 - r1 - 2 * r2,
                    top = 0f,
                    right = w / 2 - r1,
                    bottom = 2 * r2
                ),
                startAngleDegrees = 180.0f,
                sweepAngleDegrees = 180.0f,
                forceMoveTo = false
            )

            // Large Circle
            arcTo(
                rect = Rect(
                    left = w / 2 - r1,
                    top = -r1,
                    right = w / 2 + r1,
                    bottom = r1
                ),
                startAngleDegrees = 180.0f,
                sweepAngleDegrees = -180.0f,
                forceMoveTo = false
            )

            // Left Small Circle
            arcTo(
                rect = Rect(
                    left = w / 2 + r1,
                    top = 0f,
                    right = w / 2 + r1 + 2 * r2,
                    bottom = 2 * r2
                ),
                startAngleDegrees = 180.0f,
                sweepAngleDegrees = 90.0f,
                forceMoveTo = false
            )
            lineTo(x = size.width, y = 0f)
            lineTo(x = size.width, y = size.height)
            lineTo(x = 0f, y = size.height)
            lineTo(x = 0f, y = 0f)
            close()
        }
    }
}