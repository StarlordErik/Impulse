package de.seleri.impulse

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.HiltAndroidApp
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

@HiltAndroidApp
class Impulse : Application()
