package com.example.alien_abduction.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.example.alien_abduction.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val AlienHeadingFont = FontFamily(
    Font(
        googleFont = GoogleFont(name = "Krona One"),
        fontProvider = provider
    )
)

val AlienBodyFont = FontFamily(
    Font(
        googleFont = GoogleFont(name = "Montserrat"),
        fontProvider = provider,
        weight = FontWeight.Light,
        style = FontStyle.Normal
    ),
    Font(
        googleFont = GoogleFont(name = "Montserrat"),
        fontProvider = provider,
        weight = FontWeight.Normal,
        style = FontStyle.Normal
    ),
    Font(
        googleFont = GoogleFont(name = "Montserrat"),
        fontProvider = provider,
        weight = FontWeight.Medium,
        style = FontStyle.Normal
    ),
    Font(
        googleFont = GoogleFont(name = "Montserrat"),
        fontProvider = provider,
        weight = FontWeight.SemiBold,
        style = FontStyle.Normal
    ),
    Font(
        googleFont = GoogleFont(name = "Montserrat"),
        fontProvider = provider,
        weight = FontWeight.Bold,
        style = FontStyle.Normal
    ),
    Font(
        googleFont = GoogleFont(name = "Montserrat"),
        fontProvider = provider,
        weight = FontWeight.ExtraBold,
        style = FontStyle.Normal
    )
)


//TODO: Create Fallback Fonts
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = AlienBodyFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = AlienBodyFont,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 26.sp,
        letterSpacing = 0.4.sp
    ),
    titleLarge = TextStyle(
        fontFamily = AlienBodyFont,
        fontWeight = FontWeight.W700,
        fontSize = 25.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    labelLarge = TextStyle(
        fontFamily = AlienHeadingFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = AlienHeadingFont,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = AlienBodyFont,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 24.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

// Extension properties for custom styles
val Typography.bodyMediumBold: TextStyle
    get() = bodyMedium.copy(fontWeight = FontWeight.Bold)
