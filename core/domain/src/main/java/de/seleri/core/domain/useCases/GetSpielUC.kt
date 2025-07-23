package de.seleri.core.domain.useCases

import de.seleri.core.domain.model.spielelemente.spiel.Spiel
import de.seleri.core.domain.repositories.LokalisierungRepo

class GetSpielUC(private val lokalisierungRepo: LokalisierungRepo) {

	suspend operator fun invoke(spielID: Int): Spiel {
		// val lokalisierungen = lokalisierungRepo.getForSpiel(spielID)
		TODO()
		// return spielEntity.toDomain(lokalisierungen)
	}
}
