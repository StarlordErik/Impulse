package de.seleri.core.representation.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.seleri.core.domain.model.idTypes.SpielelementID
import de.seleri.core.domain.model.spielelemente.Kategorie
import de.seleri.core.domain.model.spielelemente.spiel.Spiel
import de.seleri.core.domain.useCases.GetSpielUC
import kotlinx.coroutines.launch

open class SpielscreenVM(
	private val spielID: SpielelementID.SpielID, private val getSpielUC: GetSpielUC
): ViewModel(), SpielelementAlsTextDarstellen {

	var spiel by mutableStateOf<Spiel?>(null)
		private set

	init {
		viewModelScope.launch {
			spiel = getSpielUC(spielID)
		}
	}

	fun getRandomKartentext(kategorie: Kategorie): String =
		TODO()
}
