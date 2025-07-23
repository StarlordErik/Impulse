package de.seleri.core.representation.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.seleri.core.common.Sprache
import de.seleri.core.domain.model.spielelemente.spiel.SpielMetaObjekt
import de.seleri.core.domain.useCases.GetAllSpielMetaObjekteUC
import kotlinx.coroutines.launch

open class StartscreenVM(
	private val getAllSpielMetasUseCase: GetAllSpielMetaObjekteUC
): ViewModel() {

	var spielMetaObjekte by mutableStateOf<List<SpielMetaObjekt>>(emptyList())
		private set

	init {
		loadSpielMetaObjekte()
	}

	private fun loadSpielMetaObjekte() {
		viewModelScope.launch {
			spielMetaObjekte = getAllSpielMetasUseCase() as List
		}
	}

	val sprache = Sprache.OG // TODO Sprache muss variabel gesetzt werden

	fun getDarstellungAlsText(spielMetaObjekt: SpielMetaObjekt): String =
		spielMetaObjekt.getBezeichnung(sprache)
}
