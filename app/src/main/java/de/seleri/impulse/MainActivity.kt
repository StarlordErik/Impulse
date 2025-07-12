package de.seleri.impulse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp() // Dein Einstiegspunkt für Compose UI
        }
    }
}

@Composable
fun MyApp() {
    MaterialTheme {
        Greeting("Welt")
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hallo, $name!")
}
