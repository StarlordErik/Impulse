package de.seleri.core.domain.mapper

import de.seleri.core.common.id.LokalisierungID
import de.seleri.core.domain.entities.singles.TranslationEntity
import de.seleri.core.domain.modell.Translation

fun TranslationEntity.toModell(): Translation =
	Translation(
		id = this.id, sprache = this.sprache, bezeichnung = this.bezeichnung, bearbeitet = this.bearbeitet
	)

fun Translation.toEntity(lokalisierungsID: LokalisierungID): TranslationEntity =
	TranslationEntity(
		id = this.id,
		lokalisierungID = lokalisierungsID,
		sprache = this.sprache,
		bezeichnung = this.bezeichnung,
		bearbeitet = this.bearbeitet
	)

fun Translation.toDatenbankSlice(lokalisierungsID: LokalisierungID): DatenbankSlice =
	DatenbankSlice(translationen = mutableListOf(this.toEntity(lokalisierungsID)))
