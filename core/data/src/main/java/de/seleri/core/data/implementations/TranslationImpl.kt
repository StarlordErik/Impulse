package de.seleri.core.data.implementations

import de.seleri.core.common.ids.LokalisierungID
import de.seleri.core.data.toRoom
import de.seleri.core.domain.modell.Translation
import de.seleri.core.domain.repositories.TranslationRepo

class TranslationImpl: TranslationRepo {

	override suspend fun new(entity: Translation): Int {

		entity.toRoom(
			lokalisierungID = TODO(), sprache = TODO()
		)

		TODO("Not yet implemented")
	}

	override suspend fun delete(entity: Translation): Int {
	}

	override suspend fun update(entity: Translation): Int {
		TODO("Not yet implemented")
	}

	override suspend fun getForLokalisierung(id: LokalisierungID): Collection<Translation> {
		TODO("Not yet implemented")
	}
}
