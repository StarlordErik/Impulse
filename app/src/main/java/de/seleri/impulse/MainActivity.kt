package de.seleri.impulse

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import de.seleri.spielelemente.Datenbanksystem
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity: ComponentActivity() {
    @Inject
    lateinit var dbs: Datenbanksystem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImpulseTheme {
                Navigation(dbs)
            }
        }
    }
}


@HiltAndroidApp
class Impulse: Application()

