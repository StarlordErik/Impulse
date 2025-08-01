package de.seleri.core.data.implementations

import de.seleri.core.common.ids.LokalisierungID
import de.seleri.core.data.daos.singles.LokalisierungDAO
import de.seleri.core.data.toRoom
import de.seleri.core.domain.mapper.toDomain
import de.seleri.core.domain.model.idEntity.Lokalisierung
import de.seleri.core.domain.repositories.TranslationRepo
import de.seleri.core.domain.repositories.idEntity.LokalisierungRepo

class LokalisierungImpl(private val dao: LokalisierungDAO, private val translationRepo: TranslationRepo):
	LokalisierungRepo {

	override suspend fun new(modell: Lokalisierung): LokalisierungID {
		val entity = modell.toRoom()

		val id = dao.insert(entity)

		return LokalisierungID(id.toInt())
	}

	override suspend fun delete(model: Lokalisierung): Int {
		val entity = model.toRoom()

		return dao.delete(entity)
	}

	override suspend fun get(id: LokalisierungID): Lokalisierung {
		val entity = dao.get(id.value)

		val translationen = translationRepo.getForLokalisierung(id)

		return entity.toDomain(translationen)
	}
}
