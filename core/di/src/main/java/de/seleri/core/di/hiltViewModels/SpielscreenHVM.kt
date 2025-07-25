package de.seleri.core.di.hiltViewModels

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import de.seleri.core.common.idTypes.SpielelementID
import de.seleri.core.domain.useCases.GetKategorieMitKarteUC
import de.seleri.core.domain.useCases.GetSpielUC
import de.seleri.core.representation.viewModels.SpielscreenVM
import javax.inject.Inject

@HiltViewModel
class SpielscreenHVM @Inject constructor(
	savedStateHandle: SavedStateHandle, getSpielUC: GetSpielUC, getKategorieMitKarteUC: GetKategorieMitKarteUC
): SpielscreenVM(
	spielID = SpielelementID.SpielID(savedStateHandle.get<Int>("spielID")!!),
	getSpielUC = getSpielUC,
	getKategorieMitKarteUC = getKategorieMitKarteUC
)
