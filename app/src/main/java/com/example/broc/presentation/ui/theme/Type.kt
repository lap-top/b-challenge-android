package com.example.broc.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.broc.R

// Set of Material typography styles to start with

val openSans = FontFamily(
    Font(R.font.opensans_light, weight = FontWeight.Light),
    Font(R.font.opensans_regular, weight = FontWeight.Normal),
    Font(R.font.opensans_bold, weight = FontWeight.Bold),
    Font(R.font.opensans_bold, weight = FontWeight.SemiBold),
)
val broccoliFont = FontFamily(
    Font(R.font.luckiestguy_regular, weight = FontWeight.Normal)
)

val Typography = Typography(
    /*titleLarge = TextStyle(
        fontFamily = broccoliFont,
        fontWeight = FontWeight.Normal,
        fontSize = 50.sp,
        letterSpacing = 0.5.sp
    ),
     */
    titleLarge = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
    ),

    headlineMedium = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
    ),


    headlineSmall = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    labelLarge = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)