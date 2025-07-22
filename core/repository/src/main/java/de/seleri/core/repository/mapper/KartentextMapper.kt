package de.seleri.core.repository.mapper

import de.seleri.core.data.entities.singles.spielelemente.KartentextEntity
import de.seleri.core.data.entities.singles.spielelemente.LokalisierungEntity
import de.seleri.core.domain.model.DatenbankObjektDaten
import de.seleri.core.domain.model.Lokalisierung
import de.seleri.core.domain.model.idTypes.SpielelementID
import de.seleri.core.domain.model.spielelemente.Kartentext
import de.seleri.core.domain.model.spielelemente.SpielelementDaten

object KartentextMapper: SpielelementMapper<SpielelementID.KartentextID> {

	override fun lokalisierungToEntity(
		id: SpielelementID.KartentextID, lokalisierung: Lokalisierung
	): LokalisierungEntity =
		lokalisierung.toEntityForKartentext(id)
}


fun Kartentext.toEntity(): KartentextEntity =
	KartentextEntity(
		id = id, spielelementBasis = domainToSpielelmentBasis(this), gesehen = gesehen, besprochen = besprochen
	)


fun KartentextEntity.toDomain(
	lokalisierungen: Collection<Lokalisierung>
): Kartentext =
	Kartentext(
		spielelementDaten = SpielelementDaten(
			DatenbankObjektDaten(id = id),
			selbstErstellt = spielelementBasis.selbstErstellt,
			inaktiv = spielelementBasis.inaktiv,
			favorisiert = spielelementBasis.favorisiert,
			ogSprache = spielelementBasis.ogSprache,
			lokalisierungen = lokalisierungen
		), gesehen = gesehen, besprochen = besprochen
	)
