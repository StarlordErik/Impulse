package de.seleri.core.domain.useCases.create

import de.seleri.core.common.Sprache
import de.seleri.core.common.ids.LokalisierungID
import de.seleri.core.domain.model.Translation
import de.seleri.core.domain.model.idEntity.Lokalisierung
import de.seleri.core.domain.repositories.idEntity.LokalisierungRepo

class CreateLokalisierungUC(private val lokalisierungRepo: LokalisierungRepo) {

	suspend operator fun invoke(ogSprache: Sprache, translationen: Map<Sprache, Translation>): Lokalisierung {
		val id = LokalisierungID(0)
		val lokalisierung = Lokalisierung(
			id,
			ogSprache = ogSprache,
			translationen = translationen,
		)

		lokalisierungRepo.new(lokalisierung)

		return lokalisierung
	}
}
