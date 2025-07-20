package de.seleri.core.domain.repositories

import de.seleri.core.domain.model.Lokalisierung

interface LokalisierungRepo {

	suspend fun upsert(lokalisierung: Lokalisierung)
	suspend fun delete(lokalisierung: Lokalisierung)

	suspend fun getById(id: Int): Lokalisierung?
	suspend fun getBearbeitete(): List<Lokalisierung>
}
