package de.seleri.core.domain.mapper

import de.seleri.core.common.entities.TranslationEntity
import de.seleri.core.domain.modell.Translation

fun TranslationEntity.toDomain(): Translation =
	Translation(
		bezeichnung = this.bezeichnung, bearbeitet = this.bearbeitet
	)

