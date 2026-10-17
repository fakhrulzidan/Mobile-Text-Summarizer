package com.belajar.text_summarizer.core.designSystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.belajar.text_summarizer.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)
// Font Bricolage Grotesque
val bricolageFont = GoogleFont("Bricolage Grotesque")
val bricolageFamily = FontFamily(
    Font(googleFont = bricolageFont, fontProvider = provider, weight = FontWeight.ExtraBold),
    Font(googleFont = bricolageFont, fontProvider = provider, weight = FontWeight.Normal),
    Font(googleFont = bricolageFont, fontProvider = provider, weight = FontWeight.Medium),
    Font(googleFont = bricolageFont, fontProvider = provider, weight = FontWeight.Bold),
    Font(googleFont = bricolageFont, fontProvider = provider, weight = FontWeight.SemiBold),
)

// Font Source Serif 4
val sourceSerifFont = GoogleFont("Source Serif 4")
val sourceSerifFamily = FontFamily(
    Font(googleFont = sourceSerifFont, fontProvider = provider, weight = FontWeight.Normal),
    Font(googleFont = sourceSerifFont, fontProvider = provider, weight = FontWeight.Medium),
    Font(googleFont = sourceSerifFont, fontProvider = provider, weight = FontWeight.SemiBold),
    Font(googleFont = sourceSerifFont, fontProvider = provider, weight = FontWeight.Bold),
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyMedium = TextStyle(
        fontFamily = sourceSerifFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 25.sp,
        letterSpacing = 0.5.sp
    ),
    labelMedium = TextStyle(
        fontFamily =  bricolageFamily,
        fontWeight = FontWeight.SemiBold,
    ),
    headlineLarge = TextStyle(
        fontFamily = bricolageFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 34.sp,
//        lineHeight = 28.sp,
//        letterSpacing = 0.sp
    ),

)