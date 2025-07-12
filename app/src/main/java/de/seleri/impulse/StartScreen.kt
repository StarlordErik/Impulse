package de.seleri.impulse

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import de.seleri.spielelemente.Datenbanksystem
import de.seleri.spielelemente.Sprachen

@Composable
fun StartScreen(navController: NavHostController, dbs: Datenbanksystem) {
    Column {
        Text(text = "Impulse")

        dbs.spiele.forEach { spiel ->
            Card(
                onClick = {
                    navController.navigate(Screen.Spiel.mitDerID(spiel.id))
                }
            ) {
                Text(text = spiel.localizations[Sprachen.OG]!!)
            }
        }
    }
}
