package de.seleri.core.domain.mapper

import de.seleri.core.domain.entities.joins.KategorieXkartentext
import de.seleri.core.domain.entities.singles.spielelemente.KategorieEntity
import de.seleri.core.domain.modell.Lokalisierung
import de.seleri.core.domain.modell.spielelemente.Kartentext
import de.seleri.core.domain.modell.spielelemente.Kategorie
import de.seleri.core.domain.modell.spielelemente.SpielelementDaten

fun KategorieEntity.toModell(lokalisierung: Lokalisierung, kartentexte: Collection<Kartentext>): Kategorie =
	Kategorie(
		id = this.id, spielelementDaten = SpielelementDaten(
			lokalisierung = lokalisierung,
			selbstErstellt = this.selbstErstellt,
			inaktiv = this.inaktiv,
			favorisiert = this.favorisiert
		), bestandteile = kartentexte
	)

fun Kategorie.toEntity(): KategorieEntity =
	KategorieEntity(
		id = this.id,
		lokalisierungID = this.lokalisierung.id,
		selbstErstellt = this.selbstErstellt,
		inaktiv = this.inaktiv,
		favorisiert = this.favorisiert
	)

fun Kategorie.toDatenbankSlice(): DatenbankSlice {
	val kartentextSlices = this.bestandteile.map { it.toDatenbankSlice() }

	val lokalisierungSlice = this.lokalisierung.toDatenbankSlice()

	val kategorieXkartentext = this.bestandteile.map { KategorieXkartentext(this.id, it.id) }
	val kategorieXkartentextSlice = DatenbankSlice(kategorieXkartentexte = kategorieXkartentext.toMutableList())

	return DatenbankSlice.merged(
		others = kartentextSlices + lokalisierungSlice + kategorieXkartentextSlice,
		kategorien = mutableListOf(this.toEntity()),
	)
}
