package de.seleri.core.tools.dtos

import de.seleri.core.domain.mapper.toEntity
import de.seleri.core.domain.modell.spielelemente.Kartentext
import de.seleri.core.tools.dtos.singles.spielelemente.KartentextDTO

fun Kartentext.toDTO() =
	this.toEntity(::KartentextDTO)
