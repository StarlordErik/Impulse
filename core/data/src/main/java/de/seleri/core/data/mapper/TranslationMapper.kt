package de.seleri.core.data.mapper

import de.seleri.core.common.Sprache
import de.seleri.core.common.ids.LokalisierungID
import de.seleri.core.data.entities.TranslationRoom
import de.seleri.core.domain.modell.Translation

fun TranslationRoom.toDomain(): Translation =
	Translation(
		bezeichnung = this.bezeichnung, bearbeitet = this.bearbeitet
	)

fun Translation.toEntity(lokalisierungID: LokalisierungID, sprache: Sprache): TranslationRoom =
	TranslationRoom(
		lokalisierungID = lokalisierungID,
		sprache = sprache,
		bezeichnung = this.bezeichnung,
		bearbeitet = this.bearbeitet
	)
