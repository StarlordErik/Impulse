package de.seleri.core.domain.useCases

import de.seleri.core.domain.model.idEntity.spielelemente.spiel.SpielMetaDO
import de.seleri.core.domain.repositories.idEntity.spielelemente.SpielRepo

class GetAllSpielMetaObjekteUC(private val spielRepo: SpielRepo) {

	suspend operator fun invoke(): Collection<SpielMetaDO> =
		spielRepo.getAllMetas()
}
