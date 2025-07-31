@file:JvmName("SpielMetaMapperKt")

package de.seleri.core.domain.mapper.spielelemente.spiel

import de.seleri.core.common.entities.singles.spielelemente.SpielEntity
import de.seleri.core.domain.mapper.spielelemente.SpielelementMapper
import de.seleri.core.domain.modell.Lokalisierung
import de.seleri.core.domain.modell.spielelemente.spiel.SpielMetaDO

fun SpielEntity.toMeta(lokalisierung: Lokalisierung): SpielMetaDO =
	SpielMetaDO(
		spielelementDaten = SpielelementMapper(this, lokalisierung), bildDateiname = this.bildDateiname
	)
