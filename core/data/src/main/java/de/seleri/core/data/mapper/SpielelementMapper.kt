package de.seleri.core.data.mapper

import de.seleri.core.common.idTypes.SpielelementID
import de.seleri.core.data.entities.singles.LokalisierungEntityRoom
import de.seleri.core.data.entities.singles.spielelemente.SpielelementBasis
import de.seleri.core.domain.modell.Lokalisierung
import de.seleri.core.domain.modell.spielelemente.Spielelement

interface SpielelementMapper<ID: SpielelementID> {

	fun lokalisierungenToEntities(
		id: ID, lokalisierungen: Collection<Lokalisierung>
	): Collection<LokalisierungEntityRoom> =
		lokalisierungen.map { lokalisierung ->
			lokalisierungToEntity(id, lokalisierung)
		}


	fun lokalisierungToEntity(
		id: ID, lokalisierung: Lokalisierung
	): LokalisierungEntityRoom
}

fun domainToSpielelmentBasis(spielelement: Spielelement): SpielelementBasis =
	SpielelementBasis(
		selbstErstellt = spielelement.selbstErstellt,
		inaktiv = spielelement.inaktiv,
		favorisiert = spielelement.favorisiert,
		ogSprache = spielelement.ogSprache
	)
