package de.seleri.impulse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import de.seleri.spielelemente.Datenbanksystem
import de.seleri.spielelemente.Spiel
import de.seleri.spielelemente.Sprachen

class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dbs = Datenbanksystem.generieren(this)
        setContent {
            Navigation(dbs)
        }
    }
}

@Composable
fun StartScreen(navController: NavHostController, dbs: Datenbanksystem) {
    Column {
        Text(text = "Impulse")

        dbs.spiele.forEach { spiel ->
            Button(
                onClick = {
                    navController.navigate(Screen.Spiel.mitDerID(spiel.id))
                }) {
                Text(text = spiel.localizations[Sprachen.OG]!!)
            }
        }
    }
}

@Composable
fun SpielScreen(dbs: Datenbanksystem, spiel: Spiel) {
    Column {
        spiel.getAktuelleKategorien().forEach { kategorie ->
            var kartentext by remember { mutableStateOf(kategorie.localizations[Sprachen.OG]!!) }
            Button(
                onClick = {
                    kartentext = dbs.getRandomKartentext(kategorie)
                }) {
                Text(text = kartentext)
            }
        }
    }
}
