package de.seleri.core.domain.mapper

import de.seleri.core.domain.entities.singles.spielelemente.KartentextEntity
import de.seleri.core.domain.modell.Lokalisierung
import de.seleri.core.domain.modell.spielelemente.Kartentext
import de.seleri.core.domain.modell.spielelemente.SpielelementDaten

fun KartentextEntity.toModell(lokalisierung: Lokalisierung): Kartentext =
	Kartentext(
		id = this.id, spielelementDaten = SpielelementDaten(
			lokalisierung = lokalisierung,
			selbstErstellt = this.selbstErstellt,
			inaktiv = this.inaktiv,
			favorisiert = this.favorisiert
		), gesehen = this.gesehen, besprochen = this.besprochen
	)

fun Kartentext.toEntity(): KartentextEntity =
	KartentextEntity(
		id = this.id,
		lokalisierungID = this.lokalisierung.id,
		selbstErstellt = this.selbstErstellt,
		inaktiv = this.inaktiv,
		favorisiert = this.favorisiert,
		gesehen = this.gesehen,
		besprochen = this.besprochen
	)

fun Kartentext.toDatenbankSlice(): DatenbankSlice {
	val lokalisierungSlice = this.lokalisierung.toDatenbankSlice()

	return DatenbankSlice.merged(
		others = listOf(lokalisierungSlice), kartentexte = mutableListOf(this.toEntity())
	)
}

