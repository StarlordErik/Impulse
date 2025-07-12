package de.seleri.impulse

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import de.seleri.spielelemente.Datenbanksystem
import de.seleri.spielelemente.Spiel
import de.seleri.spielelemente.Sprachen

@Composable
fun StartScreen(navController: NavHostController, dbs: Datenbanksystem) {
    Column {
        Titel()

        dbs.spiele.forEach { spiel ->
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
            .height(titelHoehe),
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
fun SpielButton(navController: NavHostController, spiel: Spiel){
    Card(
        onClick = {
            navController.navigate(Screen.Spiel.mitDerID(spiel.id))
        }
    ) {
        Text(text = spiel.localizations[Sprachen.OG]!!)
    }
}
