package de.seleri.impulse

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.seleri.spielelemente.Datenbanksystem
import de.seleri.spielelemente.Spiel
import de.seleri.spielelemente.Sprachen
import kotlin.collections.set

@Composable
fun SpielScreen(dbs: Datenbanksystem, spiel: Spiel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        val kartentexte = remember { mutableStateMapOf<Int, String>() }

        LazyColumn(
            modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center
        ) {
            items(spiel.getAktuelleKategorien().toList(), key = { it.id }) { kategorie ->
                val initialText = kategorie.localizations[Sprachen.OG]!!
                val kartentext = kartentexte[kategorie.id] ?: initialText

                SammlungsButton(kartentext) {
                    kartentexte[kategorie.id] = dbs.getRandomKartentext(kategorie)
                }
            }
        }
    }
}

@Preview
@Composable
fun SpielScreenPreview() {
    val kategorien = listOf(
        "Gedankenspiel",
        "Würdest du lieber ...\n\n ... das Weltall oder den Ozean erkunden?\n ... den ganzen Tag lang Anzug oder " + //.
                "Jogginganzug tragen?\n ... alle Sprachen dieser Welt oder alle Instrumente dieser Welt beherrschen?",
        "Selbstreflexion",
        "Mit welcher gemeinsamen Reise oder welchem Ort, den wir zusammen besucht haben, verbindest du besondere " + //.
                "Erinnerungen?",
        "Kompliment",
        "Wie hast du die Beziehung deiner Eltern wahrgenommen?\n\nHaben sie etwas besonders richtig oder falsch " + //.
                "gemacht?"
    )
    ImpulseTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Erzählt euch mehr",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.displayMedium
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_action_name),
                    contentDescription = "Settings Icon",
                    tint = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier.size(48.dp)
                )
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                verticalArrangement = Arrangement.Center
            ) {
                items(kategorien) { spielname ->
                    SammlungsButton(spielname) {}
                }
            }
        }
    }
}
