package de.seleri.core.repository.implementations

import de.seleri.core.data.daos.LokalisierungDao
import de.seleri.core.domain.model.Lokalisierung
import de.seleri.core.domain.repositories.LokalisierungRepo
import de.seleri.core.repository.mapper.toDomain
import de.seleri.core.repository.mapper.toEntity

class LokalisierungImpl(
	private val dao: LokalisierungDao
): LokalisierungRepo {

	override suspend fun upsert(lokalisierung: Lokalisierung) {
		dao.upsert(lokalisierung.toEntity())
	}

	override suspend fun delete(lokalisierung: Lokalisierung) {
		dao.delete(lokalisierung.toEntity())
	}


	override suspend fun getById(id: Int): Lokalisierung? {
		return dao
			.getById(id)
			?.toDomain()
	}

	override suspend fun getBearbeitete(): List<Lokalisierung> {
		return dao
			.getBearbeitete()
			.map { it.toDomain() }
	}
}
