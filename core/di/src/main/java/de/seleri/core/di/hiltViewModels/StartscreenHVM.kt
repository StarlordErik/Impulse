package de.seleri.core.di.hiltViewModels

import dagger.hilt.android.lifecycle.HiltViewModel
import de.seleri.core.domain.useCases.GetAllSpielMetaObjekteUC
import de.seleri.core.representation.viewModels.StartscreenVM
import javax.inject.Inject

@HiltViewModel
class StartscreenHVM @Inject constructor(
	getAllSpielMetasUseCase: GetAllSpielMetaObjekteUC
): StartscreenVM(getAllSpielMetasUseCase)
