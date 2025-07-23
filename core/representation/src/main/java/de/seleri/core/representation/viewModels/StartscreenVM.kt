package de.seleri.core.representation.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.seleri.core.domain.model.spielelemente.spiel.SpielMetaObjekt
import de.seleri.core.domain.useCases.GetAllSpielMetaObjekteUC
import kotlinx.coroutines.launch

open class StartscreenVM(
	private val getAllSpielMetasUseCase: GetAllSpielMetaObjekteUC
): ViewModel(), SpielelementAlsTextDarstellen {

	var spielMetaObjekte by mutableStateOf<List<SpielMetaObjekt>>(emptyList())
		private set

	init {
		viewModelScope.launch {
			spielMetaObjekte = getAllSpielMetasUseCase()
		}
	}
}
