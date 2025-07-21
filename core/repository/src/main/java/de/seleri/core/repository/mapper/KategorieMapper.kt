package de.seleri.core.repository.mapper

import de.seleri.core.data.entities.singles.KategorieEntity
import de.seleri.core.data.entities.singles.LokalisierungEntity
import de.seleri.core.data.entities.singles.SpielelementBasis
import de.seleri.core.domain.model.DatenbankObjektDaten
import de.seleri.core.domain.model.Lokalisierung
import de.seleri.core.domain.model.spielelemente.Kartentext
import de.seleri.core.domain.model.spielelemente.Kategorie
import de.seleri.core.domain.model.spielelemente.SpielelementDaten
import de.seleri.core.domain.model.spielelemente.sammlungen.SammlungDaten

object KategorieMapper {

	fun lokalisierungenToEntities(
		kategorieID: Int, lokalisierungen: Collection<Lokalisierung>
	): Collection<LokalisierungEntity> {
		return lokalisierungen.map { lokalisierung ->
			lokalisierung.toEntityForKategorie(kategorieID)
		}
	}
}

fun Kategorie.toEntity(): KategorieEntity {
	return KategorieEntity(
		id = id, spielelementBasis = SpielelementBasis(
			selbstErstellt = selbstErstellt, inaktiv = inaktiv, favorisiert = favorisiert, ogSprache = ogSprache
		)
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
