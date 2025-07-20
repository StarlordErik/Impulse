package de.seleri.core.repository.mapper

import de.seleri.core.data.entities.singles.KartentextEntity
import de.seleri.core.data.entities.singles.SpielelementBasis
import de.seleri.core.domain.model.Lokalisierung
import de.seleri.core.domain.model.spielelemente.Kartentext
import de.seleri.core.domain.model.spielelemente.SpielelementDaten

fun KartentextEntity.toKartentext(lokalisierungen: Collection<Lokalisierung>): Kartentext {
	val spielelementDaten = object: SpielelementDaten(
		id = this.id,
		lokalisierungen = lokalisierungen,
		ogSprache = spielelementBasis.ogSprache,
		selbstErstellt = spielelementBasis.selbstErstellt,
		inaktiv = spielelementBasis.inaktiv,
		favorisiert = spielelementBasis.favorisiert
	) {}

	return Kartentext(
		spielelementDaten = spielelementDaten, gesehen = this.gesehen, besprochen = this.besprochen
	)
}

fun Kartentext.toEntity(): KartentextEntity {
	return KartentextEntity(
		id = this.id, spielelementBasis = SpielelementBasis(
			ogSprache = this.spielelementDaten.ogSprache,
			selbstErstellt = this.spielelementDaten.selbstErstellt,
			inaktiv = this.spielelementDaten.inaktiv,
			favorisiert = this.spielelementDaten.favorisiert
		), gesehen = this.gesehen, besprochen = this.besprochen
	)
}
