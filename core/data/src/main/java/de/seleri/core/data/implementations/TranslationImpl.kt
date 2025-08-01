package de.seleri.core.data.implementations

import de.seleri.core.common.Sprache
import de.seleri.core.common.ids.LokalisierungID
import de.seleri.core.domain.model.Translation
import de.seleri.core.domain.repositories.TranslationRepo

class TranslationImpl: TranslationRepo {

	override suspend fun new(
		translation: Translation, lokalisierungID: LokalisierungID, sprache: Sprache
	) {
		TODO("Not yet implemented")
	}

	override suspend fun getForLokalisierung(id: LokalisierungID): Collection<Translation> {
		TODO("Not yet implemented")
	}

	override suspend fun delete(model: Translation): Int {
		TODO("Not yet implemented")
	}

	override suspend fun update(model: Translation): Int {
		TODO("Not yet implemented")
	}
}
