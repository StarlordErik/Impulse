package de.seleri.core.data.mapper

import de.seleri.core.common.idTypes.SpielelementID
import de.seleri.core.data.entities.singles.LokalisierungEntityRoom
import de.seleri.core.data.entities.singles.spielelemente.KategorieEntityRoom
import de.seleri.core.domain.modell.EntityModellDaten
import de.seleri.core.domain.modell.Lokalisierung
import de.seleri.core.domain.modell.spielelemente.Kartentext
import de.seleri.core.domain.modell.spielelemente.Kategorie
import de.seleri.core.domain.modell.spielelemente.SpielelementDaten

object KategorieMapper: SpielelementMapper<SpielelementID.KategorieID> {

	override fun lokalisierungToEntity(
		id: SpielelementID.KategorieID, lokalisierung: Lokalisierung
	): LokalisierungEntityRoom {
		return lokalisierung.toEntityForKategorie(id)
	}
}

fun Kategorie.toEntity(): KategorieEntityRoom {
	return KategorieEntityRoom(
		id = id, spielelementBasis = domainToSpielelmentBasis(this)
	)
	// TODO Hier z.B. muss auch KategorieXkartentexte gemappt werden
}

fun KategorieEntityRoom.toDomain(
	lokalisierungen: Collection<Lokalisierung>, kartentexte: Collection<Kartentext>
): Kategorie {
	return Kategorie(
		spielelementDaten = SpielelementDaten(
			EntityModellDaten(id = id),
			selbstErstellt = spielelementBasis.selbstErstellt,
			inaktiv = spielelementBasis.inaktiv,
			favorisiert = spielelementBasis.favorisiert,
			ogSprache = spielelementBasis.ogSprache, lokalisierung = lokalisierungen
		), bestandteile = kartentexte
	)
}
