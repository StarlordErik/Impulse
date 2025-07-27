package de.seleri.core.domain.mapper

import de.seleri.core.domain.entities.singles.LokalisierungEntity
import de.seleri.core.domain.modell.Lokalisierung
import de.seleri.core.domain.modell.Translation

fun LokalisierungEntity.toModell(translationen: Collection<Translation>): Lokalisierung =
	Lokalisierung(
		id = this.id, ogSprache = this.ogSprache, translationen = translationen
	)

fun Lokalisierung.toEntity(): LokalisierungEntity =
	LokalisierungEntity(
		id = this.id, ogSprache = this.ogSprache
	)

fun Lokalisierung.toDatenbankSlice(): DatenbankSlice {
	val translationSlices = this.translationen.map { it.toDatenbankSlice(lokalisierungsID = this.id) }

	return DatenbankSlice.merged(
		others = translationSlices, lokalisierungen = mutableListOf(this.toEntity())
	)
}

