package de.seleri.core.data.mapper

import de.seleri.core.data.entities.singles.spielelemente.KartentextRoom
import de.seleri.core.domain.mapper.toEntity
import de.seleri.core.domain.modell.spielelemente.Kartentext

fun Kartentext.toRoom() =
	this.toEntity(::KartentextRoom)
