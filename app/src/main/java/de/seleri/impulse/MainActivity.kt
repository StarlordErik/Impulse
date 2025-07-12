package de.seleri.impulse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Typography
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import de.seleri.spielelemente.Datenbanksystem

class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dbs = Datenbanksystem.generieren(this)
        setContent {
            ImpulseTheme{
                Navigation(dbs)
            }
        }
    }
}

@Composable
fun ImpulseTheme(content: @Composable () -> Unit) {
    val farbpalette = darkColorScheme(
        primary = Color(color = 0xFFD36135),
        onPrimary = Color.White,
        primaryContainer = Color(color = 0xFFB15634),
        onPrimaryContainer = Color.White,

        secondary = Color(color = 0xFF478347),
        onSecondary = Color.White,
        secondaryContainer = Color(color = 0xFF3E5641),
        onSecondaryContainer = Color.White,

        tertiary = Color(color = 0xFF2196F3),
        onTertiary = Color.White,
        tertiaryContainer = Color(color = 0xFF3F51B5),
        onTertiaryContainer = Color.White,

        background = Color(color = 0xFF181F1C),
        onBackground = Color.White,

        surface = Color(color = 0xFF3E5641),
        onSurface = Color.White,
        surfaceVariant = Color(color = 0xFF324433),
        onSurfaceVariant = Color.White,

        outline = Color(color = 0xFF83BCA9)
    )

    val formatierung = Typography(
        displayLarge = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 60.sp,
            shadow = Shadow(
                color = Color.Black.copy(alpha = 0.3f),
                offset = Offset(2f, 2f),
                blurRadius = 6f
            )
        )
    )

    MaterialTheme(
        colorScheme = farbpalette,
        typography = formatierung,
        content = content
    )
}
