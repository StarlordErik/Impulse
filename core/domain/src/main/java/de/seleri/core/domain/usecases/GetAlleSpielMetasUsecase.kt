package de.seleri.core.domain.usecases

import de.seleri.core.domain.model.spielelemente.spiel.SpielMetaObjekt
import de.seleri.core.domain.repositories.SpielRepo

class GetAlleSpielMetasUsecase(private val spielRepo: SpielRepo) {

	suspend operator fun invoke(): Collection<SpielMetaObjekt> =
		spielRepo.getAllMetas()
}
