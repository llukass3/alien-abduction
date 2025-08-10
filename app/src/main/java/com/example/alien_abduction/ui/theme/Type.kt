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
        fontSize = 25.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )

)