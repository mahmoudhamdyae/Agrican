package com.example.agrican.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.agrican.R

val cairoFontFamily = FontFamily(
    Font(R.font.cairo_black, weight = FontWeight.Black),
    Font(R.font.cairo_bold, weight = FontWeight.Bold),
    Font(R.font.cairo_extra_light, weight = FontWeight.ExtraLight),
    Font(R.font.cairo_extrabold, weight = FontWeight.ExtraBold),
    Font(R.font.cairo_light, weight = FontWeight.Light),
    Font(R.font.cairo_medium, weight = FontWeight.Medium),
    Font(R.font.cairo_regular, weight = FontWeight.Normal),
    Font(R.font.cairo_semibold, weight = FontWeight.SemiBold)
)

val Typography.title: TextStyle
    @Composable
    get() {
        return  TextStyle(
            fontFamily = cairoFontFamily,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = greenDark
        )
    }

val Typography.body: TextStyle
    @Composable
    get() {
        return  TextStyle(
            fontFamily = cairoFontFamily,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = textGray
        )
    }