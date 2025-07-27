package de.seleri.core.domain.mapper.eingabeUtils

import de.seleri.core.common.Sprache

data class SpracheMitBezeichnung(
	val sprache: Sprache = Sprache.OG, val bezeichnung: String
)
