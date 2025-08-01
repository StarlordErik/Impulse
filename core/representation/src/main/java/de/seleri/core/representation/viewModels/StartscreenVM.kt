package de.seleri.core.representation.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.seleri.core.common.idTypes.SpielelementID
import de.seleri.core.domain.model.spielelemente.spiel.SpielMetaObjekt
import de.seleri.core.domain.useCases.GetAllSpielMetaObjekteUC
import kotlinx.coroutines.launch

open class StartscreenVM(
	private val getAllSpielMetaObjekteUC: GetAllSpielMetaObjekteUC
): ViewModel(), SpielelementAlsTextDarstellen {

	var spielMetaObjekte by mutableStateOf<List<SpielMetaObjekt>>(emptyList())
		private set
		private get

	init {
		viewModelScope.launch {
			spielMetaObjekte = getAllSpielMetaObjekteUC()
		}
	}

	fun getSpiele() =
		spielMetaObjekte

	fun getID(spiel: SpielMetaObjekt) =
		SpielelementID.SpielID(spiel.id)
}
