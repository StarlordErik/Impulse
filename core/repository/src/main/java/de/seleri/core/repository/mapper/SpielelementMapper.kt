package de.seleri.core.repository.mapper

import de.seleri.core.data.entities.singles.spielelemente.LokalisierungEntity
import de.seleri.core.data.entities.singles.spielelemente.SpielelementBasis
import de.seleri.core.domain.model.Lokalisierung
import de.seleri.core.domain.model.idTypes.SpielelementID
import de.seleri.core.domain.model.spielelemente.Spielelement

interface SpielelementMapper<ID: SpielelementID> {

	fun lokalisierungenToEntities(
		id: ID, lokalisierungen: Collection<Lokalisierung>
	): Collection<LokalisierungEntity> =
		lokalisierungen.map { lokalisierung ->
			lokalisierungToEntity(id, lokalisierung)
		}


	fun lokalisierungToEntity(
		id: ID, lokalisierung: Lokalisierung
	): LokalisierungEntity
}

fun domainToSpielelmentBasis(spielelement: Spielelement): SpielelementBasis =
	SpielelementBasis(
		selbstErstellt = spielelement.selbstErstellt,
		inaktiv = spielelement.inaktiv,
		favorisiert = spielelement.favorisiert,
		ogSprache = spielelement.ogSprache
	)
