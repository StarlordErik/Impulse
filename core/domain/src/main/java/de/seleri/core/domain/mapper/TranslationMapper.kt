package de.seleri.core.domain.mapper

import de.seleri.core.common.idInt.LokalisierungIDint
import de.seleri.core.domain.entities.singles.TranslationEntity
import de.seleri.core.domain.modell.Translation

fun TranslationEntity.toModell(): Translation =
	Translation(
		id = this.id, sprache = this.sprache, bezeichnung = this.bezeichnung, bearbeitet = this.bearbeitet
	)

fun Translation.toEntity(lokalisierungsID: LokalisierungIDint): TranslationEntity =
	TranslationEntity(
		id = this.id,
		lokalisierungID = lokalisierungsID,
		sprache = this.sprache,
		bezeichnung = this.bezeichnung,
		bearbeitet = this.bearbeitet
	)

fun Translation.toDatenbankSlice(lokalisierungsID: LokalisierungIDint): DatenbankSlice =
	DatenbankSlice(translationen = listOf(this.toEntity(lokalisierungsID)))
