package de.seleri.core.tools.dbsMapper

import de.seleri.core.domain.entities.joins.SpielXKategorieEntity
import de.seleri.core.domain.model.idEntity.spielelemente.spiel.Spiel

fun Spiel.toDatenbankSlice(): DatenbankSlice {
	val lokalisierungSlice = this.lokalisierung.toDatenbankSlice()

	val kategorieSlices = this.bestandteile.map { it.toDatenbankSlice() }

	val spielXkategorieEntity = this.bestandteile.map { SpielXKategorieEntity(this.id, it.id) }
	val spielXkategorieSlice = DatenbankSlice(spielXkategorienEntity = spielXkategorieEntity.toMutableList())

	return DatenbankSlice.merged(
		others = kategorieSlices + lokalisierungSlice + spielXkategorieSlice, spiele = mutableListOf(this.toEntity())
	)
}
