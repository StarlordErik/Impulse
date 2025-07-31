package de.seleri.core.data.mapper

import de.seleri.core.data.entities.singles.LokalisierungRoom
import de.seleri.core.domain.modell.Lokalisierung

fun Lokalisierung.toRoom(): LokalisierungRoom =
	LokalisierungRoom(
		lokalisierungID = this.id.value, ogSprache = this.ogSprache
	)
