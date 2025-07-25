package de.seleri.core.di.hiltViewModels

import dagger.hilt.android.lifecycle.HiltViewModel
import de.seleri.core.domain.model.idTypes.SpielelementID
import de.seleri.core.domain.useCases.GetSpielUC
import de.seleri.core.representation.viewModels.SpielscreenVM
import javax.inject.Inject

@HiltViewModel
class SpielscreenHVM @Inject constructor(
	spielID: SpielelementID.SpielID, getSpielUC: GetSpielUC, getKategorieMitKarteUC: GetKategorieMitKarteUC
): SpielscreenVM(spielID, getSpielUC, getKategorieMitKarteUC)
