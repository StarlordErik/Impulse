package de.seleri.frontend.screens

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
import androidx.navigation.compose.rememberNavController
import de.seleri.frontend.ImpulseTheme
import de.seleri.tools.dummyViewModel
import de.seleri.viewModel.ImpulseViewModel

/**
 * Startbildschirm der App
 *
 * @param navController [Navigation] übergibt die Kontrolle für weitere [Screens]
 * @param viewModel [ImpulseViewModel] mit allen Daten und Funktionen der App
 */
@Composable
fun StartScreen(navController: NavHostController, viewModel: ImpulseViewModel) {
  val spiele = viewModel.spiele

  LazyColumn(
    modifier = Modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
  ) {
    item {
      Titel()
    }
    items(spiele, key = {it.id}) {spiel ->
      SammlungsButton(viewModel.getName(spiel)) {
        navController.navigate(Screens.SpielScreen.mitDerID(spiel.id))
      }
    }
  }
}

/**
 * Darstellung des App-Titels
 */
@Composable
fun Titel() {
  val windowInfo = LocalWindowInfo.current
  val density = LocalDensity.current
  val containerHeightDp = windowInfo.containerSize.height

  // Umrechnung von px in dp
  val containerHeight = with(density) {containerHeightDp.toDp()}
  val anteilDesTitelsAmBildschirm = 3.toFloat()
  val titelHoehe = containerHeight / anteilDesTitelsAmBildschirm

  Box(
    modifier = Modifier
      .fillMaxWidth()
      .height(titelHoehe), //.
    contentAlignment = Alignment.Center
  ) {
    Text(
      text = "Impulse", color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.displayLarge
    )
  }
}

/**
 * Darstellung von [de.seleri.backend.SammlungAnSpielelementen] als Button
 *
 * @param buttonText Text des Buttons
 * @param onClick Funktion, die ausgeführt wird, wenn der Button geklickt wird
 */
@Composable
fun SammlungsButton(buttonText: String, onClick: () -> Unit) {
  Card(
    onClick = onClick,
    colors = CardDefaults.cardColors(
      containerColor = MaterialTheme.colorScheme.secondaryContainer,
      contentColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.2f)
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
        text = buttonText,
        color = MaterialTheme.colorScheme.onSecondaryContainer,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(15.dp)
      )
    }
  }
}

/**
 * Preview von [StartScreen] mit Dummy-Daten
 */
@Preview
@Composable
fun StartScreenPreview() {
  val dummyNavController = rememberNavController()
  val dummyViewModel = dummyViewModel()

  ImpulseTheme {
    StartScreen(dummyNavController, dummyViewModel)
  }
}
