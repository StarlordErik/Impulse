package de.seleri.impulse

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import de.seleri.spielelemente.Datenbanksystem
import de.seleri.spielelemente.Spiel
import de.seleri.spielelemente.Sprachen

@Composable
fun StartScreen(navController: NavHostController, dbs: Datenbanksystem) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        item {
            Titel()
        }
        items(dbs.spiele.toList(), key = { it.id }) { spiel ->
            SpielButton(navController, spiel)
        }
    }
}

@Preview
@Composable
fun Titel() {
    val windowInfo = LocalWindowInfo.current
    val density = LocalDensity.current
    val containerHeightDp = windowInfo.containerSize.height

    // Umrechnung von px in dp
    val containerHeight = with(density) { containerHeightDp.toDp() }
    val anteilDesTitelsAmBildschirm = 3.toFloat()
    val titelHoehe = containerHeight / anteilDesTitelsAmBildschirm

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(titelHoehe), //.
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Impulse",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.displayLarge
        )
    }
}

@Composable
fun SpielButton(navController: NavHostController, spiel: Spiel) {
    Card(
        onClick = {
            navController.navigate(Screen.Spiel.mitDerID(spiel.id))
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
                text = spiel.localizations[Sprachen.OG]!!,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(15.dp)
            )
        }
    }
}
