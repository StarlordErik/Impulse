package de.seleri.core.domain.mapper.eingabeUtils

import de.seleri.core.common.Sprache
import de.seleri.core.common.idInt.TranslationIDint

data class SpracheMitBezeichnung(
	val id: TranslationIDint, val sprache: Sprache = Sprache.OG, val bezeichnung: String
)
