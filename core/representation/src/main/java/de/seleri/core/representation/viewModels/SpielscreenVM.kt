package de.seleri.core.representation.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.seleri.core.common.Sprache
import de.seleri.core.common.idTypes.SpielelementID
import de.seleri.core.domain.model.spielelemente.Kartentext
import de.seleri.core.domain.model.spielelemente.Kategorie
import de.seleri.core.domain.model.spielelemente.spiel.Spiel
import de.seleri.core.domain.useCases.GetKarteUC
import de.seleri.core.domain.useCases.GetSpielUC
import kotlinx.coroutines.launch

open class SpielscreenVM(
	private val spielID: SpielelementID.SpielID,
	private val getSpielUC: GetSpielUC, private val getKarteUC: GetKarteUC
): ViewModel(), SpielelementAlsTextDarstellen {

	var spiel by mutableStateOf<Spiel?>(null)
		private set

	var obereKategorie by mutableStateOf<Kategorie?>(null)
		private set
	var obereKartentexte by mutableStateOf<List<Kartentext>>(emptyList())
		private set
	var untereKategorie by mutableStateOf<Kategorie?>(null)
		private set
	var untereKartentexte by mutableStateOf<List<Kartentext>>(emptyList())
		private set

	init {
		viewModelScope.launch {
			val geladenesSpiel: Spiel = getSpielUC(spielID)
			spiel = geladenesSpiel

			val obereKarte = getKarteUC(geladenesSpiel.texteProKarte, geladenesSpiel)
			obereKategorie = obereKarte.kategorie
			obereKartentexte = obereKarte.kartentexte

			val untereKarte = getKarteUC(geladenesSpiel.texteProKarte, geladenesSpiel)
			untereKategorie = untereKarte.kategorie
			untereKartentexte = untereKarte.kartentexte
		}
	}

	fun getRandomKartentext(kategorie: Kategorie): String =
		obereKartentexte
			.first()
			.getBezeichnung(Sprache.OG)
}
