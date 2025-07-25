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
import de.seleri.core.domain.useCases.GetKategorieMitKarteUC
import de.seleri.core.domain.useCases.GetSpielUC
import kotlinx.coroutines.launch

open class SpielscreenVM(
	private val spielID: SpielelementID.SpielID,
	private val getSpielUC: GetSpielUC,
	private val getKategorieMitKarteUC: GetKategorieMitKarteUC
): ViewModel(), SpielelementAlsTextDarstellen {

	var spiel by mutableStateOf<Spiel?>(null)
		private set

	var obereKarte by mutableStateOf<List<Kartentext>>(emptyList())
		private set
	var obereKategorie by mutableStateOf<Kategorie?>(null)
		private set
	var untereKarte by mutableStateOf<List<Kartentext>>(emptyList())
		private set
	var untereKategorie by mutableStateOf<Kategorie?>(null)
		private set

	init {
		viewModelScope.launch {
			spiel = getSpielUC(spielID)

			val obereKMK = getKategorieMitKarteUC(spiel.texteProKarte, spiel)
			obereKategorie = obereKMK.first
			obereKarte = obereKMK.second

			val untereKMK = getKategorieMitKarteUC(spiel.texteProKarte, spiel)
			untereKategorie = untereKMK.first
			untereKarte = untereKMK.second
		}
	}

	fun getRandomKartentext(kategorie: Kategorie): String =
		obereKarte
			.first()
			.getBezeichnung(Sprache.OG)
}
