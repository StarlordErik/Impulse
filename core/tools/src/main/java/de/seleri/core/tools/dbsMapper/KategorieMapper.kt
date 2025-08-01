package de.seleri.core.tools.dbsMapper

import de.seleri.core.domain.entities.joins.KategorieXKartentextEntity
import de.seleri.core.domain.modell.idEntity.spielelemente.Kategorie


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
