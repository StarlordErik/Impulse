package de.seleri.core.data.mapper

import de.seleri.core.common.Sprache
import de.seleri.core.common.ids.LokalisierungID
import de.seleri.core.data.entities.TranslationRoom
import de.seleri.core.domain.modell.Translation

fun Translation.toRoom(lokalisierungID: LokalisierungID, sprache: Sprache): TranslationRoom =
	TranslationRoom(
		lokalisierungID = lokalisierungID,
		sprache = sprache,
		bezeichnung = this.bezeichnung,
		bearbeitet = this.bearbeitet
	)
