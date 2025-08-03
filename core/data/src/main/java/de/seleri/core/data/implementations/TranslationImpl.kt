package de.seleri.core.data.implementations

import de.seleri.core.common.Sprache
import de.seleri.core.common.ids.LokalisierungID
import de.seleri.core.data.daos.compositePk.TranslationDAO
import de.seleri.core.data.toRoom
import de.seleri.core.domain.mapper.toDomain
import de.seleri.core.domain.model.Translation
import de.seleri.core.domain.repositories.TranslationRepo

class TranslationImpl(private val dao: TranslationDAO): TranslationRepo {

	override suspend fun new(
		lokalisierungID: LokalisierungID, translation: Translation, sprache: Sprache
	) {
		val entity = translation.toRoom(lokalisierungID, sprache)

		dao.insert(entity)
	}

	override suspend fun delete(
		translation: Translation, lokalisierungID: LokalisierungID, sprache: Sprache
	): Int {
		val entity = translation.toRoom(lokalisierungID, sprache)

		return dao.delete(entity)
	}

	override suspend fun update(
		translation: Translation, lokalisierungID: LokalisierungID, sprache: Sprache
	): Int {
		val entity = translation.toRoom(lokalisierungID, sprache)

		return dao.update(entity)
	}

	override suspend fun findLokalisierungIDByBezeichnung(bezeichnung: String): LokalisierungID? {
		val ggfVorhandeneEntity = dao.findByBezeichnung(bezeichnung)

		return ggfVorhandeneEntity?.lokalisierungID
	}

	override suspend fun getForLokalisierung(id: LokalisierungID): Map<Sprache, Translation> {
		val entities = dao.getForLokalisierung(id)

		return entities.associate {
			it.sprache to it.toDomain()
		}
	}
}
