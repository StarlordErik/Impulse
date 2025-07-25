package de.seleri.core.di.hiltViewModels

import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.components.SingletonComponent
import de.seleri.core.common.idTypes.SpielelementID
import de.seleri.core.domain.useCases.GetKategorieMitKarteUC
import de.seleri.core.domain.useCases.GetSpielUC
import de.seleri.core.representation.viewModels.SpielscreenVM

@HiltViewModel
class SpielscreenHVM @AssistedInject constructor(
	@Assisted
	spielID: SpielelementID.SpielID,

	getSpielUC: GetSpielUC, getKategorieMitKarteUC: GetKategorieMitKarteUC
): SpielscreenVM(spielID, getSpielUC, getKategorieMitKarteUC) {

	@AssistedFactory
	interface Factory {

		fun create(spielID: SpielelementID.SpielID): SpielscreenHVM
	}
}

@EntryPoint
@InstallIn(SingletonComponent::class)
interface SpielscreenHVMFactoryProvider {

	fun spielscreenHVMFactory(): SpielscreenHVM.Factory
}
