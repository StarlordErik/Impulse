package de.seleri.core.repository.mapper

import de.seleri.core.data.entities.singles.KartentextEntity
import de.seleri.core.data.entities.singles.LokalisierungEntity
import de.seleri.core.data.entities.singles.SpielelementBasis
import de.seleri.core.domain.model.DatenbankObjektDaten
import de.seleri.core.domain.model.Lokalisierung
import de.seleri.core.domain.model.spielelemente.Kartentext
import de.seleri.core.domain.model.spielelemente.SpielelementDaten

object KartentextMapper {

	fun lokalisierungenToEntities(
		kartentextID: Int, lokalisierungen: Collection<Lokalisierung>
	): Collection<LokalisierungEntity> {
		return lokalisierungen.map { lokalisierung ->
			lokalisierung.toEntityForKartentext(kartentextID)
		}
	}
}

fun Kartentext.toEntity(): KartentextEntity {
	return KartentextEntity(
		id = id, spielelementBasis = SpielelementBasis(
			selbstErstellt = selbstErstellt, inaktiv = inaktiv, favorisiert = favorisiert, ogSprache = ogSprache
		), gesehen = gesehen, besprochen = besprochen
	)
}

fun KartentextEntity.toDomain(
	lokalisierungen: Collection<Lokalisierung>
): Kartentext {
	return Kartentext(
		spielelementDaten = SpielelementDaten(
			DatenbankObjektDaten(id = id),
			selbstErstellt = spielelementBasis.selbstErstellt,
			inaktiv = spielelementBasis.inaktiv,
			favorisiert = spielelementBasis.favorisiert,
			ogSprache = spielelementBasis.ogSprache,
			lokalisierungen = lokalisierungen
		), gesehen = gesehen, besprochen = besprochen
	)
}
