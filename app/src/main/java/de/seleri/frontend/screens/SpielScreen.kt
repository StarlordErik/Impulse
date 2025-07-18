package de.seleri.frontend.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.seleri.backend.Spiel
import de.seleri.frontend.ImpulseTheme
import de.seleri.tools.dummyViewModel
import de.seleri.viewModel.ImpulseViewModel

/**
 * Darstellung eines Spiel als eine Bildschirmoberfläche
 *
 * @param viewModel [ImpulseViewModel] mit allen Daten und Funktionen der App
 * @param spiel [Spiel], das angezeigt wird
 */
@Composable
fun SpielScreen(viewModel: ImpulseViewModel, spiel: Spiel) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
  ) {
    Spacer(modifier = Modifier.height(10.dp))

    SpielScreenRow {
      SpielTitel(viewModel.getName(spiel), Modifier.weight(1f))
      SpielScreenIcon(SpielScreenIcons.Einstellungsrad)
    }

    val kartentexte = remember {mutableStateMapOf<Int, String>()}
    LazyColumn(
      modifier = Modifier
        .weight(1f)
        .fillMaxWidth(), verticalArrangement = Arrangement.Center
    ) {
      items(spiel.getAktuelleKategorien().toList(), key = {it.id}) {kategorie ->
        val initialText = viewModel.getName(kategorie)
        val kartentext =
          kartentexte[kategorie.id]
            ?: initialText

        SammlungsButton(kartentext) {
          kartentexte[kategorie.id] = viewModel.getRandomKartentext(kategorie)
        }
      }
    }

    SpielScreenRow {
      SpielScreenIcon(SpielScreenIcons.PfeilFuerLetzteKarte)
      SpielScreenIcon(SpielScreenIcons.KarteLoeschen)
    }
  }
}

/**
 * Gruppierung von den Row-Inhalten des [SpielScreen]
 *
 * @param content Inhalt der Row
 */
@Composable
fun SpielScreenRow(content: @Composable RowScope.() -> Unit) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 0.dp),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {
    content()
  }
}

/**
 * Darstellung des Spiele-Titels
 *
 * @param name Name des Spiels aus [Spiel.localizations]
 * @param modifier [Modifier] für die Darstellung in [SpielScreenRow]
 */
@Composable
fun SpielTitel(name: String, modifier: Modifier) {
  Text(
    text = name,
    color = MaterialTheme.colorScheme.primary,
    style = MaterialTheme.typography.displayMedium,
    maxLines = 2,
    overflow = TextOverflow.Ellipsis,
    modifier = modifier
  )
}

/**
 * Darstellung eines Icons für den [SpielScreen]
 *
 * @param icon Icon aus [SpielScreenIcons]
 */
@Composable
fun SpielScreenIcon(icon: SpielScreenIcons) {
  Icon(
    painter = painterResource(icon.ressource),
    contentDescription = stringResource(icon.beschreibung),
    tint = MaterialTheme.colorScheme.primaryContainer,
    modifier = Modifier.size(50.dp)
  )
}

/**
 * Preview von [SpielScreen] mit Dummy-Daten
 */
@Preview
@Composable
fun SpielScreenPreview() {
  val dummyViewModel = dummyViewModel()
  val dummySpiel = dummyViewModel.getSpiel(1)

  ImpulseTheme {
    SpielScreen(dummyViewModel, dummySpiel)
  }
}
