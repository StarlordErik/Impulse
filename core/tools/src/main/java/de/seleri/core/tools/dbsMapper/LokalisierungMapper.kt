package de.seleri.core.tools.dbsMapper

import de.seleri.core.domain.modell.idEntity.Lokalisierung

fun Lokalisierung.toDatenbankSlice(): DatenbankSlice {
	val translationSlices = this.translationen.map { it.toDatenbankSlice(lokalisierungsID = this.id) }

	return DatenbankSlice.merged(
		others = translationSlices, lokalisierungen = mutableListOf(this.toEntity())
	)
}

