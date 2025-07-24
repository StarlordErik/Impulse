package de.seleri.core.representation.ui.screens

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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import de.seleri.core.representation.viewModels.SpielscreenVM

@Composable
fun Spielscreen(
	vm: SpielscreenVM
) {
	val spiel = vm.spiel
	Column(
		modifier = Modifier
			.fillMaxSize()
			.background(MaterialTheme.colorScheme.background)
	) {
		Spacer(modifier = Modifier.height(10.dp))

		SpielScreenRow {
			SpielTitel(vm.getDarstellungAlsText(spiel), Modifier.weight(1f))
			SpielScreenIcon(SpielscreenIcons.Einstellungsrad)
		}

		val kartentexte = remember { mutableStateMapOf<Int, String>() }
		LazyColumn(
			modifier = Modifier
				.weight(1f)
				.fillMaxWidth(), verticalArrangement = Arrangement.Center
		) {
			items(spiel!!.bestandteile as List) { kategorie ->
				val initialText = vm.getDarstellungAlsText(kategorie)
				val kartentext = kartentexte[kategorie.id]
					?: initialText

				SammlungsButton(kartentext) {
					kartentexte[kategorie.id] = vm.getRandomKartentext(kategorie)
				}
			}
		}

		SpielScreenRow {
			SpielScreenIcon(SpielscreenIcons.PfeilFuerLetzteKarte)
			SpielScreenIcon(SpielscreenIcons.KarteLoeschen)
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
fun SpielScreenIcon(icon: SpielscreenIcons) {
	Icon(
		painter = painterResource(icon.ressource), contentDescription = null,
		tint = MaterialTheme.colorScheme.primaryContainer,
		modifier = Modifier.size(50.dp)
	)
}
