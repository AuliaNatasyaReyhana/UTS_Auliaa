package com.example.todolist_aulia.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.todolist_aulia.R

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)

val LobsterFontFamily = FontFamily(
    Font(R.font.lobster_regular, weight = FontWeight.Normal)
)

private val AppTypography = androidx.compose.material3.Typography().copy(
    displayLarge = androidx.compose.material3.Typography().displayLarge.copy(
        fontFamily = LobsterFontFamily
    ),
    displayMedium = androidx.compose.material3.Typography().displayMedium.copy(
        fontFamily = LobsterFontFamily
    ),
    headlineLarge = androidx.compose.material3.Typography().headlineLarge.copy(
        fontFamily = LobsterFontFamily
    ),
    titleLarge = androidx.compose.material3.Typography().titleLarge.copy(
        fontFamily = LobsterFontFamily
    ),
    bodyLarge = androidx.compose.material3.Typography().bodyLarge.copy(
        fontFamily = LobsterFontFamily
    ),
    labelLarge = androidx.compose.material3.Typography().labelLarge.copy(
        fontFamily = LobsterFontFamily
    )
    // Tambahkan override lain jika perlu
)

@Composable
fun Todolist_auliaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context)
            else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else      -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography  = AppTypography,
        content     = content
    )
}
