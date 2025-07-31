package de.seleri.core.tools.mapper

import de.seleri.core.domain.modell.Lokalisierung
import de.seleri.core.tools.dtos.singles.LokalisierungDTO

fun Lokalisierung.toEntity(): LokalisierungDTO =
	LokalisierungDTO(
		lokalisierungID = this.id.value, ogSprache = this.ogSprache
	)
