package de.seleri.core.domain.useCases

import de.seleri.core.domain.modell.spielelemente.spiel.SpielMetaDO
import de.seleri.core.domain.repositories.SpielRepo

class GetAllSpielMetaObjekteUC(private val spielRepo: SpielRepo) {

	suspend operator fun invoke(): Collection<SpielMetaDO> =
		spielRepo.getAllMetas()
}
