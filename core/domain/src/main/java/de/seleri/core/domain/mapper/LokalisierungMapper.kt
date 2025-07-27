package de.seleri.core.domain.mapper

import de.seleri.core.domain.entities.singles.LokalisierungEntity
import de.seleri.core.domain.modell.EntityModellDaten
import de.seleri.core.domain.modell.Lokalisierung
import de.seleri.core.domain.modell.Translation

fun LokalisierungEntity.toModell(translationen: Collection<Translation>): Lokalisierung =
	Lokalisierung(
		entityModellDaten = EntityModellDaten(id = this.id), ogSprache = this.ogSprache, translationen = translationen
	)
