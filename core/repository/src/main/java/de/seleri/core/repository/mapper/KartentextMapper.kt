package de.seleri.core.repository.mapper

import de.seleri.core.data.entities.singles.Basis
import de.seleri.core.data.entities.singles.KartentextEntity
import de.seleri.core.domain.model.Lokalisierung
import de.seleri.core.domain.model.SpielelementDaten
import de.seleri.core.domain.model.spielelemente.Kartentext

fun KartentextEntity.toKartentext(lokalisierungen: Collection<Lokalisierung>): Kartentext {
	val spielelementDaten = object: SpielelementDaten(
		id = this.id,
		lokalisierungen = lokalisierungen,
		ogSprache = basis.ogSprache,
		selbstErstellt = basis.selbstErstellt,
		inaktiv = basis.inaktiv,
		favorisiert = basis.favorisiert
	) {}

	return Kartentext(
		spielelementDaten = spielelementDaten, gesehen = this.gesehen, besprochen = this.besprochen
	)
}

fun Kartentext.toEntity(): KartentextEntity {
	return KartentextEntity(
		id = this.id, basis = Basis(
			ogSprache = this.spielelementDaten.ogSprache,
			selbstErstellt = this.spielelementDaten.selbstErstellt,
			inaktiv = this.spielelementDaten.inaktiv,
			favorisiert = this.spielelementDaten.favorisiert
		), gesehen = this.gesehen, besprochen = this.besprochen
	)
}
