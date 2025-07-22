package de.seleri.core.repository.mapper

import de.seleri.core.common.idTypes.SpielelementID
import de.seleri.core.data.entities.singles.spielelemente.KategorieEntity
import de.seleri.core.data.entities.singles.spielelemente.LokalisierungEntity
import de.seleri.core.domain.model.DatenbankObjektDaten
import de.seleri.core.domain.model.Lokalisierung
import de.seleri.core.domain.model.spielelemente.Kartentext
import de.seleri.core.domain.model.spielelemente.Kategorie
import de.seleri.core.domain.model.spielelemente.SpielelementDaten
import de.seleri.core.domain.model.spielelemente.sammlungen.SammlungDaten

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
		), sammlungDaten = SammlungDaten(kartentexte)
	)
}
