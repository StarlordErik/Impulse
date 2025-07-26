package de.seleri.core.di.hiltViewModels

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import de.seleri.core.common.idTypes.SpielelementID
import de.seleri.core.domain.useCases.GetKarteUC
import de.seleri.core.domain.useCases.GetSpielUC
import de.seleri.core.representation.viewModels.SpielscreenVM
import javax.inject.Inject

@HiltViewModel
class SpielscreenHVM @Inject constructor(
	savedStateHandle: SavedStateHandle, getSpielUC: GetSpielUC, getKarteUC: GetKarteUC
): SpielscreenVM(
	spielID = SpielelementID.SpielID(savedStateHandle.get<Int>("spielID")!!),
	getSpielUC = getSpielUC, getKarteUC = getKarteUC
)
