package de.seleri.core.data.deprecatedMapper/*
import de.seleri.core.common.idTypes.SpielelementID
import de.seleri.core.data.entities.singles.LokalisierungRoom
import de.seleri.core.data.entities.singles.spielelemente.KartentextRoom
import de.seleri.core.domain.modell.EntityModellDaten
import de.seleri.core.domain.modell.Lokalisierung
import de.seleri.core.domain.modell.spielelemente.Kartentext
import de.seleri.core.domain.modell.spielelemente.SpielelementDaten

object KartentextMapper: SpielelementMapper<SpielelementID.KartentextID> {

	override fun lokalisierungToEntity(
		id: SpielelementID.KartentextID, lokalisierung: Lokalisierung
	): LokalisierungRoom =
		lokalisierung.toEntityForKartentext(id)
}


fun Kartentext.toEntity(): KartentextRoom =
	KartentextRoom(
		id = id, spielelementDatenRoom = domainToSpielelmentBasis(this), gesehen = gesehen, besprochen = besprochen
	)


fun KartentextRoom.toDomain(
	lokalisierungen: Collection<Lokalisierung>
): Kartentext =
	Kartentext(
		spielelementDaten = SpielelementDaten(
			EntityModellDaten(id = id),
			selbstErstellt = spielelementDatenRoom.selbstErstellt,
			inaktiv = spielelementDatenRoom.inaktiv,
			favorisiert = spielelementDatenRoom.favorisiert,
			ogSprache = spielelementDatenRoom.ogSprache,
			lokalisierung = lokalisierungen
		), gesehen = gesehen, besprochen = besprochen
	)


 */
