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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.seleri.spielelemente.Datenbanksystem
import de.seleri.spielelemente.Spiel
import de.seleri.spielelemente.Sprachen
import kotlin.collections.set
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope

@Composable
fun SpielScreen(dbs: Datenbanksystem, spiel: Spiel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        SpielScreenRow {
            SpielTitel(spiel.localizations[Sprachen.OG]!!)
            SpielScreenIcon(SpielIcon.Einstellungsrad)
        }

        val kartentexte = remember { mutableStateMapOf<Int, String>() }
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(), verticalArrangement = Arrangement.Center
        ) {
            items(spiel.getAktuelleKategorien().toList(), key = { it.id }) { kategorie ->
                val initialText = kategorie.localizations[Sprachen.OG]!!
                val kartentext = kartentexte[kategorie.id] ?: initialText

                SammlungsButton(kartentext) {
                    kartentexte[kategorie.id] = dbs.getRandomKartentext(kategorie)
                }
            }
        }

        SpielScreenRow {
            SpielScreenIcon(SpielIcon.PfeilFuerLetzteKarte)
            SpielScreenIcon(SpielIcon.KarteLoeschen)
        }
    }
}

@Composable
fun SpielScreenRow(content: @Composable RowScope.() -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
    }
}

@Composable
fun SpielTitel(name: String) {
    Text(
        text = name,
        color = MaterialTheme.colorScheme.primary,
        style = MaterialTheme.typography.displayMedium
    )
}

sealed class SpielIcon(
    @param:DrawableRes val ressource: Int, @param:StringRes val beschreibung: Int
) {
    object Einstellungsrad: SpielIcon(
        R.drawable.settings_for_game_mechanics, R.string.settings_for_game_mechanics
    )
    object PfeilFuerLetzteKarte: SpielIcon(
        R.drawable.arrow_back, R.string.arrow_back
    )

    object KarteLoeschen: SpielIcon(
        R.drawable.remove_card, R.string.remove_card
    )

}

@Composable
fun SpielScreenIcon(icon: SpielIcon) {
    Icon(
        painter = painterResource(icon.ressource),
        contentDescription = stringResource(icon.beschreibung),
        tint = MaterialTheme.colorScheme.primaryContainer,
        modifier = Modifier.size(50.dp)
    )
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
            SpielScreenRow {
                SpielTitel("Erzählt euch mehr")
                SpielScreenIcon(SpielIcon.Einstellungsrad)
            }

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                items(kategorien) { spielname ->
                    SammlungsButton(spielname) {}
                }
            }

            SpielScreenRow {
                SpielScreenIcon(SpielIcon.PfeilFuerLetzteKarte)
                SpielScreenIcon(SpielIcon.KarteLoeschen)
            }
        }
    }
}
