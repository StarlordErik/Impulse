package de.seleri.impulse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
