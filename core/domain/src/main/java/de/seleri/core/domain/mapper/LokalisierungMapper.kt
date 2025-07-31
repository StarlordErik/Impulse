package de.seleri.core.domain.mapper

import de.seleri.core.common.Sprache
import de.seleri.core.common.entities.singles.LokalisierungEntity
import de.seleri.core.domain.modell.Lokalisierung
import de.seleri.core.domain.modell.Translation

fun LokalisierungEntity.toDomain(translationen: Map<Sprache, Translation>): Lokalisierung =
	Lokalisierung(
		id = this.id, ogSprache = this.ogSprache, translationen = translationen
	)

