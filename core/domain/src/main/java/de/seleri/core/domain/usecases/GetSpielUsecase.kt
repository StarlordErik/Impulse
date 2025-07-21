package de.seleri.core.domain.usecases

import de.seleri.core.domain.model.spielelemente.spiel.Spiel
import de.seleri.core.domain.repositories.LokalisierungRepo

class GetSpielUsecase(private val lokalisierungRepo: LokalisierungRepo) {

	suspend operator fun invoke(spielID: Int): Spiel {
		val lokalisierungen = lokalisierungRepo.getForSpiel(spielID)
		TODO()
		// return spielEntity.toDomain(lokalisierungen)
	}
}
