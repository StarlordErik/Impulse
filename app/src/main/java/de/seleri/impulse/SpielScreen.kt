package de.seleri.impulse

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.seleri.spielelemente.Datenbanksystem
import de.seleri.spielelemente.Spiel
import de.seleri.spielelemente.Sprachen

@Composable
fun SpielScreen(dbs: Datenbanksystem, spiel: Spiel) {
    val kartentexte = remember {
        mutableStateMapOf<Int, String>()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center
    ) {
        items(spiel.getAktuelleKategorien().toList(), key = { it.id }) { kategorie ->
            val initialText = kategorie.localizations[Sprachen.OG]!!
            val kartentext = kartentexte[kategorie.id] ?: initialText

            Card(
                onClick = {
                    kartentexte[kategorie.id] = dbs.getRandomKartentext(kategorie)
                },
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp, vertical = 10.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = kartentext,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(15.dp)
                    )
                }
            }
        }
    }
}
