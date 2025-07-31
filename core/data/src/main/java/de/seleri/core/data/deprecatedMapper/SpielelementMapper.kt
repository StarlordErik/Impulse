package de.seleri.core.data.deprecatedMapper/*
import de.seleri.core.common.idTypes.SpielelementID
import de.seleri.core.data.entities.singles.LokalisierungRoom
import de.seleri.core.data.entities.singles.spielelemente.SpielelementDatenRoom
import de.seleri.core.domain.modell.Lokalisierung
import de.seleri.core.domain.modell.spielelemente.Spielelement

interface SpielelementMapper<ID: SpielelementID> {

	fun lokalisierungenToEntities(
		id: ID, lokalisierungen: Collection<Lokalisierung>
	): Collection<LokalisierungRoom> =
		lokalisierungen.map { lokalisierung ->
			lokalisierungToEntity(id, lokalisierung)
		}


	fun lokalisierungToEntity(
		id: ID, lokalisierung: Lokalisierung
	): LokalisierungRoom
}

fun domainToSpielelmentBasis(spielelement: Spielelement): SpielelementDatenRoom =
	SpielelementDatenRoom(
		selbstErstellt = spielelement.selbstErstellt,
		inaktiv = spielelement.inaktiv,
		favorisiert = spielelement.favorisiert,
		ogSprache = spielelement.ogSprache
	)


 */
