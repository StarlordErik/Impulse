package de.seleri.core.data.deprecatedMapper/*
import de.seleri.core.common.idTypes.SpielelementID
import de.seleri.core.data.entities.singles.LokalisierungRoom
import de.seleri.core.data.entities.singles.spielelemente.KategorieRoom
import de.seleri.core.domain.modell.EntityModellDaten
import de.seleri.core.domain.modell.Lokalisierung
import de.seleri.core.domain.modell.spielelemente.Kartentext
import de.seleri.core.domain.modell.spielelemente.Kategorie
import de.seleri.core.domain.modell.spielelemente.SpielelementDaten

object KategorieMapper: SpielelementMapper<SpielelementID.KategorieID> {

	override fun lokalisierungToEntity(
		id: SpielelementID.KategorieID, lokalisierung: Lokalisierung
	): LokalisierungRoom {
		return lokalisierung.toEntityForKategorie(id)
	}
}

fun Kategorie.toEntity(): KategorieRoom {
	return KategorieRoom(
		id = id, spielelementDatenRoom = domainToSpielelmentBasis(this)
	)
	// TODO Hier z.B. muss auch KategorieXkartentexte gemappt werden
}

fun KategorieRoom.toDomain(
	lokalisierungen: Collection<Lokalisierung>, kartentexte: Collection<Kartentext>
): Kategorie {
	return Kategorie(
		spielelementDaten = SpielelementDaten(
			EntityModellDaten(id = id),
			selbstErstellt = spielelementDatenRoom.selbstErstellt,
			inaktiv = spielelementDatenRoom.inaktiv,
			favorisiert = spielelementDatenRoom.favorisiert,
			ogSprache = spielelementDatenRoom.ogSprache,
			lokalisierung = lokalisierungen
		), bestandteile = kartentexte
	)
}


 */
