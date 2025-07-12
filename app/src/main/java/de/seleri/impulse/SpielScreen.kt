package de.seleri.impulse

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import de.seleri.spielelemente.Datenbanksystem
import de.seleri.spielelemente.Spiel
import de.seleri.spielelemente.Sprachen

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
