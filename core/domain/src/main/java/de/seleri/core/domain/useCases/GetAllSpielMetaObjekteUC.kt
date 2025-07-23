package de.seleri.core.domain.useCases

import de.seleri.core.domain.model.spielelemente.spiel.SpielMetaObjekt
import de.seleri.core.domain.repositories.SpielRepo

class GetAllSpielMetaObjekteUC(private val spielRepo: SpielRepo) {

	suspend operator fun invoke(): Collection<SpielMetaObjekt> =
		spielRepo.getAllMetaObjekte()
}
