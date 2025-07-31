package de.seleri.core.tools.dbsMapper

import de.seleri.core.domain.modell.spielelemente.Kartentext

fun Kartentext.toDatenbankSlice(): DatenbankSlice {
	val lokalisierungSlice = this.lokalisierung.toDatenbankSlice()

	return DatenbankSlice.merged(
		others = listOf(lokalisierungSlice), kartentexte = mutableListOf(this.toEntity())
	)
}

