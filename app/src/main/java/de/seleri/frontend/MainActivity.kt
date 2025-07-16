package de.seleri.frontend

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import de.seleri.frontend.screens.Navigation
import de.seleri.frontend.screens.Screens

/**
 * Einstiegspunkt der App:
 * setzt das Theme (Farbgebung, Formatierung, etc.) und die Navigation über die [Screens]
 */
@AndroidEntryPoint
class MainActivity: ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      ImpulseTheme {
        Navigation()
      }
    }
  }
}

/**
 * Hilt benötigt diese Klasse, um bestimmte Werte über die gesamte Laufzeit der App zur Verfügung zu stellen,
 * unabhängig von der Lebensdauer einer Activity oder eines [Screens].
 */
@HiltAndroidApp
class Impulse: Application()
