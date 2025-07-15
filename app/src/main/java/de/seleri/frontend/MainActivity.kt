package de.seleri.frontend

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import de.seleri.frontend.screens.Navigation

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

@HiltAndroidApp
class Impulse: Application()
