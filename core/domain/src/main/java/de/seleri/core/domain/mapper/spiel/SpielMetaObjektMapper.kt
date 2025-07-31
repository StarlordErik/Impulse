package de.seleri.core.domain.mapper.spiel

import de.seleri.core.common.entities.singles.spielelemente.SpielEntity
import de.seleri.core.domain.modell.Lokalisierung
import de.seleri.core.domain.modell.spielelemente.spiel.SpielMetaDO

fun SpielEntity.toMeta(lokalisierung: Lokalisierung): SpielMetaDO =
	SpielMetaMapper(this, lokalisierung)
