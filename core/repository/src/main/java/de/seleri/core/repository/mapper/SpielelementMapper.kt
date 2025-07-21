package de.seleri.core.repository.mapper

import de.seleri.core.common.idTypes.SpielelementID
import de.seleri.core.data.entities.singles.LokalisierungEntity
import de.seleri.core.domain.model.Lokalisierung

interface SpielelementMapper<ID: SpielelementID> {

	fun lokalisierungenToEntities(
		id: ID, lokalisierungen: Collection<Lokalisierung>
	): Collection<LokalisierungEntity> {
		return lokalisierungen.map { lokalisierung ->
			lokalisierungToEntity(id, lokalisierung)
		}
	}

	fun lokalisierungToEntity(
		id: ID, lokalisierung: Lokalisierung
	): LokalisierungEntity
}
