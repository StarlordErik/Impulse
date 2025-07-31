package de.seleri.core.tools.mapper

import de.seleri.core.common.Sprache
import de.seleri.core.common.ids.LokalisierungID
import de.seleri.core.domain.modell.Translation
import de.seleri.core.tools.dtos.TranslationDTO

fun Translation.toDTO(lokalisierungID: LokalisierungID, sprache: Sprache): TranslationDTO =
	TranslationDTO(
		lokalisierungID = lokalisierungID,
		sprache = sprache,
		bezeichnung = this.bezeichnung,
		bearbeitet = this.bearbeitet
	)
