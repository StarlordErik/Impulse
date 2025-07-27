package de.seleri.core.data.mapper

import de.seleri.core.common.idTypes.SpielelementID
import de.seleri.core.data.entities.singles.LokalisierungEntity
import de.seleri.core.data.entities.singles.spielelemente.KartentextEntity
import de.seleri.core.domain.modell.EntityModellDaten
import de.seleri.core.domain.modell.Lokalisierung
import de.seleri.core.domain.modell.spielelemente.Kartentext
import de.seleri.core.domain.modell.spielelemente.SpielelementDaten

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
			EntityModellDaten(id = id),
			selbstErstellt = spielelementBasis.selbstErstellt,
			inaktiv = spielelementBasis.inaktiv,
			favorisiert = spielelementBasis.favorisiert,
			ogSprache = spielelementBasis.ogSprache,
			lokalisierungen = lokalisierungen
		), gesehen = gesehen, besprochen = besprochen
	)
