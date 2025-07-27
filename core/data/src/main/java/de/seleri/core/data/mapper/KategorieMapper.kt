package de.seleri.core.data.mapper

import de.seleri.core.common.idTypes.SpielelementID
import de.seleri.core.data.entities.singles.LokalisierungEntity
import de.seleri.core.data.entities.singles.spielelemente.KategorieEntity
import de.seleri.core.domain.model.DatenbankObjektDaten
import de.seleri.core.domain.model.Lokalisierung
import de.seleri.core.domain.model.spielelemente.Kartentext
import de.seleri.core.domain.model.spielelemente.Kategorie
import de.seleri.core.domain.model.spielelemente.SpielelementDaten

object KategorieMapper: SpielelementMapper<SpielelementID.KategorieID> {

	override fun lokalisierungToEntity(
		id: SpielelementID.KategorieID, lokalisierung: Lokalisierung
	): LokalisierungEntity {
		return lokalisierung.toEntityForKategorie(id)
	}
}

fun Kategorie.toEntity(): KategorieEntity {
	return KategorieEntity(
		id = id, spielelementBasis = domainToSpielelmentBasis(this)
	)
	// TODO Hier z.B. muss auch KategorieXkartentexte gemappt werden
}

fun KategorieEntity.toDomain(
	lokalisierungen: Collection<Lokalisierung>, kartentexte: Collection<Kartentext>
): Kategorie {
	return Kategorie(
		spielelementDaten = SpielelementDaten(
			DatenbankObjektDaten(id = id),
			selbstErstellt = spielelementBasis.selbstErstellt,
			inaktiv = spielelementBasis.inaktiv,
			favorisiert = spielelementBasis.favorisiert,
			ogSprache = spielelementBasis.ogSprache,
			lokalisierungen = lokalisierungen
		), bestandteile = kartentexte
	)
}
