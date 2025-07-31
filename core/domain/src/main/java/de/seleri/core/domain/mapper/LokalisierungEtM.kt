package de.seleri.core.domain.mapper

import de.seleri.core.common.Sprache
import de.seleri.core.data.entities.singles.LokalisierungRoom
import de.seleri.core.domain.modell.Lokalisierung
import de.seleri.core.domain.modell.Translation

fun LokalisierungRoom.toDomain(translationen: Map<Sprache, Translation>): Lokalisierung =
	Lokalisierung(
		id = this.id, ogSprache = this.ogSprache, translationen = translationen
	)

fun Lokalisierung.toEntity(): LokalisierungRoom =
	LokalisierungRoom(
		lokalisierungID = this.id.value, ogSprache = this.ogSprache
	)
