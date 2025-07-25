package de.seleri.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import de.seleri.core.representation.ui.ImpulseTheme

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
