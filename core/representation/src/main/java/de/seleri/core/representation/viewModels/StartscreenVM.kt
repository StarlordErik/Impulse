package de.seleri.core.representation.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.seleri.core.domain.model.idEntity.spielelemente.spiel.SpielMetaDO
import de.seleri.core.domain.useCases.GetAllSpielMetaDOsUC
import kotlinx.coroutines.launch

open class StartscreenVM(
	private val getAllSpielMetaDOsUC: GetAllSpielMetaDOsUC
): ViewModel(), SpielelementAlsTextDarstellen {

	var spielMetaObjekte by mutableStateOf<List<SpielMetaDO>>(emptyList())
		private set
		private get

	init {
		viewModelScope.launch {
			spielMetaObjekte = getAllSpielMetaDOsUC()
		}
	}

	fun getSpiele() =
		spielMetaObjekte

	fun getID(spiel: SpielMetaDO) =
		spiel.id
}
