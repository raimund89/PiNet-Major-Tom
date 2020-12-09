package pinet.majortom.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val DefaultSectionPadding = 12.dp

@Composable
fun MajorTomTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = darkColors(
        primary = Color(0xFF1EB980),
        surface = Color(0xFF26282F),
        onSurface = Color.White,
        background = Color(0xFF26282F),
        onBackground = Color.White
    )

    val typography = Typography(
        defaultFontFamily = FontFamily.Default,
        body1 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ),
        h2 = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
    )

    MaterialTheme(
            colors = colors,
            typography = typography,
            content = content
            // Add shapes
    )
}