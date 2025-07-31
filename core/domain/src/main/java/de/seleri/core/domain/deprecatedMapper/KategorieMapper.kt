package de.seleri.core.domain.deprecatedMapper

import de.seleri.core.domain.entities.joins.KategorieXKartentextEntity
import de.seleri.core.domain.entities.singles.spielelemente.KategorieEntity
import de.seleri.core.domain.modell.Lokalisierung
import de.seleri.core.domain.modell.spielelemente.Kartentext
import de.seleri.core.domain.modell.spielelemente.Kategorie
import de.seleri.core.domain.modell.spielelemente.SpielelementDO

fun KategorieEntity.toModell(lokalisierung: Lokalisierung, kartentexte: Collection<Kartentext>): Kategorie =
	Kategorie(
		id = this.id, spielelementDaten = SpielelementDO(
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

	val kategorieXkartentextEntity = this.bestandteile.map { KategorieXKartentextEntity(this.id, it.id) }
	val kategorieXkartentextSlice =
		DatenbankSlice(kategorieXkartentexteEntity = kategorieXkartentextEntity.toMutableList())

	return DatenbankSlice.merged(
		others = kartentextSlices + lokalisierungSlice + kategorieXkartentextSlice,
		kategorien = mutableListOf(this.toEntity()),
	)
}
