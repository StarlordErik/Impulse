package de.seleri.core.di.hiltViewModels

import dagger.hilt.android.lifecycle.HiltViewModel
import de.seleri.core.domain.useCases.GetAllSpielMetaDOsUC
import de.seleri.core.representation.viewModels.StartscreenVM
import javax.inject.Inject

@HiltViewModel
class StartscreenHVM @Inject constructor(
	getAllSpielMetaDOsUC: GetAllSpielMetaDOsUC
): StartscreenVM(getAllSpielMetaDOsUC)
